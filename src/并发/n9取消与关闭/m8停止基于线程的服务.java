package 并发.n9取消与关闭;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * 正确的封装原则是：除非拥有某个线程，否则不能对该线程进行操控。
 * 对于持有线程的服务，只要服务的存在时间大于创建线程的方法的存在时间，
 * 那么就应该提供生命周期方法。
 */
public class m8停止基于线程的服务 {

    /**
     * LogService 的日志消息的提交操作为原子操作，解决了日志线程之间的
     * 静态条件问题。并且当对服务进行取消操作或者线程出现中断时，会保持
     * 在队列的日志的有效状态，继续执行直到全部日志取出。
     * <p>
     * 消息加入队列时，尽量不去持有一个锁，因为put方法本身就可以阻塞，会造成
     * 持有锁的线程在阻塞态下不会释放锁，可能会造成死锁。
     * <p>
     * 解决方法是：通过原子方式检查关闭请求，并且有条件地递增一个计数器来“保持”
     * 提交消息的权利。
     */
    public class LogService {
        private final BlockingQueue<String> queue;
        private final LoggerThread loggerThread;
        private final PrintWriter writer;
        private boolean isShutdown; // 关闭请求标志,注意使用的是LogService.this对象监视器锁
        private int reservations; //计数器，同样用的是LogService.this对象监视器锁

        public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
            this.queue = queue;
            this.loggerThread = loggerThread;
            this.writer = writer;
        }

        public void start(){loggerThread.start();}
        public void stop(){
            synchronized (this){isShutdown = true;}
            loggerThread.interrupt();// 对 任务进行中断操作
        }
        public void log(String msg) throws InterruptedException{ //抛出中断异常让服务调用者处理
                 synchronized (this){
                     if(isShutdown)
                         throw new IllegalStateException();//抛出运行时状态异常
                     ++reservations;//增加计数器
                 }
                 queue.put(msg);// 入队列
        }
        private class LoggerThread extends Thread {
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            synchronized (LogService.this) {
                                 if(isShutdown && reservations == 0)//当关闭请求标识为true并且计数器为0（队列里日志已全部处理）跳出循环
                                     break;
                            }
                            String msg = queue.take();//出队列
                            synchronized (LogService.this){--reservations;}
                            writer.println(msg);
                        }catch (InterruptedException e){/* 直接重试*/}
                    }
                }finally {
                    writer.close();//关闭流的嵌套字句柄
                }
            }
        }
    }
}

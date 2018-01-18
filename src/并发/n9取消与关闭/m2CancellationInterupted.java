package 并发.n9取消与关闭;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通常中断是实现取消的最合理方式
 * 取消机制在调用阻塞方法时可能会出现调用不到取消方法cancel()的情况，
 * 因为可能线程一直在阻塞。那么任务就不可能结束。
 *
 * 调用interrupt 并不意味着立即停止目标线程正在进行的工作，
 * 而只是传递了请求中断的消息
 *
 * 当线程在非阻塞状态下中断时，它的中断状态将被设置，然后根据被取消
 * 的操作来检查中断状态以判断发生了中断。
 *
 * 无论任务把中断视为取消，还是其他某个中断响应操作，都应该小心地
 * 保存执行线程的中断状态。如果除了将InterruptedException 传递给调用
 * 者外还需要执行其他操作，那么应该在捕获InterruptedException 之后恢复
 * 中断状态
 */
public class m2CancellationInterupted {
    public class PrimeProducer extends Thread{
        private final BlockingQueue<BigInteger> queue;
        PrimeProducer(BlockingQueue<BigInteger> queue){
            this.queue = queue;
        }
        public void run(){
            try{
                BigInteger p = BigInteger.ONE;
                while (!Thread.currentThread().isInterrupted())
                    queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {//响应中断
              /* 允许线程退出 */
            }
        }
        public void cancel(){interrupt();}
    }
}

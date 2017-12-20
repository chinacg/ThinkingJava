package 并发.n8任务执行;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程池的使用
 */
public class ExecutorExample {
    private final static int threadNum = 100;
    // 通过Executors类的静态工厂方法可以创建不同类型的线程池
    private final static Executor fixedExecutor = Executors.newFixedThreadPool(threadNum); // newFiexedThreadPool 固定数量的线程池

    private final static Executor cachedExecutor =
            Executors.newCachedThreadPool();// newCachedThreadPool 将创建一个可缓存的线程池，如果线程池的
    // 当前规模超过了处理需求时，那么将回收空闲的线程，当需求增加时，则可以添加新的线程，线程的数量没有限制


    private final static Executor singleExecotor = Executors.newSingleThreadExecutor();// 创建单个工作者线程来执行任务，
    // 如果这个线程异常结束，会创建另一个线程来替代

    private final static Executor scheduledExecutor = Executors.newScheduledThreadPool(threadNum);// newScheduledThreadPool 是固定长度的线程池，
    // 而且已延迟或定时的方式来执行任务


    private static void handleRequest(Socket socket) {
        // TODO: 2017-12-20  handle the socket request
    }

    public static void mian(String args[]) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
                fixedExecutor.execute(() -> handleRequest(connection));
        }
    }
}

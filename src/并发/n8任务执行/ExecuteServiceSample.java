package 并发.n8任务执行;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ExecuteServiceSample {
    private final int threadNum = 100;
    private final ExecutorService exec = Executors.newFixedThreadPool(threadNum);

    private static void handleRequest(Socket socket) {
    }

    private static String handleRequestF(Socket socket) {
           return "handle";
    }

    public void start() throws IOException, ExecutionException, InterruptedException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(() -> handleRequest(conn)); // 直接执行任务
                Future<?> result = exec.submit(new FutureTask<>(() -> handleRequestF(conn))); // 执行futureTask，返回Furure类型
                String s = (String) result.get();//阻塞获取
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown())
                    e.printStackTrace();
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }


}

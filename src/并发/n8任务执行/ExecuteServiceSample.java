package 并发.n8任务执行;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;

public class ExecuteServiceSample  {
    private final int threadNum = 100;
    private final ExecutorService exec = Executors.newFixedThreadPool(threadNum);
    private static void handleRequest(Socket socket) {
    }
    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()){
            try {
                final Socket conn = socket.accept();
                exec.execute(()-> handleRequest(conn));

            }catch (RejectedExecutionException e){
                if (!exec.isShutdown())
                    e.printStackTrace();
            }
        }
    }

    public void stop(){exec.shutdown();}


}

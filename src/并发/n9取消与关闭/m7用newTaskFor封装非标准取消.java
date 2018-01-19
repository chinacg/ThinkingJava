package 并发.n9取消与关闭;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * ExecutorService的submit 方法会返回一个Future,我们可以通过这个Future来取消任务。
 * newTaskFor是一个工厂方法，它将创建Future来代表任务。newTaskFor还能返回一个RunnableFuture
 * 接口，该接口扩展了Future和Runnable（并由FutureTask实现）
 */
public class m7用newTaskFor封装非标准取消 {
    public interface CancellableTask<T> extends Callable<T> {
        void cancel();

        RunnableFuture<T> newTask();
    }

    public static class CancellingExecutor extends ThreadPoolExecutor {

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            if (callable instanceof CancellableTask) {
                return ((CancellableTask) callable).newTask();
            } else
                return super.newTaskFor(callable);
        }

        public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }
    }

    /**
     * SocketUsingTask 实现了CancellableTask,并定义了Future.cancel来
     * 关闭套接字和调用super.cancel。如果SocketUsingTask通过其自己的
     * Future来取消，那么底层的套接字将被关闭并且线程将被中断。
     * @param <T>
     */
    public static abstract class SocketUsingTask<T>
                       implements CancellableTask<T>{
        private Socket socket;
        protected synchronized void setSocket(Socket socket){this.socket = socket;}
        public synchronized void cancel(){
            try {
                if(socket !=null){
                    socket.close();
                }
            }catch(IOException ignored){}
        }

        @Override
        public RunnableFuture<T> newTask() {
            return new FutureTask<T>(this){
                public boolean cancel(boolean mayInterrupIfRunning){
                    try {
                        SocketUsingTask.this.cancel();
                    }finally {
                        return super.cancel(mayInterrupIfRunning);
                    }
                }
            };
        }
    }
}

package 并发.n9取消与关闭;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static 并发.utils.LaunderThrowable.launderThrowable;

public class m4计时运行 {

    /**
     * 在指定时间内运行一个任意的Runnable的示例（最好不要这么做）
     * 它在调用线程中运行任务，并安排了一个取消任务，在运行指定的时间间隔后中断它。
     * 这解决了从任务重抛出未检查异常的问题，因为该异常会被timeRun的调用者捕获。
     * 但这种方法破坏了一下规则：在中断线程之前，应该了解它的中断策略。代码中中断的是
     * 调用timeRun方法的线程，会触发调用者中断策略（如果有的话）但是这种结果是不可预测的。
     */
    public static class outerInterrupt {
        private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);

        public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
            final Thread taskThread = Thread.currentThread();
            cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
            r.run();
        }
    }

    /**
     *执行任务线程拥有自己的执行策略，即使任务不响应中断，限时运行的方法仍能返回
     * 到它的调用者。在启动任务线程后，timeRun 将执行一个限时的join方法。在join返回
     * 后，它将检查任务中是否有异常抛出，如果有的话，则会在调用timeRun 的线程中再次抛出该
     * 异常。由于Throwable 将在两个线程之间共享，因此该变量被声明为volatile类型，从而确保
     * 安全地将其从任务线程发布到timeRun线程。
     */
    public static class specailInterrupt {
        private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);
        public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
            class RethrowableTask implements Runnable {
                private volatile Throwable t;

                @Override
                public void run() {
                    try {
                        r.run();
                    }catch (Throwable t){this.t = t;}
                }
                void rethrow(){
                    if (t != null)
                        throw launderThrowable(t);
                }
            }
            RethrowableTask task = new RethrowableTask();
            final Thread taskThead = new Thread(task);
            taskThead.start();
            cancelExec.schedule(taskThead::interrupt,timeout,unit);
            taskThead.join(unit.toMillis(timeout));
            task.rethrow();
        }
    }
}

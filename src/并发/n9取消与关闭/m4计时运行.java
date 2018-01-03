package 并发.n9取消与关闭;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class m4计时运行 {

    /**
     * 在指定时间内运行一个任意的Runnable的示例（最好不要这么做）
     * 它在调用线程
     */
    public static class outerInterrupt{
        private static   final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);
        public static void timedRun(Runnable r, long timeout, TimeUnit unit){
            final Thread taskThread = Thread.currentThread();
            cancelExec.schedule(() -> taskThread.interrupt(),timeout,unit);
            r.run();
        }
    }
}

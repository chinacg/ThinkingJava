package 并发.n2使用Excutor;

import 并发.n1定义任务.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wulei on 16/4/1.
 * singleThreadExecutor就像是线程数量为1的FixedThreadPool.
 * 如果向它提交了多个任务,那么这些任务将排队,每个任务都会在下
 * 个任务开始前运行结束,所有的任务都将使用相同的线程.
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec=
                Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}

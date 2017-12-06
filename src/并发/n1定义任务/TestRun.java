package 并发.n1定义任务;


import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 */
public class TestRun implements Runnable {
    private static int taskNUm=0;
    private final int id=taskNUm++;
    private final String startMessage;
    private final String endMessage;
    public TestRun(){
        startMessage="线程"+id+"启动";
        endMessage="线程"+id+"结束";
    }
    @Override
    public void run() {
         print(startMessage);
        Thread.yield();
        print(endMessage);

    }
}

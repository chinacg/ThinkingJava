package 并发.n1定义任务;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 * 添加更多的线程去驱动更多的任务
 */
public class MoreBasicThreads {
    public static void main(String[] args){
        for(int i=0;i<5;i++)
            new Thread(new LiftOff()).start();
        print("Waiting for LiftOff");
    }
}

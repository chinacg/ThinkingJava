package 并发.n1定义任务;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 * 用Thread驱动LiftOff对象
 */
public class BasicThreads {
    public static void main(String[] args){
        Thread t=new Thread(new LiftOff());
        t.start();
        print("Waiting for LiftOff");
    }
}

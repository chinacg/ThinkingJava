package 并发.n1定义任务;


import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 * 倒计时任务
 */
public class LiftOff implements Runnable {
    protected int counDown=10;//默认值
    private static int taskCount=0;
    private final int id=taskCount++;
    public LiftOff(){}
    public LiftOff(int counDown){
        this.counDown=counDown;
    }
    public String status(){
        return "#"+id+"("+
                (counDown>0?counDown:"Liftoff!")+"), ";
    }
    @Override
    public void run() {
        while (counDown-- >0){
            print(status());
            Thread.yield();
        }
    }
}

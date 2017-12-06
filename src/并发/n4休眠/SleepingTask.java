package 并发.n4休眠;

import 并发.n1定义任务.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 */
public class SleepingTask extends LiftOff {
    public void run(){
        while (counDown-- >0){
            print(status());
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
            exec.execute(new SleepingTask());
        exec.shutdown();
    }
}

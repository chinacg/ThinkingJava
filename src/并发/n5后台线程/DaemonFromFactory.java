package 并发.n5后台线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 */
public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        while (true){//持续打印直到main的线程结束.
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("interrupted");
            }
            print(Thread.currentThread()+" "+this);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool(
                new DaemonThreadFactory());
        for(int i=0;i<10;i++)
            exec.execute(new DaemonFromFactory());
        print("all daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}

package 并发.n5后台线程;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 * 后台（daemon）线程在程序运行时在后台提供一种通用服务,
 * 并且这种线程并不属于程序中不可或缺的部分.当所有的非后台线程结束时,
 * 程序也就终止了,同时会杀死进程中的所有后台线程.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print(Thread.currentThread()+" " +this);
        }

    }
    public static void main(String[] args ) throws Exception{
        for(int i=0;i<10;i++){
            Thread daemon=new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        print("All daemons started");
       TimeUnit.MICROSECONDS.sleep(1);//当main()方法执行完成后,所有的后台线程被杀死,可以设置休眠时间来延后时间来看输出的内容的变化
    }
}

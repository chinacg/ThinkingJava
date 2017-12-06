package 并发.n5后台线程;

import java.util.concurrent.ThreadFactory;

/**
 * Created by wulei on 16/4/1.
 * 定制只能创建后台线程的ThreadFactory
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

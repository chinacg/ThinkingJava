package 并发.n7同步工具类;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁的使用例子
 */
public class countDownSample {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1); // 开市门设置为1
        final CountDownLatch endGate = new CountDownLatch(nThreads);// 结束门设置为线程的总数

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await(); // 线程在此等待开始门
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();//结束门减一
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();// 开始门减一
        endGate.await(); //等待结束门
        long end = System.nanoTime();
        return end - start;

    }
}

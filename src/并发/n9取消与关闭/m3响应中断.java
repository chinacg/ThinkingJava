package 并发.n9取消与关闭;

import java.util.concurrent.BlockingQueue;

public class m3响应中断 {
    public class Task {
    }

    /**
     * 不可取消的任务在退出前恢复中断
     * <p>
     * 对于一些不支持取消但仍可以调用可中断阻塞方法的操作，
     * 它们必须在循环中调用这些方法，并在发现中断后重新尝试。
     * 这这种情况下，它们应该在本地保存中断状态，并在返回前
     * 恢复状态而不是在捕获InterruptedException 时恢复状态。
     * 如果过早地设置中断状态，就可能引起无限循环，因为大多数可
     * 中断的阻塞方法都会在入口处检查中断状态，并且当发现该状态已被
     * 设置时会立即抛出 InterruptedException 。（ 通常，可中断的方法
     * 会在阻塞或进行重要的工作前首先检查中断，从而尽快地响应中断）。
     * @param queue
     * @return
     */
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;//保存中断状态
                    // 重新尝试
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();// 判断保存的中断状态来恢复中断
        }
    }
}

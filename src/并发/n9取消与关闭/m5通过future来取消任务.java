package 并发.n9取消与关闭;


import 并发.utils.LaunderThrowable;

import java.util.concurrent.*;

import static 并发.utils.LaunderThrowable.launderThrowable;

/**
 * ExecutorService.submit 将返回一个Future来描述任务。Future拥有一个cancel
 * 方法，该方法带有一个boolean 类型的参数 mayInterruptIfRunning,表示取消操作
 * 是否成功。通过Executor 线程池执行任务是可以用这个参数的。
 *
 * 当Future.get抛出InterruptedException 或TimeoutException时，如果你知道不在需要结果，
 * 那么就可以调用Future.cancel来取消任务
 */
public class m5通过future来取消任务  {
    final static ExecutorService executorService = Executors.newCachedThreadPool();
   public static void timedRun(Runnable r,
                               long timeout, TimeUnit unit)
                               throws InterruptedException{
       Future<?> task = executorService.submit(r);
       try {
           task.get(timeout,unit);
       }catch (TimeoutException e){
           // 超时任务将被取消
       }catch (ExecutionException e){
          throw launderThrowable(e.getCause()); //如果发生异常会重新抛出
       }finally {
           //如果任务已经结束那么执行取消也不会造成任何影响
           task.cancel(true); //如果任务正在运行，那么将被中断
       }
   }

}

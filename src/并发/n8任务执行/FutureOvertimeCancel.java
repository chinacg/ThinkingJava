package 并发.n8任务执行;

import 并发.n7同步工具类.FutureTaskSample;

import java.util.concurrent.*;

/**
 * 利用future 在限定时间内返回结果，超时会抛出TimeoutException
 *
 * FutureOvertimeCancel 是futre.get 的典型应用。
 * 在它生成的页面中包括响应用户请求的内容以及从广告服务器上获得的广告
 * 它将获取广告的任务提交给一个Executor, 然后计算剩余的文本页面内容，
 * 最后等待广告信息，直到超出指定的时间。如果get超时，那么将取消广告获取
 * 任务，并转而使用默认的广告信息
 */
public class FutureOvertimeCancel {
    private final long TIME_BUDGET = 5000;
    private final ExecutorService executor;
    private final Ad DEFAULT_AD = new Ad();

    FutureOvertimeCancel(ExecutorService executor){
      this.executor = executor;
    }

    Page renderPageWithAd() throws InterruptedException{
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = executor.submit(new FetchAdTask());

        // 在等待广告的同时显示页面
        Page page=new Page();
        // page = renderPageBody();
        Ad ad;
        try{
            // 只等待指定的时间长度
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft,TimeUnit.NANOSECONDS);
        }catch (ExecutionException e){
            ad = DEFAULT_AD;
        }catch (TimeoutException e){
            ad = DEFAULT_AD;
            f.cancel(true); // 超时条件下直接取消任务执行
        }
        page.setAd(ad);
        return page;
    }

    /**
     * 获取广告任务
     */
   public static class FetchAdTask implements Callable<Ad>{

       @Override
       public Ad call() throws Exception {
           return null;
       }
   }

    public static class Page{
        public Ad getAd() {
            return ad;
        }

        public void setAd(Ad ad) {
            this.ad = ad;
        }

        private Ad ad;
    }// 页面类
    public static class Ad{} //广告类
}

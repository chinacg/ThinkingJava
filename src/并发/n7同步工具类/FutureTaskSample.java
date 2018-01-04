package 并发.n7同步工具类;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static 并发.utils.LaunderThrowable.launderThrowable;

public class FutureTaskSample {
    private final FutureTask<ProductInfo> futureTask =
            new FutureTask<>(ProductInfo::loadProductInfo);

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw launderThrowable(cause);
        }
    }



    public static class ProductInfo {
        public static ProductInfo loadProductInfo() {
            return new ProductInfo();
        }
    }
}

package 并发.n7同步工具类;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskSample {
    private final FutureTask<ProductInfo> futureTask =
            new FutureTask<>(() -> ProductInfo.loadProductInfo());

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw landerThrowable(cause);
        }
    }

    public static RuntimeException landerThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException)
            return (RuntimeException) throwable;
        else if (throwable instanceof Error)
            throw (Error) throwable;
        else
            throw new IllegalStateException("Not unchecked", throwable);
    }

    public static class ProductInfo {
        public static ProductInfo loadProductInfo() {
            return new ProductInfo();
        }
    }
}

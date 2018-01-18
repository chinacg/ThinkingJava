package 并发.n8任务执行;

import 并发.n7同步工具类.FutureTaskSample;

import java.util.List;
import java.util.concurrent.*;

import static 并发.utils.LaunderThrowable.launderThrowable;

/**
 * CompletionService 将Executor 和BlockingQueue 的功能融合在一起。你可以将
 * Callable任务交给它来执行，然后使用类似于队列操作的take和poll等方法来获得已完成的结果，
 * 而这些结果会在完成时将被封装为Future。ExecutorCompletionService实现了CompletionService,
 * 并将计算部分委托给一个Executor。
 * <p>
 * ExecutorCompletionServiceSample 实现页面渲染器，缩短了总运行时间及提高了响应性。为每一幅
 * 图像的下载都创建一个独立任务，并在线程池中执行它们，从而将串行的下载过程转换为并行的过程：
 * 减少下载所有图像的总时间。此外，通过从CompletionService 中获取结果以及使每张图片在下载完成后立刻
 * 显示出来，能使用户获得一个更加动态和更高响应性的用户界面。
 */
public class ExecutorCompletionServiceSample {
    private final ExecutorService executor; //用于CompletionService的线程池

    ExecutorCompletionServiceSample(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<>(executor);

        info.stream()
                .forEach((i) -> completionService.submit(i::downloadImageData));// 对每个图片信息实例都单独交于线程池执行

        try{
            for (int t =0,  n = info.size(); t< n; t++){
                Future<ImageData> f = completionService.take(); // 从封闭到线程池类中的阻塞队列里取数据
                ImageData imageData = f.get(); //获取数据
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }catch (ExecutionException e){
            throw launderThrowable(e.getCause());
        }
    }

    public List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

    /**
     * 图片信息类
     */
    public static class ImageInfo {
        ImageData downloadImageData() {
            return null;
        }
    }

    /**
     * 图片数据类
     */
    public static class ImageData {
    }
}

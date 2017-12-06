package 并发.n3从任务中产生返回值;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/1.
 * Callable示例
 */
public class CallableDemo {
    public static void main(String[] args){
        ExecutorService exec= Executors.newCachedThreadPool();
        ArrayList<Future<String>> results=
                new ArrayList<Future<String>>();
        for(int i=0;i<10;i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for(Future<String> fs:results)
            try {
                print(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
    }
}

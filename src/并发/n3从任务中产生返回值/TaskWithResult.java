package 并发.n3从任务中产生返回值;

import java.util.concurrent.Callable;

/**
 * Created by wulei on 16/4/1.
 * 实现Callable接口,在任务完成时能够返回一个值.
 */
public class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id=id;
    }
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult "+id;
    }
}

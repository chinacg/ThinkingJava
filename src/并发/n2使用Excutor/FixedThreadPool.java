package 并发.n2使用Excutor;

import 并发.n1定义任务.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wulei on 16/4/1.
 * 使用优先的线程集来执行所提交的任务;
 */
public class FixedThreadPool {
    public static void main(String[] args){
        ExecutorService exec= Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}

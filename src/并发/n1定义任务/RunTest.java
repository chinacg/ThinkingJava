package 并发.n1定义任务;

/**
 * Created by wulei on 16/4/1.
 */
public class RunTest {
    public static void main(String[] args){
        for(int i=0;i<3;i++){
            Thread t=new Thread(new TestRun());
            t.start();
        }
    }
}

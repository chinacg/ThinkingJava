package 并发.n6编码的变体;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/4/6.
 * 在非常简单的情况下,可以直接从Thread继承这种可替换的方式
 */
public class SimpleThread extends Thread {
    private int countDown=5;
    private static int threadCount=0;
    public SimpleThread(){
        super(Integer.toString(++threadCount));
        start();
    }
    public String toString(){
        return "#"+getName()+"("+countDown+"),";

    }
    public void run(){
        while (true){
            print(this);
            if(--countDown==0)
                return;
        }
    }
    public static void main(String[] args){
        for(int i=0;i<5;i++)
            new SimpleThread();
    }
}

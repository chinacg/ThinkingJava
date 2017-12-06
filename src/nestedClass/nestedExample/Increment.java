package nestedClass.nestedExample;

import nestedClass.nestedExample.nestedInterface.Incremental;

/**
 * Created by wulei on 16/1/12.
 */
public class Increment implements Incremental{

    @Override
    public void increment() {
        int i=0;
        while(i<3) {
            System.out.println(i++);
        }
    }

    @Override
    public void run() {
        this.increment();
    }
}

package nestedClass.nestedExample;

import nestedClass.nestedExample.nestedInterface.Incremental;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wulei on 16/1/12.
 */
public class IncrementNested extends MyIncrement {
    private int p;
    private IncrementNested(){
    }
    private static IncrementNested INCREMENT_NESTED=new IncrementNested();
    private final static AtomicReference<IncrementNested> incrementNested=new AtomicReference<IncrementNested>();

    @Override
    public void increment(){
        super.increment();
            System.out.println(p++);
    }
   private class incrementable implements Incremental{
       @Override
       public void increment() {
           IncrementNested.this.increment();
       }//内部类返回外围类的hook（钩子),获得外围类引用以便以后调用

       @Override
       public void run() {
           this.increment();
       }
   }

    public  Incremental getIncrementalReference(){
        return new incrementable();
    }
    public static IncrementNested makeIncrementNestedSingleUnsafe(){
        return INCREMENT_NESTED;
    }
    public static  IncrementNested makeIncrementNestedSingleSafe(){
        incrementNested.getAndSet(INCREMENT_NESTED);
        return incrementNested.get();
    }


    public static IncrementNested makeIncrementNested(){
        return new IncrementNested();
    }
}

package nestedClass.nestedExample;

import nestedClass.nestedExample.nestedInterface.Incremental;

/**
 * Created by wulei on 16/1/12.
 */
public class IncrementProcessor {
    private Incremental incremental;

    public IncrementProcessor(Incremental incremental) {
        this.incremental = incremental;
    }

    public void process() {
        this.incremental.increment();
    }

    public static void main(String[] args) {
        Increment increment = new Increment();
        IncrementNested incrementNestedSingle = IncrementNested.makeIncrementNestedSingleSafe();
        IncrementNested incrementNestedSingle2 = IncrementNested.makeIncrementNestedSingleSafe();
        IncrementNested incrementNestedSingleUnsafe1 = IncrementNested.makeIncrementNestedSingleUnsafe();
        IncrementNested incrementNestedSingleUnsafe2 = IncrementNested.makeIncrementNestedSingleUnsafe();
        IncrementNested incrementNestedSingleUnsafe3 = IncrementNested.makeIncrementNestedSingleUnsafe();
        IncrementNested incrementNestedSingleUnsafe4 = IncrementNested.makeIncrementNestedSingleUnsafe();
        IncrementNested incrementNested = IncrementNested.makeIncrementNested();
        IncrementNested incrementNested2 = incrementNested.makeIncrementNested();
        IncrementProcessor p1 = new IncrementProcessor(increment);
        IncrementProcessor p2 = new IncrementProcessor(incrementNested.getIncrementalReference());
        IncrementProcessor p3 = new IncrementProcessor(incrementNestedSingle.getIncrementalReference());
        IncrementProcessor p4 = new IncrementProcessor(incrementNestedSingle2.getIncrementalReference());
        IncrementProcessor p5 = new IncrementProcessor(incrementNested2.getIncrementalReference());
        System.out.println("单线程");
        p1.process();
        p2.process();
        p3.process();
        p2.process();
        p3.process();
        p4.process();
        p5.process();
        System.out.println("单例安全的多线程");
        Thread r1 = new Thread(incrementNestedSingle.getIncrementalReference());
        Thread r2 = new Thread(incrementNestedSingle2.getIncrementalReference());
        Thread r3 = new Thread(incrementNestedSingleUnsafe1.getIncrementalReference());
        Thread r4 = new Thread(incrementNestedSingleUnsafe2.getIncrementalReference());
        Thread r5 = new Thread(incrementNestedSingleUnsafe3.getIncrementalReference());
        Thread r6 = new Thread(incrementNestedSingleUnsafe4.getIncrementalReference());
        r1.run();
        r2.run();
        r3.run();
        r4.run();
        r5.run();
        r6.run();

    }
}

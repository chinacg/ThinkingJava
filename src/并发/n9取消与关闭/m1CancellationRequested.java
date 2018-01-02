package 并发.n9取消与关闭;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 在java 中没有一种安全的抢占式方法来停止线程，因此也就没有安全的
 * 抢占式方法来停止任务，只有一些协作式的机制，使请求的任务和代码都
 * 遵循一种协商好的协议。
 * 其中一种协作机制能设置某个“已请求取消”标志，而任务将定期地查看该
 * 标志。如果设置了这个标志，那么任务将提前结束。
 */
public class m1CancellationRequested  {

    public class PrimeGenerator implements Runnable {
        private final List<BigInteger> primes
                = new ArrayList<>();
        private volatile boolean cancelled;// 为了使整个过程尽可能可靠的工作，

        //cancelled 用volatile 类型
        @Override
        public void run() {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                p = p.nextProbablePrime();
                synchronized (this) {
                    primes.add(p);
                }
            }
        }

        public void cancel() {
            cancelled = true;
        }

        public synchronized List<BigInteger> get() {
            return new ArrayList<>(primes);
        }
    }
    List<BigInteger> aSecondOfPrimes() throws InterruptedException{
         PrimeGenerator generator = new PrimeGenerator();
         new Thread(generator).start();
         try{
            SECONDS.sleep(1);
         }finally {
             generator.cancel();
         }
         return generator.get();
    }

}

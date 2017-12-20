package 并发.n7同步工具类;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreSample {
    /**
     * 用信号量实现有边界的集合
     * @param <T>
     */
    public class BoundedHashSet<T> {
        private final Set<T> set;
        private final Semaphore sem;

        public BoundedHashSet(int bound) {
            this.set = Collections.synchronizedSet(new HashSet<>());
            sem = new Semaphore(bound);
        }

        public boolean add(T o) throws InterruptedException {
            sem.acquire(); // 请求许可资源，没有的话阻塞直到资源池有资源
            boolean wasAdded = false;
            try {
                wasAdded = set.add(o);
                return wasAdded;
            } finally {
                if (!wasAdded)
                    sem.release(); // 释放资源到资源池
            }
        }

        public boolean remove(Object o) {
            boolean wasRemoved = set.remove(o);
            if (wasRemoved)
                sem.release();
            return wasRemoved;

        }
    }


}

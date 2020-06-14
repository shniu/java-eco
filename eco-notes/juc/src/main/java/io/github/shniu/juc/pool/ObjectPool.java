package io.github.shniu.juc.pool;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 资源池化，下面是一个对象池的实现
 */
public class ObjectPool<T, R> {
    private Semaphore semaphore;
    private List<T> pools;

    public ObjectPool(int size, T t) {
        pools = new Vector<>();
        for (int i = 0; i < size; i++) {
            pools.add(t);
        }
        semaphore = new Semaphore(size);
    }

    public R exec(Function<T, R> function) {
        T t = null;
        try {
            semaphore.acquire();
            try {
                t = pools.remove(0);
                return function.apply(t);
            } finally {
                if (t != null) {
                    pools.add(t);
                }
                semaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

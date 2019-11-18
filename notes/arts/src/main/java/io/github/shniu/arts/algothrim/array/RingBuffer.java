package io.github.shniu.arts.algothrim.array;

/**
 * RingBuffer 接口.
 * 参考：http://tutorials.jenkov.com/java-performance/ring-buffer.html
 *
 * @author shniu
 * @date 2019/09/25 17:46:46
 */
public interface RingBuffer<T> {
    /**
     * Put an element into ring buffer.
     */
    boolean put(T element);

    /**
     * Take an element from ring buffer.
     */
    T take();

    /**
     * Batch mode
     */
    int put(T[] elements);
    int put(T[] elements, int length);

    int take(T[] into);
    int take(T[] into, int length);
}

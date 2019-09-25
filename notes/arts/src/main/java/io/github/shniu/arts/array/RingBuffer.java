package io.github.shniu.arts.array;

/**
 * RingBuffer 接口.
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
}

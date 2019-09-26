package io.github.shniu.arts.array;

/**
 * @author shniu
 * @date 2019/09/25 17:49:37
 */
public class MyRingBufferImpl<T> implements RingBuffer<T> {
    /**
     * Default capacity
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Buffer 中的元素数据
     */
    private Object[] elements = null;

    /**
     * 读数据的位置
     */
    private int readPos = 0;

    /**
     * 写数据的位置
     */
    private int writePos = 0;

    /**
     * Buffer 中的元素数量
     */
    private int size = 0;

    private MyRingBufferImpl() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyRingBufferImpl(final int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else {
            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    public int capacity() {
        return elements.length;
    }

    public int size() {
        return size;
    }

    public int remainCapacity() {
        return capacity() - size();
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int incrPos(int pos) {
        if (pos == elements.length - 1) {
            return 0;
        } else {
            return pos + 1;
        }
    }

    @Override
    public boolean put(T element) {
        // 已满
        if (remainCapacity() == 0) {
            return false;
        }

        elements[writePos] = element;
        size++;
        writePos = incrPos(writePos);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T take() {
        // 当前没有数据可读
        if (isEmpty()) {
            return null;
        }

        Object element = elements[readPos];
        size--;
        readPos = incrPos(readPos);
        return (T) element;
    }

    @Override
    public int put(T[] elements) {
        return 0;
    }

    @Override
    public int put(T[] elements, int length) {
        return 0;
    }

    @Override
    public int take(T[] into) {
        return 0;
    }

    @Override
    public int take(T[] into, int length) {
        return 0;
    }
}

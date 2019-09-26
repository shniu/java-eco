package io.github.shniu.arts.array;

/**
 * 填充计数的实现
 * - 使用 writePos 标识要写入的位置
 * - 使用 available 标识有多少元素可以被读取
 *
 * @author shniu
 * @date 2019/09/25 17:49:37
 */
public class RingBufferFillCountImpl<T> implements RingBuffer<T> {
    /**
     * Default capacity
     */
    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;
    private int available;
    private int writePos = 0;

    /**
     * Buffer 中的元素数据
     */
    private Object[] elements = null;

    private RingBufferFillCountImpl() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public RingBufferFillCountImpl(final int initialCapacity) {
        capacity = DEFAULT_CAPACITY;
        if (initialCapacity > 0) {
            capacity = initialCapacity;
        }
        elements = new Object[capacity];
    }

    public int remainCapacity() {
        return capacity - available;
    }

    public int capacity() {
        return capacity;
    }

    public int available() {
        return available;
    }

    private boolean isEmpty() {
        return available == 0;
    }

    private boolean isFull() {
        return capacity == available;
    }

    private boolean notFull() {
        return available < capacity;
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
        // 不满
        if (notFull()) {
            if (writePos >= capacity) {
                writePos = 0;
            }
            elements[writePos] = element;
            writePos++;
            available++;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T take() {
        // 当前没有数据可读
        if (isEmpty()) {
            return null;
        }

        // 计算 readPos
        int readPos = Math.abs(writePos - available) % capacity;
        /*if (readPos < 0) {
            readPos = readPos + capacity;
        }*/
        Object element = elements[readPos];
        available--;
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

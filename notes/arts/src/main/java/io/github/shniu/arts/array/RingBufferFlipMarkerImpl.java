package io.github.shniu.arts.array;

/**
 * @author shniu
 * @date 2019/09/26 09:56:58
 */
public class RingBufferFlipMarkerImpl<T> implements RingBuffer<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int readPos;
    private int writePos;
    private boolean flipped;
    private int capacity;
    private Object[] elements;

    public RingBufferFlipMarkerImpl(final int initialCapacity) {
        capacity = DEFAULT_CAPACITY;
        if (initialCapacity > 0) {
            capacity = initialCapacity;
        }
        elements = new Object[capacity];
        flipped = false;
    }

    @Override
    public boolean put(T element) {
        boolean ok = false;
        // 没有翻转，说明 readPos 在追赶 writePos, 只有当 writePos 写到最后时需要考虑翻转
        if (!flipped) {
            // 这里说明已经写到数组的最后一个元素，需要回到0，构成闭环
            if (writePos == capacity) {
                writePos = 0;
                flipped = true;

                // 这个说明当writePos 落后于 readPos 的时候才可写
                if (writePos < readPos) {
                    elements[writePos] = element;
                    writePos++;
                    ok = true;
                }
            } else { // 正常的写入即可
                elements[writePos] = element;
                writePos++;
                ok = true;
            }
        } else { // 这里是翻转了之后的逻辑，writePos 在 readPos 的前面
            if (writePos < readPos) {
                elements[writePos] = element;
                writePos++;
                ok = true;
            }
        }

        return ok;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T take() {
        T ele = null;

        // 没有翻转，只要 readPos 在 writePos 的后面都可以读取
        if (!flipped ) {
            if (readPos < writePos) {
                ele = (T) elements[readPos];
                readPos++;
            }
        } else {
            // readPos 读到了最后，需要回到0位置
            if (readPos == capacity) {
                readPos = 0;
                flipped = false;

                if (readPos < writePos) {
                    ele = (T) elements[readPos];
                    readPos++;
                }
            } else {
                ele = (T) elements[readPos];
                readPos++;
            }
        }

        return ele;
    }

    @Override
    public int put(T[] elements) {
        return put(elements, elements.length);
    }

    @Override
    public int put(T[] elements, int length) {
        return 0;
    }

    @Override
    public int take(T[] into) {
        return take(into, into.length);
    }

    @Override
    public int take(T[] into, int length) {
        return 0;
    }
}

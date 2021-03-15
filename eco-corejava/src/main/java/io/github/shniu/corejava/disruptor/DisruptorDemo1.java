package io.github.shniu.corejava.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;

import java.util.concurrent.ThreadFactory;

/**
 * @author niushaohan
 * @date 2020/8/31 11
 */
public class DisruptorDemo1 {

    @Data
    static class Element {
        private int value;
    }

    public static void test() {
        ThreadFactory threadFactory = r -> new Thread(r, "disruptor");

        EventFactory<Element> eventFactory = Element::new;

        EventHandler<Element> eventHandler =
                (event, sequence, endOfBatch) -> System.out.println("Element: " + event.getValue());

        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        int bufferSize = 16;

        Disruptor<Element> disruptor = new Disruptor<>(eventFactory,
                bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++) {
            long sequence = ringBuffer.next();

            try {
                Element element = ringBuffer.get(sequence);
                element.setValue(l);
            } finally {
                ringBuffer.publish(sequence);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test();
    }
}

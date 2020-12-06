package io.github.shniu.concurrency.guava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author niushaohan
 * @date 2020/11/17 19
 */
public class EventBus01 {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("OrderEventBus");
        eventBus.register(new OrderEventSubscribe());

        eventBus.post(new OrderCreatedEvent("O187312", "120"));
        eventBus.post(new Object());
        eventBus.post(new OrderCancelledEvent("O9999", System.currentTimeMillis()));

        EventBus eventBus1 = new EventBus((exception, context) -> {
            System.out.println(exception);
            System.out.println(context);
        });

        eventBus1.register(new NoopEventSubscribe());
        eventBus1.post(new OrderCreatedEvent("123", "13.0"));
    }

    static class OrderEventSubscribe {
        @Subscribe
        public void handleOrderCreated(OrderCreatedEvent event) {
            System.out.println("Handle 1: " + event);
        }

        @Subscribe
        public void handleOrder2(OrderCreatedEvent event) {
            System.out.println("Handle 2: " + event);
        }

        @Subscribe
        public void handleOrder3(Object event) {
            System.out.println("Handle 3: " + event);
        }
    }

    static class NoopEventSubscribe {
        @Subscribe
        public void handleOrderCreated(DeadEvent event) {
            System.out.println("Dead Handle 1: " + event);
            throw new RuntimeException("Mock");
        }
    }

    @Data
    @ToString
    @AllArgsConstructor
    static class OrderCreatedEvent {
        private String orderId;
        private String amount;
        // ...
    }

    @Data
    @ToString
    @AllArgsConstructor
    static class OrderCancelledEvent {
        private String orderId;
        private long cancelledAt;
        // ...
    }
}

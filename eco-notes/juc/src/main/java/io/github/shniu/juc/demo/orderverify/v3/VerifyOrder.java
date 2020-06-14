package io.github.shniu.juc.demo.orderverify.v3;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VerifyOrder {
    List<Order> proOrders;
    List<Order> deliveryOrders;

    private Executor executor = Executors.newFixedThreadPool(2);

    public void checkAllOrders() {
        while (hasUncheckOrder()) {
            CountDownLatch latch = new CountDownLatch(2);
            executor.execute(() -> {
                proOrders = getProOrders();
                latch.countDown();
            });

            executor.execute(() -> {
                deliveryOrders = getDelivyOrders();
                latch.countDown();
            });

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Order> diff = check(proOrders, deliveryOrders);
            save(diff);
        }
    }

    private void save(List<Order> diff) {
        // todo
    }

    private List<Order> check(List<Order> proOrders, List<Order> deliveryOrders) {
        // todo
        return null;
    }

    private List<Order> getDelivyOrders() {
        // todo
        return null;
    }

    private List<Order> getProOrders() {
        // todo
        return null;
    }

    private boolean hasUncheckOrder() {
        // todo
        return false;
    }
}

class Order {
    // todo
}

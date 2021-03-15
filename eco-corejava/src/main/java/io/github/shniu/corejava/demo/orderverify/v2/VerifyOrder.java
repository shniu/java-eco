package io.github.shniu.corejava.demo.orderverify.v2;

import java.util.List;

public class VerifyOrder {
    List<Order> proOrders;
    List<Order> deliveryOrders;

    public void checkAllOrders() {
        while (hasUncheckOrder()) {
            Thread t1 = new Thread(() -> {
                proOrders = getProOrders();
            });

            Thread t2 = new Thread(() -> {
                deliveryOrders = getDelivyOrders();
            });
            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
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

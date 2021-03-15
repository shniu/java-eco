package io.github.shniu.corejava.demo.orderverify.v1;

import java.util.List;

public class VerifyOrder {

    public void checkAllOrders() {
        while (hasUncheckOrder()) {
            List<Order> proOrders = getProOrders();
            List<Order> deliveryOrders = getDelivyOrders();
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

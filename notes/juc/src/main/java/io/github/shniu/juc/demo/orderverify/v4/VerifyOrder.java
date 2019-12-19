package io.github.shniu.juc.demo.orderverify.v4;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VerifyOrder {
    Vector<Order> proQueue = new Vector<>();
    Vector<Order> deliveryQueue = new Vector<>();

    private Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        executor.execute(this::check);
    });

    private void check() {
        Order proOrder = proQueue.remove(0);
        Order deliveryOrder = deliveryQueue.remove(0);
        boolean diff = check(proOrder, deliveryOrder);
        if (diff) {
            save(proOrder, deliveryOrder);
        }
    }

    private void save(Order proOrder, Order deliveryOrder) {
        // todo
    }

    public void checkAllOrders() {
        new Thread(() -> {
            while (hasUncheckOrder()) {
                for (Order order : getProOrders()) {
                    proQueue.add(order);
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (hasUncheckOrder()) {
                for (Order order : getDelivyOrders()) {
                    deliveryQueue.add(order);
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void save(List<Order> diff) {
        // todo
    }

    private boolean check(Order proOrders, Order deliveryOrders) {
        // todo
        return true;
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

package io.github.shniu.algorithm.struct.map;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author niushaohan
 * @date 2020/9/23 13
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Order, Order> orders = new TreeMap<>(SORT_ASC);

        Order buy1 = Order.builder()
                .amount(1000).price(12000).symbol("BTC_USD").placeTimestamp(System.currentTimeMillis())
                .build();
        orders.put(buy1, buy1);

        Order buy2 = Order.builder()
                .amount(1000).price(11000).symbol("BTC_USD").placeTimestamp(System.currentTimeMillis())
                .build();
        orders.put(buy2, buy2);

        Order buy3 = Order.builder()
                .amount(200).price(11000).symbol("BTC_USD").placeTimestamp(System.currentTimeMillis() - 100)
                .build();
        orders.put(buy3, buy3);

        orders.forEach((order, order2) -> {
            System.out.println(order);
        });
    }

    @Data
    @Builder
    public static class Order {
        private long amount;
        private long price;
        private String symbol;
        private long placeTimestamp;
    }

    final static Comparator<Order> SORT_DESC = (o1, o2) -> {
        if (o1.price > o2.price) {
            return -1;
        } else if (o1.price < o2.price) {
            return 1;
        }

        if (o1.placeTimestamp > o2.placeTimestamp) {
            return -1;
        } else if (o1.placeTimestamp < o2.placeTimestamp) {
            return 1;
        }

        return 0;
    };

    final static Comparator<Order> SORT_ASC = (o1, o2) -> {
        if (o1.price > o2.price) {
            return 1;
        } else if (o1.price < o2.price) {
            return -1;
        }

        if (o1.placeTimestamp > o2.placeTimestamp) {
            return 1;
        } else if (o1.placeTimestamp < o2.placeTimestamp) {
            return -1;
        }

        return 0;
    };
}

package io.github.shniu.coffee.waiter.domain.order.entity;

import io.github.shniu.coffee.waiter.domain.order.valueobject.CoffeeItem;
import io.github.shniu.coffee.waiter.domain.order.valueobject.OrderState;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

/**
 * @author niushaohan
 * @date 2020/6/20 08
 */
@Builder
public class Order {
    private Long id;

    private String customer;
    private OrderState state;
    private List<CoffeeItem> items;

    public void addCoffee(final CoffeeItem coffeeItem) {
        Objects.requireNonNull(coffeeItem);
        items.add(coffeeItem);
    }
}

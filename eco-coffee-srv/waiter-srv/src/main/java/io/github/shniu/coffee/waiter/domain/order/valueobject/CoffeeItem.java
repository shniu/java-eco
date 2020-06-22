package io.github.shniu.coffee.waiter.domain.order.valueobject;

import lombok.Builder;
import lombok.Getter;
import org.joda.money.Money;

/**
 * @author niushaohan
 * @date 2020/6/20 00
 */
@Getter
@Builder
public class CoffeeItem {
    private String name;
    private Money price;
}

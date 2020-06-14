package io.github.shniu.arts.design.oo.badCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 滥用 getter setter 的 Case
 * <p>
 * 分析：
 * 1. itemsCount 和 totalPrice 是两个属性，但是都提供了 set 和 get，是不合理的，并没有达到
 * 封装的目的（隐藏和保护内部数据），因为通过 set 是可以随意修改的，itemsCount 和 totalPrice 应该
 * 根据 items 动态更新，而不是随意 set
 * 2. items 定义了 getter 方法，返回 List，但是 List 里的数据是可以修改的，会影响封装性，外部代码可以通过
 * list.clear() 来清空购物车，但是 itemsCount 和 totalPrice 并没有得到更新，造成了数据的不一致性；如果真的
 * 需要查看购物车里的物品清单，我们可以返回 Collections.unmodifiableList() 来强制避免容器里的数据被修改
 */
public class ShoppingCartBadCase {
    private int itemsCount;
    private BigDecimal totalPrice;
    private List<ShoppingCartItem> items = new ArrayList<>();

    public int getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCartItem> getItems() {
        return this.items;
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
        itemsCount++;
        totalPrice.add(item.getPrice());
    }
}

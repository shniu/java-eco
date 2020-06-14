package io.github.shniu.arts.design.oo;

import java.math.BigDecimal;

/**
 * OO 中封装的例子
 * 金融系统中一个简化版的虚拟钱包的代码实现，给每个用户创建一个虚拟钱包，用来记录用户在我们的系统中的虚拟货币量
 */
public class Wallet {
    // 封装了 4 个属性（成员变量）
    private String id;  // 钱包唯一编号
    private long createTime;  // 钱包创建时间
    private BigDecimal balance; // 余额
    private long balanceLastModifiedTime;  // 余额最后修改时间

    public Wallet() {
        this.id = IdGenerator.getInstance().generate();
        this.createTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    // --- 下面是暴露的接口（方法）
    // id, createTime 只能在创建时初始化，并不能修改，所以不提供 set 入口
    // balance, balanceLastModifiedTime 是共生关系，不需要为 balanceLastModifiedTime 单独设置方法
    // balance 只能增减，不能重新设置，所以提供 increaseBalance and decreaseBalance 两个接口，同时保证了
    //     balance 和 balanceLastModifiedTime 的数据一致性

    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getBalanceLastModifiedTime() {
        return balanceLastModifiedTime;
    }

    public void increaseBalance(BigDecimal increasedAmount) {
        if (increasedAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("InvalidArgumentException");
        }

        //noinspection ResultOfMethodCallIgnored
        this.balance.add(increasedAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    public void decreaseBalance(BigDecimal decreasedAmount) {
        if (decreasedAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("InvalidArgumentException");
        }

        if (decreasedAmount.compareTo(this.balance) > 0) {
            throw new RuntimeException("InsufficientAmountException");
        }

        //noinspection ResultOfMethodCallIgnored
        this.balance.subtract(decreasedAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }
}

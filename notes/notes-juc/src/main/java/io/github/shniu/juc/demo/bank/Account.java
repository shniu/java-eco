package io.github.shniu.juc.demo.bank;

public class Account {
    // 细粒度锁，分别使用两把锁来保护没有关系的两个共享资源
    // 达到可以并行
    private final Object balanceLock = new Object();
    private final Object passwordLock = new Object();

    private int accountId;
    private int balance;
    private String password;

    private Allocator allocator;

    public Account() {
        allocator = Allocator.getInstance();
    }

    public Account(final int balance) {
        this();
        this.balance = balance;
    }

    public Account(final int accountId, final int balance) {
        this();
        this.accountId = accountId;
        this.balance = balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    public void withdraw(final int amt) {
        synchronized (balanceLock) {
            if (balance > amt) {
                balance -= amt;
            }
        }
    }

    public int getBalance() {
        synchronized (balanceLock) {
            return balance;
        }
    }

    public void updatePassword(final String newPassword) {
        synchronized (passwordLock) {
            password = newPassword;
        }
    }

    public String getPassword() {
        synchronized (passwordLock) {
            return password;
        }
    }

    // 会有并发性问题
    public synchronized void transfer(final Account target, final int amt) {
        if (balance > amt) {
            balance -= amt;
            target.balance += amt;
        }
    }

    // 没有并发性问题, 但是这种做法性能差
    public void transfer2(final Account target, final int amt) {
        // 这种做法虽然能解决并发问题，但是性能较差，如果100个人在等待转账，必须得一个一个来
        // 即使有很多人之间是没有关系的
        synchronized (Account.class) {
            if (balance > amt) {
                balance -= amt;
                target.balance += amt;
            }
        }
    }

    // 试着使用两把锁来降低性能差的问题
    public void transfer3(final Account target, final int amt) {
        // 我们使用如下的方式，非常容易造成死锁，需要获取到2把锁后才能进入临界区
        synchronized (this) {
            synchronized (target) {
                if (balance > amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    // 破坏占有且等待条件
    public void transfer4(final Account target, final int amt) {
        // 一次性申请所有资源
        while (!allocator.apply(this, target))
            ;

        try {
            // 锁定当前账户
            synchronized (this) {
                // 锁定目标账户
                synchronized (target) {
                    if (balance > amt) {
                        balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            allocator.free(this, target);
        }
    }

    // 破坏循环等待条件
    public void transfer5(final Account target, final int amt) {
        Account left = this;
        Account right = target;
        if (left.accountId > right.accountId) {
            left = target;
            right = this;
        }

        synchronized (left) {
            synchronized (right) {
                if (balance > amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", password='" + password + '\'' +
                '}';
    }
}

# 对账系统的例子

需求描述：

用户通过在线商城下单，会生成电子订单，保存在订单库；之后物流会生成派送单给用户发货，派送单保存在派送单库。
为了防止漏派送或者重复派送，对账系统每天还会校验是否存在异常订单。

大概就是这样的：

![对账系统](https://static001.geekbang.org/resource/image/06/fe/068418bdc371b8a1b4b740428a3b3ffe.png)

## 实现思路

- 方案1

```text
// 伪代码
while(存在未对账订单) {
    查询未对账订单
    查询对应的派送单
    diff = check(未对账订单, 派送单);
    save(diff);
}
```

单线程实现，可以满足功能。但是性能太低了，完全串行化执行，比如查询不同的订单可以并行，查询订单的时候和对账可以并行等

- 方案2

引入并行来优化性能, 使用两个线程让查询订单并行，主线程等待查询订单完成，然后执行对账和save，看 v2 代码实现.

问题是：主线程要等待t1和t2执行完成后才能执行，while 循环里频繁创建和销毁线程也不是好的方式。可以用线程池来优化。

- 方案3

引入线程池进行优化，配合 CountDownLatch 来等待多个线程完成。

- 方案4

使用 CyclicBarrier 进一步优化，让整个对账过程完全并行，并借助两个队列实现线程间的数据共享。

- 还可以继续优化

比如使用多个 check() 的消费线程
比如引入异步框架，Future和CompletableFuture
比如对于等待线程的操作设置一个超时时间
比如平衡生产者和消费这之间的速率，以免造成 OOM
比如一次取多个进行对账，而不是一个一个取


## 参考

- https://time.geekbang.org/column/article/89461
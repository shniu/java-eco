package io.github.shniu.mq.store;

import java.util.Collection;

/**
 * 使用 Java 或者 C++ 实现一个进程内的队列引擎，单机可支持 100 万队列以上。
 * <p>
 * put 方法将一条消息写入一个队列，这个接口需要是线程安全的，评测程序会并发调用该接口进行 put，
 * 每个 queue 中的内容按发送顺序存储消息（可以理解为 Java 中的 List），同时每个消息会有一个索引，
 * 索引从 0 开始，不同 queue 中的内容，相互独立，互不影响，queueName 代表队列的名称，message
 * 代表消息的内容，评测时内容会随机产生，大部分长度在 58 字节左右，会有少量消息在 1k 左右。
 * <p>
 * get 方法从一个队列中读出一批消息，读出的消息要按照发送顺序来，这个接口需要是线程安全的，也即评
 * 测程序会并发调用该接口进行 get，返回的 Collection 会被并发读，但不涉及写，因此只需要是线程读安全就可以了，
 * queueName 代表队列的名字，offset 代表消息的在这个队列中的起始索引，num 代表读取的消息的条数，
 * 如果消息足够，则返回 num 条，否则只返回已有的消息即可，若消息不足，则返回一个空的集合。
 *
 * ** 评测程序介绍 **
 *
 * 发送阶段：消息大小在 58 字节左右，消息条数在 20 亿条左右，即发送总数据在 100G 左右，总队列数 100w
 * 索引校验阶段：会对所有队列的索引进行随机校验；平均每个队列会校验 1~2 次；(随机消费)
 * 顺序消费阶段：挑选 20% 的队列进行 ** 全部 ** 读取和校验； (顺序消费)
 * 发送阶段最大耗时不能超过 1800s；索引校验阶段和顺序消费阶段加在一起，最大耗时也不能超过 1800s；超时会被判断为评测失败。
 * 各个阶段线程数在 20~30 左右
 *
 * 测试环境为 4c8g 的 ECS，限定使用的最大 JVM 大小为 4GB(-Xmx 4g)。带一块 300G 左右大小的 SSD 磁盘。
 * 对于 Java 选手而言，可使用的内存可以理解为：堆外 4g 堆内 4g。
 *
 * @author niushaohan
 * @date 2021/4/14 16
 */
public abstract class QueueStore {
    abstract void put(String queueName, byte[] message);

    abstract Collection<byte[]> get(String queueName, long offset, long num);
}


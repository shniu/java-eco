package io.github.shniu.arts.algothrim.stack;

/**
 * 栈的接口定义
 * 此接口规定了栈的一般方法，栈的特性：
 * 1. 栈是一种操作受限的线性表，支持在一端插入和删除数据
 * 2. 先进后出
 * <p>
 * 先不做一般的实现，实现一个支持字符串的栈
 */
public interface Stack {
    /**
     * 压栈
     *
     * @param item 要被压栈的元素
     * @return 是否压栈成功；当容量不足时有可能失败
     */
    boolean push(String item);

    /**
     * 出栈
     *
     * @return 栈顶元素
     */
    String pop();

    /**
     * 栈中元素的个数
     */
    int size();

    /**
     * 清空
     */
    void clear();
}

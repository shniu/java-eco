package io.github.shniu.corejava.jvm;

/**
 * @author niushaohan
 * @date 2021/5/27 06
 */
public class Hello {

    public static void main(String[] args) {
        int num = 10;
        double d = 20.0D;
        long l = 1000L;
        byte b = 8;

        if ("".length() < 10) {
            System.out.println("错误：d + l = " + d + l);
        }

        for (int i = 0; i < num; i++) {
            System.out.println("四则运算：num * b = ");
            System.out.println(num * b);
        }
    }
}

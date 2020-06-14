package io.github.shniu.arts.algothrim.leetcode.powxn;

/**
 * https://leetcode-cn.com/problems/powx-n/
 * 50. Pow(x, n)
 */
public class Powxn {
    // 1. 暴力法，求解x的n次方，简单来看就是对x乘n次
    public double myPow1(double x, int n) {
        double res = 1;
        // 如果n是负数，转成正数的处理方式
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        for (int i = 0; i < n; i++) {
            res *= x;
        }
        return res;
    }

    // 2. 分治 -> 递归写法
    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        return dcPow(x, N);
    }

    private double dcPow(double x, long n) {
        // terminator
        if (n == 0) return 1;
        if (n == 1) return x;

        // process current logic: split your big problem, n -> n/2 + n/2
        // drill down -> sub problems
        double subres = dcPow(x, n / 2);

        // merge result & return result
        if (n % 2 == 1) {
            return subres * subres * x;
        } else {
            return subres * subres;
        }
    }

    // 3. 分治 -> 循环写法
    public double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double res = 1;
        double curr = x;
        for (long i = N; i > 0; i /= 2) {
            // 如果是i是奇数，补一个curr
            if ((i % 2) == 1) {
                res *= curr;
            }
            curr = curr * curr;
        }
        return res;
    }

    // 4. 分治 -> 精简写法
    public double myPow4(double x, int n) {
        if (n == 0) return 1.;
        double res = myPow4(x, n / 2);
        return n % 2 == 0 ? res * res :
                n < 0 ? res * res * (1 / x) : res * res * x;
    }
}

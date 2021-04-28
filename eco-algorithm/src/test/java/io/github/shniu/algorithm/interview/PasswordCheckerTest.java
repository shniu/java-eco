package io.github.shniu.algorithm.interview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/28 11
 */
public class PasswordCheckerTest {

    @Test
    public void check() {
        PasswordChecker checker = new PasswordChecker();
        String res = checker.check("abc", "xxcabyy");
        System.out.println(res);

        res = checker.check("abc", "cabyy");
        System.out.println(res);

        res = checker.check("abc", "xxcab");
        System.out.println(res);

        res = checker.check("abc", "xxx");
        System.out.println(res);
    }
}
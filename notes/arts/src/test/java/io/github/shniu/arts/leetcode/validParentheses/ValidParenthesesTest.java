package io.github.shniu.arts.leetcode.validParentheses;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

    @Test
    public void isValid() {
        ValidParentheses validParentheses = new ValidParentheses();
        boolean isValid = validParentheses.isValid1("(())");
        assert isValid;

        isValid = validParentheses.isValid1("((])");
        assert !isValid;

        isValid = validParentheses.isValid1("(([]{}))");
        assert isValid;

        isValid = validParentheses.isValid1("");
        assert isValid;
    }
}
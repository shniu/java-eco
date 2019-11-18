package io.github.shniu.arts.algothrim.leetcode.validParentheses;

import org.junit.Test;

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
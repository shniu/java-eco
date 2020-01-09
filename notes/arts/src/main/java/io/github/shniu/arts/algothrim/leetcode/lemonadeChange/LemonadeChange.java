package io.github.shniu.arts.algothrim.leetcode.lemonadeChange;

/**
 * https://leetcode-cn.com/problems/lemonade-change/
 * 860. 柠檬水找零
 */
public class LemonadeChange {

    // 贪心求解
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {
                ten++;
                five--;
            } else if (ten > 0) {
                ten--;
                five--;
            } else five -= 3;

            if (five < 0) return false;
        }
        return true;
    }
}

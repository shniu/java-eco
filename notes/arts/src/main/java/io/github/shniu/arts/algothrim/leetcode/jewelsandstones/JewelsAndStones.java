package io.github.shniu.arts.algothrim.leetcode.jewelsandstones;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/jewels-and-stones/
 * 771. 宝石与石头
 */
public class JewelsAndStones {

    // 遍历S中的每个字符，看是否在J中，在的话就计数+1
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.length() == 0) return 0;
        if (S == null || S.length() == 0) return 0;

        // 使用 Set 加速查找速度
        Set<Character> jewel = new HashSet<>();
        for (Character c : J.toCharArray()) {
            jewel.add(c);
        }

        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            if (jewel.contains(S.charAt(i))) {
                res++;
            }
        }
        return res;
    }
}

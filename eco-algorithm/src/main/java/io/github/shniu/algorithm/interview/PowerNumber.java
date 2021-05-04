package io.github.shniu.algorithm.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2的幂次方数
 *
 * Tom是一个十分喜欢数学的人，尤其喜欢2的幂次方的数字。现有n（1<=n<=150000）个数字，
 * 对于其中的每一个数字ai(1<=i<n, 1<=ai<=1000000000)，Tom希望找到除了它自己以外的一个数aj(i!=j)，
 * 使得ai+aj是一个2的幂次方数，如果找不到就将这个数删除，问最终需要删除多少个数字。
 * 输入数字个数n和n个数字
 * 输出一个数字，表示最终需要删除的数字的个数
 *
 * https://developer.aliyun.com/coding/40
 *
 * @author niushaohan
 * @date 2021/4/29 16
 */
public class PowerNumber {

    // 把所有数字放在 Map 中
    // 遍历 30 ... 1

    public int power(int n, int[] nums) {
        Set<Integer> idxSet = new HashSet<>();
        Map<Integer, List<Integer>> numCache = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> idxList = numCache.getOrDefault(nums[i], null);
            if (idxList == null) {
                idxList = new ArrayList<>();
                numCache.put(nums[i], idxList);
            }
            idxList.add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : numCache.entrySet()) {
            for (int i = 0; i <= 30; i++) {
                int ai = entry.getKey();
                int aj = (1 << i) - ai;

                if (aj < 0) {
                    continue;
                }

                if (numCache.get(aj) != null) {
                    if (!(numCache.get(aj).size() == 1
                            && numCache.get(aj).get(0).equals(numCache.get(ai).get(0)))) {
                        idxSet.addAll(numCache.get(aj));
                        idxSet.addAll(entry.getValue());
                    }
                }
            }
        }

        return n - idxSet.size();
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        System.out.println(Integer.toBinaryString( 16 & a));
        System.out.println(Integer.toBinaryString( 17 & a));
        System.out.println(Integer.bitCount(16 & a));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(1 << 30);
    }
}

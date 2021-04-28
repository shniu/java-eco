package io.github.shniu.algorithm.interview;

import java.util.Arrays;

/**
 * 最小奖券问题
 *
 * @author niushaohan
 * @date 2021/4/28 19
 */
public class Reward {

    public int minRewards(int[] seqs) {

        // 对序列计算需要的奖券数量，然后对需要的奖券数排序，取前边的一半相加即可

        // 计算每个数字需要的最小奖券数
        int[] rewardNums = new int[seqs.length];
        for (int i = 0; i < seqs.length; i++) {
            rewardNums[i] = caclMinRewardNum(seqs[i]);
        }

        Arrays.sort(rewardNums);
        // System.out.println(Arrays.toString(rewardNums));

        int i = 0;
        int res = 0;
        while (i < rewardNums.length / 2) {
            res += rewardNums[i++];
        }

        return res;
    }

    private int caclMinRewardNum(int seq) {
        int point = 0;
        for (int i = 1; i <= seq / 2; i++) {
            if (i * i == seq) {
                return 0;
            } else if (i * i > seq) {
                point = i;
                break;
            }
        }

        return Math.min(point * point - seq, seq - (point - 1) * (point -1));
    }

    public static void main(String[] args) {
        Reward reward = new Reward();
        int minRewards = reward.minRewards(new int[]{4, 7, 9, 10, 17, 23});
        System.out.println(minRewards); // 1
    }
}

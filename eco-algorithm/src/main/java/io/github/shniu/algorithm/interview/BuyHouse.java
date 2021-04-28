package io.github.shniu.algorithm.interview;

import java.util.Arrays;

/**
 * 买房子获得最大舒适度问题：
 *
 * n 个朋友，每个人有金币 x，有 m 个房子，每个房子有舒适度和价格，当前仅当这个人金币数大于房子价格时才能购买，
 * 每个人至多买一个房子，每个房子最多被一个人买，求最大舒适度
 *
 * @author niushaohan
 * @date 2021/4/28 16
 */
public class BuyHouse {

    static class House implements Comparable<House> {
        int comfortable;
        int price;
        boolean selled;

        public House(int comfortable, int price) {
            this.comfortable = comfortable;
            this.price = price;
            this.selled = false;
        }

        public String toString() {
            return comfortable + " -> " + price;
        }

        @Override
        public int compareTo(House house) {
            int res = Integer.compare(house.comfortable, this.comfortable);
            if (res == 0) {
                return Integer.compare(this.price, house.price);
            }
            return res;
        }
    }

    /**
     * 用尽可能少的钱买舒适度尽可能高的房子.
     *
     * @param houseNum    房子的数量
     * @param houses      房子的舒适度
     * @param personNum   人数
     * @param personCoins 每个人的金币数量
     * @return 最大舒适度
     */
    public int maxComfortable(int houseNum, House[] houses, int personNum, int[] personCoins) {
        int maxComfortable = 0;

        Arrays.sort(personCoins);
        System.out.println(Arrays.toString(personCoins));
        Arrays.sort(houses);
        System.out.println(Arrays.toString(houses));

//        for (int h = 0; h < houseNum; h++) {
//            for (int p = 0; p < personNum; p++) {
//                if (personCoins[p] >= houses[h].price) {
//                    maxComfortable += houses[h].comfortable;
//                    personCoins[p] = -1; // 下次就不可以再买了
//                    break;
//                }
//            }
//        }

        for (int personCoin : personCoins) {
            for (House house : houses) {
                // 找到满足的房子
                if (!house.selled && personCoin >= house.price) {
                    maxComfortable += house.comfortable;
                    house.selled = true;
                    break;
                }
            }
        }

        return maxComfortable;
    }

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouse();

        int max = buyHouse.maxComfortable(4,
                new House[]{new House(4, 2), new House(5, 6), new House(3, 3), new House(2, 1)},
                3,
                new int[]{4, 3, 5});
        System.out.println(max);
    }
}

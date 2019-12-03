package io.github.shniu.arts.algothrim.sort;

/**
 * 冒泡排序，从小到大排序
 */
public class BubbleSort implements Sortable {

    @Override
    public void sort(Comparable[] arr) {
        if (arr.length <= 1) return;

        // 哨兵，来监控是否可以提前结束
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = false;  // 重置状态
            for (int j = 0; j < arr.length - i - 1; j++) {
                //noinspection unchecked
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;  // 有数据交换
                }
            }
            // 在本轮中未发生数据交换，表示已经有序，退出
            if (!flag) break;
        }
    }
}

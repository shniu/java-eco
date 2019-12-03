package io.github.shniu.arts.algothrim.sort;

/**
 * 选择排序实现
 */
public class SelectionSort implements Sortable {

    @Override
    public void sort(Comparable[] arr) {
        // 记录每轮次的最小值的位置，最后做置换
        int minPosPerRound;
        Comparable tmp;
        // loop len=arr.length times
        for (int i = 0; i < arr.length; i++) {
            minPosPerRound = i;  // 重置状态
            // 找到剩余未排序元素的最小值的位置
            for (int j = i + 1; j < arr.length; j++) {
                // minPosPerRound 位置的元素比 j 位置的大，就更新 minPosPerRound
                // arr[minPosPerRound].compareTo(arr[j]) > 0 -> greater(arr[minPosPerRound], arr[j])
                if (greater(arr[minPosPerRound], arr[j])) {
                    minPosPerRound = j;
                }
            }
            // swap，把最小值往前交换
            if (minPosPerRound != i) {
                tmp = arr[i];
                arr[i] = arr[minPosPerRound];
                arr[minPosPerRound] = tmp;
            }
        }
    }
}

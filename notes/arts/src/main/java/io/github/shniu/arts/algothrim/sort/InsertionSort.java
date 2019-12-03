package io.github.shniu.arts.algothrim.sort;

/**
 * 插入排序
 */
public class InsertionSort implements Sortable {

    @Override
    public void sort(Comparable[] arr) {

        int pos;
        Comparable curr;  // 记录每次遍历的当前元素
        for (int i = 1; i < arr.length; i++) {
            pos = i - 1;  // 开始比较的初始位置
            curr = arr[i];  // 当前元素，缓存下来

            // 在前i个有序的数据中找一个合适的插入位置，并移动元素
            // 比较好的处理方式是从后往前比较，找到那个位置
            // arr[pos].compareTo(curr) > 0  -> greater(arr[pos], curr)
            while (pos >= 0 && greater(arr[pos], curr)) {
                arr[pos + 1] = arr[pos];
                pos--;
            }
            arr[pos + 1] = curr;
        }
    }
}

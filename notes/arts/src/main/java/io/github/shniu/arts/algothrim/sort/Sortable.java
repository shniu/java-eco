package io.github.shniu.arts.algothrim.sort;

public interface Sortable {
    void sort(Comparable[] arr);

    default void show(Comparable[] arr) {
        for (Comparable item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    default boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //noinspection unchecked
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    default boolean less(Comparable v, Comparable w) {
        //noinspection unchecked
        return v.compareTo(w) < 0;
    }

    default boolean greater(Comparable v, Comparable w) {
        //noinspection unchecked
        return v.compareTo(w) > 0;
    }

    default boolean equal(Comparable v, Comparable w) {
        //noinspection unchecked
        return v.compareTo(w) == 0;
    }
}

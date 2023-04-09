package com.nhlstenden.ad.sorting;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.Comparator;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the given collection using BubbleSort.
     *
     * @param collection The collection.
     * @param comparator The comparator for how to sort.
     */
    @Override
    public T[] sort(CustomCollection<T> collection, Comparator<T> comparator) {
        int n = collection.getArray().length;
        T[] arr = collection.getArray();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                T curr = arr[j];
                T next = arr[j + 1];
                if(next == null){
                    break;
                }
                if (comparator.compare(curr, next) > 0) {
                    arr[j] = next;
                    arr[j + 1] = curr;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return arr;
    }
}

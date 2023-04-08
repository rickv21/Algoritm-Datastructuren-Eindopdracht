package com.nhlstenden.ad.sorting;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.Arrays;
import java.util.Comparator;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the given collection using BubbleSort.
     *
     * @param collection The collection.
     * @param comparator The comparator for how to sort.
     */
    @Override
    public void sort(CustomCollection<T> collection, Comparator<T> comparator) {
        System.out.println(Arrays.toString(collection.getArray()));
        int n = collection.getArray().length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                T curr = collection.getArray()[j];
                T next = collection.getArray()[j + 1];
                if(next == null){
                    break;
                }
                if (comparator.compare(curr, next) > 0) {
                    collection.getArray()[j] = next;
                    collection.getArray()[j + 1] = curr;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.println(Arrays.toString(collection.getArray()));
    }
}

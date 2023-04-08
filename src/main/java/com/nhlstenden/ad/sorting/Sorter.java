package com.nhlstenden.ad.sorting;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.Comparator;

public interface Sorter<T extends Comparable<T>> {
    void sort(CustomCollection<T> collection, Comparator<T> comparator);
}
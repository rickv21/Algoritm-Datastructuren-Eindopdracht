package com.nhlstenden.ad;

import java.util.Comparator;

public interface Sorter<T extends Comparable<T>> {
    void sort(CustomCollection<T> collection, Comparator<T> comparator);
}
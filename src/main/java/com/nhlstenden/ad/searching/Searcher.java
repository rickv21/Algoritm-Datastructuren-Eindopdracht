package com.nhlstenden.ad.searching;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface Searcher<K, T extends Comparable<T>> {
    Set<T> search(K key, CustomCollection<T> collection, Function<T, K> keyExtractor);
}
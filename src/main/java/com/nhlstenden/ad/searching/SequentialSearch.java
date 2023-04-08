package com.nhlstenden.ad.searching;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class SequentialSearch<K, T extends Comparable<T>> implements Searcher<K, T>{

    /**
     * Sequential search algorithm.

     * @return The value if found otherwise null.
     */
    public Set<T> search(K key, CustomCollection<T> collection, Function<T, K> keyExtractor) {
        Set<T> results = new HashSet<>();
        for (T t : collection.getArray()) {
            if(t == null){
                continue;
            }
            if (keyExtractor.apply(t).equals(key)) {
                results.add(t);
            }
        }
        return results;
    }
}

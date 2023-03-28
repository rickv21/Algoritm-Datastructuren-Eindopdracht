package com.nhlstenden.ad.data;

public interface CustomCollection<K, T> {

    boolean add(K key, T value);

    boolean contains(K key);

    T get(K key);

    T remove(K key);

    int getSize();
}

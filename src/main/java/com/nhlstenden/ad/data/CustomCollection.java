package com.nhlstenden.ad.data;

public interface CustomCollection<T> {

    boolean add(T value);

    T[] getArray();

    String[] getStringArray();

    T[] getCollection();

    int getSize();
}

package com.nhlstenden.ad.data;

public interface CustomCollection<V> {

    V[] getArray();

    void setArray(Comparable<V>[] arr);

    String[] getStringArray();

    int getSize();

    void clear();
}

package com.nhlstenden.ad.data;

public interface CustomCollection<V> {

    V[] getArray();

    String[] getStringArray();

    int getSize();

    void clear();
}

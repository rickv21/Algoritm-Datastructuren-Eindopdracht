package com.nhlstenden.ad;

import com.nhlstenden.ad.treemap.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        treeMap.add(treeMap.root, 5, 12);
        treeMap.add(treeMap.root, 4, 11);
        treeMap.add(treeMap.root, 6, 15);
        treeMap.add(treeMap.root, 3, 10);
        treeMap.add(treeMap.root, 2, 9);
        treeMap.printNodes(treeMap.root);
    }
}
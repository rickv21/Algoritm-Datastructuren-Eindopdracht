package com.nhlstenden.ad;

import com.nhlstenden.ad.treemap.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        treeMap.add(treeMap.root, 5, 12);
        treeMap.add(treeMap.root, 2, 11);
        treeMap.add(treeMap.root, 8, 15);
        treeMap.add(treeMap.root, 6, 10);
        treeMap.add(treeMap.root, 9, 9);
        treeMap.remove(treeMap.root, 8);
        treeMap.printNodes(treeMap.root);
    }
}
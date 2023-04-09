package com.nhlstenden.ad;

import com.nhlstenden.ad.data.CircularBuffer;
import com.nhlstenden.ad.data.treemap.TreeMap;
import com.nhlstenden.ad.linkedlist.LinkedList;

public class Main {

    public static void main(String[] args) {
        CircularBuffer<Student> circularBuffer = new CircularBuffer<>(5);
        TreeMap<Integer, Student> treeMap = new TreeMap<>();
        LinkedList<Integer, Student> linkedList = new LinkedList<>();


        AppUI appUI = new AppUI();
        appUI.addTab("Circular Buffer", circularBuffer);
        appUI.addTab("Linked List", linkedList);
        appUI.addTab("Tree Map", treeMap);

        appUI.setVisible(true);

    }


}
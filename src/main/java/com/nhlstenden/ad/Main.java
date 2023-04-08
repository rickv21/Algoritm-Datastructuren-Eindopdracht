package com.nhlstenden.ad;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
        buffer.add(2);
        buffer.add(9);
        buffer.add(1);
        buffer.add(20);
        buffer.add(6);
        buffer.add(7);

        buffer.add(3);

        System.out.println(buffer);

        BubbleSorter<Integer> bubbleSorter = new BubbleSorter<>();
        Comparator<Integer> comparator = Integer::compareTo;
        bubbleSorter.sort(buffer, comparator);

        System.out.println("after");
        System.out.println(buffer);
    }
}
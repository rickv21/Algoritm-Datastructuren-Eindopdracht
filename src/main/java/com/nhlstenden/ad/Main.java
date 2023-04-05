package com.nhlstenden.ad;

public class Main {
    public static void main(String[] args) {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(5, Integer::compare);
        buffer.add(2);
        buffer.add(9);
        buffer.add(1);
        buffer.add(6);

        buffer.add(3);

        buffer.add(20);
        System.out.println(buffer.toString());

        buffer.bubbleSort(Integer::intValue);

        System.out.println("after");
        System.out.println(buffer.toString());
    }
}
package com.nhlstenden.ad.treemap;

public class Node
{
    public Node left = null;
    public Node right = null;
    public int key;
    public int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

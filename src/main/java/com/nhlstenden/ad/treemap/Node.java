package com.nhlstenden.ad.treemap;

public class Node
{
    public Node left = null;
    public Node right = null;
    public int payload;

    public Node(int payload) {
        this.payload = payload;
    }
}

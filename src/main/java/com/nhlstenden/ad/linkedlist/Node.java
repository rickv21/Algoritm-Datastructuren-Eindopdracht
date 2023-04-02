package com.nhlstenden.ad.linkedlist;

public class Node
{
    private Node next = null;
    private int key;
    private int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node next)
    {
        this.next = next;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}

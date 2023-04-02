package com.nhlstenden.ad.linkedlist;

public class LinkedList
{
    private Node head = null;
    private int size;

    public void add(int key, int value)
    {
        Node node = new Node(key, value);
        node.setNext(null);

        if (this.head == null) {
            this.head = node;
        } else {
            Node headNode = head;
            while (headNode.getNext() != null) {
                headNode = headNode.getNext();
            }
            headNode.setNext(node);
        }
        size++;
    }

    public void remove(int key) {
        if (key == 0) {
            this.head = this.head.getNext();
        } else if (key < 0) {
            return;
        } else {
            Node deletedNode;
            Node headNode = this.head;
            int i = 0;
            while (i < key - 1) {
                headNode = headNode.getNext();
                i++;
            }
            deletedNode = headNode.getNext();
            headNode.setNext(deletedNode.getNext());
        }
        size--;
    }

    public void printNodes()
    {
        Node node = head;

        while (node.getNext() != null)
        {
            System.out.println("Key: " + node.getKey() + " Value: " + node.getValue());
            node = node.getNext();
        }
        System.out.println("Key: " + node.getKey() + " Value: " + node.getValue());
    }
}

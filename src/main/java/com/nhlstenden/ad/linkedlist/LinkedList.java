package com.nhlstenden.ad.linkedlist;

public class LinkedList
{
    private Node head = null;
    private int size;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Adds an element to a linked list.
     * @param key the key of the new node.
     * @param value the value of the new node.
     */
    public void add(int key, int value)
    {
        // Creates a new node and sets the element next of the node to null.
        Node node = new Node(key, value);
        node.setNext(null);

        // If the head node is null, the new node becomes the head node.
        if (this.head == null) {
            this.head = node;
        } else {
            // If the head node is not null, loop through the linked list until the last node is reached, and set the new node as the next node.
            Node headNode = head;
            while (headNode.getNext() != null) {
                headNode = headNode.getNext();
            }
            headNode.setNext(node);
        }
        size++;
    }

    /**
     * Removes an element from the linked list with a specific key.
     * @param key the key.
     */
    public void remove(int key) {
        // If the element to be removed is the first element, set the root node to the node next to the root node.
        if (key == 0) {
            this.head = this.head.getNext();
        }
        // If the key is a number lower than 0, return.
        else if (key < 0) {
            return;
        } else {
            // If the key is greater than 0, loop until the element right before the element that is to be deleted,
            // store the node that is to be deleted in deletedNode and set the next node of the headnode to the next node of the deleted node.
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

    /**
     * Searches for an element with a specific key by looping through the collection and returning the found element.
     * @param key the key of the specified element.
     * @return the node if the node with the specified key is found or null if it is not found.
     */
    public Node sequentialSearch(int key) {
        // Loop through all the nodes of the linked list and check if the node is the node with the specified key.
        Node parent = this.head;
        while (parent.getNext() != null) {
            // Return the node if the node is found.
            if (parent.getKey() == key) {
                return parent;
            }
            parent = parent.getNext();
        }
        // Return null if the node with the key does not exist.
        return null;
    }

    /**
     * Print the nodes to the console.
     */
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

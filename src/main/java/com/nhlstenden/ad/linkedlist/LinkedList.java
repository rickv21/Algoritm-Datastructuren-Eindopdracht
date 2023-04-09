package com.nhlstenden.ad.linkedlist;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<K extends Comparable<K>, V> implements CustomCollection<V> {
    private ListNode<K, V> head = null;
    private int size;

    public ListNode<K, V> getHead() {
        return head;
    }

    public void setHead(ListNode<K, V> head) {
        this.head = head;
    }

    /**
     * Adds an element to a linked list.
     * @param key the key of the new node.
     * @param value the value of the new node.
     */
    public void add(K key, V value) {
        // Creates a new node and sets the element next of the node to null.
        ListNode<K, V> node = new ListNode<>(key, value);
        node.setNext(null);

        // If the head node is null, the new node becomes the head node.
        if (this.head == null) {
            this.head = node;
        } else {
            // If the head node is not null, loop through the linked list until the last node is reached, and set the new node as the next node.
            ListNode<K, V> headNode = head;
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
    public void remove(K key) {
        // If the element to be removed is the first element, set the root node to the node next to the root node.
        if (key.equals(head.getKey())) {
            this.head = this.head.getNext();
        }
        // If the key is a number lower than 0, return.
        else if (key.compareTo(head.getKey()) < 0) {
            return;
        } else {
            // If the key is greater than 0, loop until the element right before the element that is to be deleted,
            // store the node that is to be deleted in deletedNode and set the next node of the headnode to the next node of the deleted node.
            ListNode<K, V> deletedNode;
            ListNode<K, V> headNode = this.head;
            while (headNode.getNext() != null && key.compareTo(headNode.getNext().getKey()) > 0) {
                headNode = headNode.getNext();
            }
            if (headNode.getNext() == null) {
                return;
            }
            if (key.equals(headNode.getNext().getKey())) {
                deletedNode = headNode.getNext();
                headNode.setNext(deletedNode.getNext());
                size--;
            }
        }
    }

    /**
     * Print the nodes to the console.
     */
    public void printNodes() {
        ListNode<K, V> node = head;

        while (node.getNext() != null) {
            System.out.println("Key: " + node.getKey() + " Value: " + node.getValue());
            node = node.getNext();
        }
        System.out.println("Key: " + node.getKey() + " Value: " + node.getValue());
    }

    @Override
    public V[] getArray() {
        V[] array  = (V[]) new Comparable[size];
        ListNode<K, V> node = head;

        int i = 0;
        while (node.getNext() != null) {
            array[i] = node.getValue();
            node = node.getNext();
            i++;
        }
        array[i] = node.getValue();

        return array;
    }

    @Override
    public String[] getStringArray() {
        String[] array =  new String[size];
        ListNode<K, V> node = head;

        int i = 0;
        if(head == null){
            return array;
        }
        while (node.getNext() != null) {
            array[i] = node.getValue().toString();
            node = node.getNext();
            i++;
        }
        array[i] = node.getValue().toString();
        return array;
    }

    //Needed because the linked list uses a list instead of a array.
    public void setArray(Comparable<V>[] arr){
        List<K> keys = new ArrayList<>();
        List<V> values = new ArrayList<>();

        List<V> temp = (List<V>) List.of(arr);


        for (V value : temp) {
            ListNode<K, V> node = head;
            //Get keys for values.
            if(node.getValue().equals(value)){
                keys.add(node.getKey());
                values.add(value);
                continue;
            }

            while(node.getNext() != null){
                node = node.getNext();
                if(node.getValue().equals(value)){
                    keys.add(node.getKey());
                    values.add(value);
                }
            }

        }
        head = new ListNode<>(keys.get(0), values.get(0));
        ListNode<K, V> next = head;
        for(int i = 1; i < keys.size(); i++){
            next.setNext(new ListNode<>(keys.get(i), values.get(i)));
            next = next.getNext();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}

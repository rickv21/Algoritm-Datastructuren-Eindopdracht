package com.nhlstenden.ad.linkedlist;

public class ListNode<K, V> {
    private ListNode<K, V> next = null;
    private K key;
    private V value;

    public ListNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public ListNode<K, V> getNext() {
        return next;
    }

    public void setNext(ListNode<K, V> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
}

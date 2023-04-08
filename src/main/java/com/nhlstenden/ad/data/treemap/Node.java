package com.nhlstenden.ad.data.treemap;

public class Node<K extends Comparable<K>, T> {
    private Node<K, T> left = null;
    private Node<K, T> right = null;
    private K key;
    private T value;

    public Node(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public Node<K, T> getLeft() {
        return left;
    }

    public void setLeft(Node<K, T> left) {
        this.left = left;
    }

    public Node<K, T> getRight() {
        return right;
    }

    public void setRight(Node<K, T> right) {
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
           return "Node: key: " + getKey() + ", value: " + getValue() + (left != null ? "\n     Left: " + left : "") + (right != null ? "\n     Right: " + right : "");
    }

    public String toString(int level) {
        level++;
        StringBuilder s = new StringBuilder("Node: key: " + getKey() + ", value: " + getValue());
        s.append("\n");
        s.append("     ".repeat(Math.max(0, level)));
        if(left != null){
            s.append("Left: ").append(left.toString(level));
        } else {
            s.append("Left: null");
        }
        s.append("\n");
        s.append("     ".repeat(Math.max(0, level)));
        if(right != null){
            s.append("Right: ").append(right.toString(level));
        } else {
            s.append("Right: null");
        }
        return s.toString();
    }
}

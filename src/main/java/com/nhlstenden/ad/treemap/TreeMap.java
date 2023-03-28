package com.nhlstenden.ad.treemap;

public class TreeMap
{
    public Node root;

    public TreeMap() {
        root = new Node(10, 15);
    }

    public Node add(Node parent, int key, int value) {
        if (parent != null) {
            if (key < parent.key) {
                parent.left = add(parent.left, key, value);
            }
            else if (key > parent.key) {
                parent.right = add(parent.right, key, value);
            }
            return parent;
        } else {
            return new Node(key, value);
        }
    }

    /*public Node remove(Node parent, int key) {
        if (parent.key != key) {
            if (key < parent.key) {
                parent.left = remove(parent.left, key);
            } else {
                parent.right = remove(parent.right, key);
            }
            //return parent;
        } else {
            if (parent.left == null) {
                return parent.right;
            } else if (parent.right == null) {
                return parent.left;
            } else {

            }

        }
    }*/

    public void printNodes(Node parent) {
        //Node tree = root;
        if (parent != null) {
            System.out.println("Node with key: " + parent.key + " and value: " + parent.value);
            printNodes(parent.left);
            printNodes(parent.right);
        }
    }
}

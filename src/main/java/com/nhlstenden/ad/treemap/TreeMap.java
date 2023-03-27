package com.nhlstenden.ad.treemap;

public class TreeMap
{
    public Node root;

    public TreeMap() {
        root = new Node(15);
    }

    public Node add(Node parent, int payload) {
        if (parent != null) {
            if (payload < parent.payload) {
                parent.left = add(parent.left, payload);
            }
            else if (payload > parent.payload) {
                parent.right = add(parent.right, payload);
            }
            return parent;
        } else {
            return new Node(payload);
        }
    }

    public void printLeftNodes() {
        Node tree = root;
        while (tree.left != null) {
            System.out.println(tree.left.payload);
            tree = tree.left;
        }
    }
}

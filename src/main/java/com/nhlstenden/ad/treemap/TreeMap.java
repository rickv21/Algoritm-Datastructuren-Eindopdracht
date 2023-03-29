package com.nhlstenden.ad.treemap;

import java.util.ArrayList;

public class TreeMap
{
    public Node root;

    private ArrayList<Node> nodes;

    public TreeMap() {
        root = new Node(10, 15);
        nodes = new ArrayList<>();
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

    public Node remove(Node parent, int key) {
        if (parent == null) {
            return null;
        }
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
            }
            if (parent.right == null) {
                return parent.left;
            }
            Node newParent = getSmallestNode(parent.right);
            parent.key = newParent.key;
            parent.value = newParent.value;
            parent.right = remove(parent.right, newParent.key);
        }
        return parent;
    }

    /*public void restructureTree(ArrayList<Node> nodes) {
        nodes.remove(0);
        for (Node node : nodes) {
            System.out.println(node.key);
        }
        nodes.sort((node1, node2) -> {
            if (node1.key == node2.key) {
                return 0;
            }
            return node1.key < node2.key ? -1 : 1;
        });
        for (int i = 1; i < nodes.size(); i++) {
            add(root, nodes.get(i).key, nodes.get(i).value);
        }
        this.nodes.clear();
        //return newParent;
    }

    public ArrayList<Node> getNodes(Node parent) {
        if (parent != null) {
            this.nodes.add(parent);
            getNodes(parent.left);
            getNodes(parent.right);
        }
        return nodes;
    }*/

    public Node getSmallestNode(Node parent) {
        while (parent.left != null) {
            parent = parent.left;
        }
        return parent;
    }

    public void printNodes(Node parent) {
        //Node tree = root;
        if (parent != null) {
            System.out.println("Node with key: " + parent.key + " and value: " + parent.value);
            printNodes(parent.left);
            printNodes(parent.right);
        }
    }
}

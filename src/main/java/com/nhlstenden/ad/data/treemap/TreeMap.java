package com.nhlstenden.ad.data.treemap;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.ArrayList;
import java.util.List;

public class TreeMap<K extends Comparable<K>, T> implements CustomCollection<T> {
    public Node<K, T> root = null;

    private int size;

    private final List<Node<K, T>> nodes;

    public TreeMap() {
        nodes = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Adds an element with a key and a value to a treemap.
     * @param parent the parent node the treemap the element is to be added to.
     * @param key the key of the new element.
     * @param value the value of the new element.
     * @return a new node if a position is found, and then recursively return the parent in order to build the treemap.
     */
    public Node<K, T> add(Node<K, T> parent, K key, T value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            System.out.println(value + " - " +size);
            nodes.clear();
            populateNodeWithNodes(root);
            return root;
        }
        if (parent != null) {
            System.out.println(value + " - no number");
            // Check if the key is smaller or greater than the key of the parent node.
            if (key.compareTo(parent.getKey()) < 0) {
                parent.setLeft(add(parent.getLeft(), key, value));
            } else if (key.compareTo(parent.getKey()) > 0) {
                parent.setRight(add(parent.getRight(), key, value));
            }
            // Recursively return parent to build treemap.
            nodes.clear();
            populateNodeWithNodes(root);
            return parent;
        } else {
            // If a position is found, return a new node with the specified key value pair in order to place it in the found position.
            size++;
            System.out.println(value + " - " +size);
            return new Node<>(key, value);
        }
    }

    /**
     * Removes an element with a specific key from a treemap, and rearranges the treemap if necessary.
     * @param parent the parent node of the treemap the element is to be added to.
     * @param key the key of the element that must be removed.
     * @return null if the element with the specific key does not exist, parent.left or parent.right if
     * the node that is to be removed has one child node, or recursively return the parent in order to build the treemap.
     */
    public Node<K, T> remove(Node<K, T> parent, K key) {
        // If the node with the specified key does not exist, return null.
        if (parent == null) {
            return null;
        }
        int cmp = key.compareTo(parent.getKey());
        if (cmp != 0) {
            // Check if the key is smaller or greater than the key of the parent node.
            if (cmp < 0) {
                parent.setLeft(remove(parent.getLeft(), key));
            } else {
                parent.setRight(remove(parent.getRight(), key));
            }
        } else {
            // If the found node that is to be removed has no left child node, replace the found node with its right child node.
            if (parent.getLeft() == null) {
                size--;
                return parent.getRight();
            }
            // If the found node that is to be removed has no right child node, replace the found node with its left child node.
            if (parent.getRight() == null) {
                size--;
                return parent.getLeft();
            }
        /* If the found node has two child nodes, find the smallest node on the right side of the found node, replace the
           key value pair of the found node with the smallest node and then remove the smallest node from the tree. */
            Node<K, T> newParent = getSmallestNode(parent.getRight());
            parent.setKey(newParent.getKey());
            parent.setValue(newParent.getValue());
            parent.setRight(remove(parent.getRight(), newParent.getKey()));
        }
        // Recursively return parent to build treemap.
        return parent;
    }

    /**
     * Balances the treemap by changing the root node, making the amount of elements on the left of the new root node
     * equal to the amount of elements on the right of the new root node.
     * @param parent the root node of the treemap.
     */
    public void balanceTree(Node<K, T> parent) {
        nodes.clear();
        populateNodeWithNodes(parent);

        // Sort the nodes arraylist based on key from lowest to highest.
        nodes.sort((node1, node2) -> {
            if (node1.getKey().equals(node2.getKey())) {
                return 0;
            }
            return node1.getKey().compareTo(node2.getKey()) < 0 ? -1 : 1;
        });
        // Get the middle node from the arraylist.
        Node<K, T> middleNode = nodes.get((nodes.size() / 2));
        // Remove the middle node from the arraylist.
        nodes.remove(nodes.size() / 2);
        // Add the middle node to the beginning of the arraylist.
        nodes.add(0, middleNode);

        // Reset the treemap and the size variable.
        this.root = null;
        this.size = 0;

        // Add each node from the arraylist to the empty treemap.
        for (Node<K, T> node : this.nodes) {
        //    System.out.println(node);
            add(this.root, node.getKey(), node.getValue());
        }

    }

    /**
     * Finds the smallest child node of a specified parent node.
     * @param parent the parent node.
     * @return the smallest child node of the parent node.
     */
    public Node<K, T> getSmallestNode(Node<K, T> parent) {
        // Find the last and smallest node on the left side of the parent.
        while (parent.getLeft() != null) {
            parent = parent.getLeft();
        }
        // Return the smallest node.
        return parent;
    }

    /**
     * Populates the ArrayList named nodes with all the nodes from the specified treemap.
     * @param parent the parent node of the treemap.
     */
    public void populateNodeWithNodes(Node<K, T> parent) {
        // Traverse through the treemap using preorder traversal and add each node of the treemap to the nodes arraylist.
        if (parent != null) {
            this.nodes.add(parent);
            populateNodeWithNodes(parent.getLeft());
            populateNodeWithNodes(parent.getRight());
        }
    }

    /**
     * Prints the key value pairs of all the nodes in a treemap to the console.
     * @param parent the parent node of the treemap.
     */
    public void printNodes(Node<K, T> parent) {
        // Traverse through the treemap using preorder traversal and print the key value pairs of each node.
        if (parent != null) {
            System.out.println(parent.toString(0));
        }
    }

    /**
     * Sequential search algorithm that uses preorder traversal to find a node in a treemap.
     * @param parent the treemap.
     * @param key the key that needs to be searched.
     * @return the node if it is found or null if the node with the specified key does not exist.
     */
    public Node<K, T> sequentialSearch(Node<K, T> parent, K key) {
        if (parent != null) {
            if (parent.getKey().equals(key)) {
                return parent;
            }
            Node<K, T> left = sequentialSearch(parent.getLeft(), key);
            if (left != null) {
                return left;
            }
            return sequentialSearch(parent.getRight(), key);
        }
        return null;
    }

    @Override
    public T[] getArray() {
        T[] array = (T[]) new Object[size];
        for(int i = 0; i < nodes.size(); i++){
            Node<K, T> node = nodes.get(i);
            array[i] = node.getValue();
        }
        return array;
    }

    @Override
    public String[] getStringArray() {
        System.out.println(size);
        String[] array =  new String[size];
        for(int i = 0; i < nodes.size(); i++){
            Node<K, T> node = nodes.get(i);
            array[i] = node.getValue().toString();
        }
        return array;
    }

    public int getSize() {
        return this.size;
    }
}

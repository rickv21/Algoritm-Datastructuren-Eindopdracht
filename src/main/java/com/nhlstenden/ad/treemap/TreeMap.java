package com.nhlstenden.ad.treemap;

import java.util.ArrayList;

public class TreeMap<T>
{
    public Node root = null;

    private int size;

    private ArrayList<Node> nodes;

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
    public Node add(Node parent, int key, int value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return root;
        }
        if (parent != null) {
            // Check if the key is smaller or greater than the key of the parent node.
            if (key < parent.key) {
                parent.left = add(parent.left, key, value);
            }
            else if (key > parent.key) {
                parent.right = add(parent.right, key, value);
            }
            // Recursively return parent to build treemap.
            return parent;
        } else {
            // If a position is found, return a new node with the specified key value pair in order to place it in the found position.
            size++;
            return new Node(key, value);
        }
    }

    /**
     * Removes an element with a specific key from a treemap, and rearranges the treemap if necessary.
     * @param parent the parent node of the treemap the element is to be added to.
     * @param key the key of the element that must be removed.
     * @return null if the element with the specific key does not exist, parent.left or parent.right if
     * the node that is to be removed has one child node, or recursively return the parent in order to build the treemap.
     */
    public Node remove(Node parent, int key) {
        // If the node with the specified key does not exist, return null.
        if (parent == null) {
            return null;
        }
        if (parent.key != key) {
            // Check if the key is smaller or greater than the key of the parent node.
            if (key < parent.key) {
                parent.left = remove(parent.left, key);
            } else {
                parent.right = remove(parent.right, key);
            }
        } else {
            // If the found node that is to be removed has no left child node, replace the found node with its right child node.
            if (parent.left == null) {
                size--;
                return parent.right;
            }
            // If the found node that is to be removed has no right child node, replace the found node with its left child node.
            if (parent.right == null) {
                size--;
                return parent.left;
            }
            /* If the found node has two child nodes, find the smallest node on the right side of the found node, replace the
               key value pair of the found node with the smallest node and then remove the smallest node from the tree. */
            Node newParent = getSmallestNode(parent.right);
            parent.key = newParent.key;
            parent.value = newParent.value;
            parent.right = remove(parent.right, newParent.key);
        }
        // Recursively return parent to build treemap.
        return parent;
    }

    /**
     * Balances the treemap by changing the root node, making the amount of elements on the left of the new root node
     * equal to the amount of elements on the right of the new root node.
     * @param parent the root node of the treemap.
     */
    public void balanceTree(Node parent) {
        populateNodeWithNodes(parent);
        // Sort the nodes arraylist based on key from lowest to highest.
        nodes.sort((node1, node2) -> {
            if (node1.key == node2.key) {
                return 0;
            }
            return node1.key < node2.key ? -1 : 1;
        });
        // Get the middle node from the arraylist.
        Node middleNode = nodes.get((nodes.size() / 2));
        // Remove the middle node from the arraylist.
        nodes.remove(nodes.size() / 2);
        // Add the middle node to the beginning of the arraylist.
        nodes.add(0, middleNode);
        // Reset the treemap and the size variable.
        this.root = null;
        this.size = 0;
        // Add each node from the arraylist to the empty treemap.
        for (Node node : this.nodes) {
            add(this.root, node.key, node.value);
        }
    }

    /**
     * Finds the smallest child node of a specified parent node.
     * @param parent the parent node.
     * @return the smallest child node of the parent node.
     */
    public Node getSmallestNode(Node parent) {
        // Find the last and smallest node on the left side of the parent.
        while (parent.left != null) {
            parent = parent.left;
        }
        // Return the smallest node.
        return parent;
    }

    /**
     * Populates the ArrayList named nodes with all the nodes from the specified treemap.
     * @param parent the parent node of the treemap.
     */
    public void populateNodeWithNodes(Node parent) {
        // Traverse through the treemap using preorder traversal and add each node of the treemap to the nodes arraylist.
        if (parent != null) {
            this.nodes.add(parent);
            populateNodeWithNodes(parent.left);
            populateNodeWithNodes(parent.right);
        }
    }

    /**
     * Prints the key value pairs of all the nodes in a treemap to the console.
     * @param parent the parent node of the treemap.
     */
    public void printNodes(Node parent) {
        // Traverse through the treemap using preorder traversal and print the key value pairs of each node.
        if (parent != null) {
            System.out.println("Node with key: " + parent.key + " and value: " + parent.value);
            printNodes(parent.left);
            printNodes(parent.right);
        }
    }

    public int getSize() {
        return this.size;
    }
}

/**
 * Class to create and interact with a binary search tree with lazy deletion
 */
public class LazyBinarySearchTree {
    /**
     * Node class that is used to implement the lazy binary search tree
     */
    private class TreeNode{
        private int key;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private boolean deleted;

        /**
         * Constructor method that initializes a new tree node/element
         * @param key_ the value of the element given from the main class
         */
        public TreeNode(int key_){
            key = key_;
            leftChild = null;
            rightChild = null;
            deleted = false;
        }
    }

    private TreeNode root; // declare root of the tree
    private int numNodes;  // declare counter to track size of tree

    /**
     * Constructor method to initialize a new tree
     */
    public LazyBinarySearchTree(){
        root = null;
    }

    /**
     * Method to insert a new element into the tree
     * @param key the element to insert
     * @return true if insertion of element was successful, false otherwise
     */
    public boolean insert(int key){
        // determine if given key is in the range of [1,99]
        if (key < 1 || key > 99)
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");

        // if tree has just been created, create new tree node and assign to root
        if (root == null) {
            root = new TreeNode(key);
            numNodes++;
            return true;
        }

        // call contains() method to determine if the given key is a duplicate
        if (contains(key)){
            return false;
        }

        return insert(root, root, key);
    }

    /**
     * Recursive helper method to insert a new element into the tree
     * @param subRoot the node that will root the new element's node
     * @param node the node that will contain the new element
     * @param key the element to insert
     * @return true if insertion of element was successful, false otherwise
     */
    public boolean insert(TreeNode subRoot, TreeNode node, int key){
        // if the current element is null, create and assign new node to proper subtree
        if (node == null){
            // if given key is less than sub-root's key, assign new node to sub-root's left child
            if (key < subRoot.key) {
                subRoot.leftChild = new TreeNode(key);
                numNodes++; // increase tree size counter when added
                return true;
            }
            // if given key is greater than sub-root's key, assign new node to sub-root's right child
            else {
                subRoot.rightChild = new TreeNode(key);
                numNodes++; // increase tree size counter when added
                return true;
            }
        }
        // if the key already exists but is deleted, "undelete" element/change deleted status to false
        else if (key == node.key){
            node.deleted = false;
            return true;
        }

        // if new node hasn't been created yet, move down the tree based on current node's key compared to given key
        if (key < node.key) // move left if given key is less than current node's key
            return insert(node, node.leftChild, key);
        else // move right if given key is greater than current node's key
            return insert(node, node.rightChild, key);
    }

    /**
     * Method to lazy delete an element from the tree
     * @param key the element to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean delete(int key){
        // determine if given key is in the range of [1,99]
        if (key < 1 || key > 99)
            throw new IllegalArgumentException("Error in delete: IllegalArgumentException raised");

        // if tree is empty, return false
        if (root == null)
            return false;

        TreeNode tempNode = root; // temporary node declared to traverse the tree
        while (tempNode != null){
            // move to left or right child based on current node's key compared to search key
            if (key < tempNode.key)
                tempNode = tempNode.leftChild;
            else if (key > tempNode.key)
                tempNode = tempNode.rightChild;
            else {
                tempNode.deleted = true; // set node's deleted status to true if element exists in tree
                return true;
            }
        }

        return false;
    }

    /**
     * Method to find the minimum non-deleted element in the tree
     * @return value of minimum non-deleted element
     */
    public int findMin(){
        if (root == null) // return -1 if tree is empty
            return -1;
        if (numNodes == 1 && !root.deleted) // if tree only contains one element, return the root's key
            return root.key;

        TreeNode tempNode = root; // temporary node declared to traverse the tree
        int min = root.key; // integer variable to hold the minimum value found

        while (tempNode != null){
            // if the current node's key is less than min and is not deleted, set min value to current key
            if (tempNode.key < min && !tempNode.deleted)
                min = tempNode.key;
            tempNode = tempNode.leftChild; // continue to move down the left-most path of the tree
        }

        return min;
    }

    /**
     * Method to find the maximum non-deleted element in the tree
     * @return value of maximum non-deleted element
     */
    public int findMax(){
        if (root == null) // return -1 if tree is empty
            return -1;
        if (numNodes == 1 && !root.deleted) // if tree only contains one element, return the root's key
            return root.key;

        TreeNode tempNode = root; // temporary node declared to traverse the tree
        int max = root.key; // integer variable to hold the maximum value found

        while (tempNode != null){
            // if the current node's key is greater than max and is not deleted, set max value to current key
            if (tempNode.key > max && !tempNode.deleted)
                max = tempNode.key;
            tempNode = tempNode.rightChild; // continue to move down the right-most path of the tree
        }

        return max;
    }

    /**
     * Method to determine whether the given element exists in the tree and is non-deleted
     * @param key the element to be searched for
     * @return true if the element exists and is non-deleted, false otherwise
     */
    public boolean contains(int key){
        // determine if given key is in the range of [1,99]
        if (key < 1 || key > 99)
            throw new IllegalArgumentException("Error in contains: IllegalArgumentException raised");
        if (root == null) // if tree is empty
            return false;

        return contains(root, key);
    }

    /**
     * Recursive helper method to determine whether the given element exists in the tree and is non-deleted
     * @param node the current node being searched
     * @param key the element to be searched for
     * @return true if the element exists and is non-deleted, false otherwise
     */
    public boolean contains(TreeNode node, int key){
        if (node == null) // if node is empty
            return false;

        // if the given node's key matches the search key and is not deleted, return true
        if (node.key == key && !node.deleted)
            return true;

        // call this method again using both the given node's left and right child to expand search on the tree
        return contains(node.leftChild, key) || contains(node.rightChild, key);
    }

    /**
     * Method to print a pre-order traversal of the tree's elements and their values, including deleted elements
     * @return the string containing each element's value
     */
    public String toString(){
        return toString(root); // calls upon recursive helper method and uses root of the tree as starting node
    }

    /**
     * Method to print a pre-order traversal of the tree's element and their values, including deleted elements
     * @param node the current element to be printed
     * @return the string containing each element's value
     */
    public String toString(TreeNode node){
        if (node == null) // if the given node is empty, return an empty string
            return "";

        if (node.deleted) // if the given node is deleted, print an asterisk along with its key to indicate its status
            return "*" + node.key + " " + toString(node.leftChild) + "" + toString(node.rightChild);
        else
            return node.key + " " + toString(node.leftChild) + "" + toString(node.rightChild);
    }

    /**
     * Method to determine the height of the tree, including deleted elements
     * @return the path length from the root to the deepest element
     */
    public int height(){
        if (root == null) // if tree is empty, return a height of 0
            return 0;
        return height(root); // calls upon recursive helper method and uses root of the tree as starting node
    }

    /**
     * Recursive helper method to determine the height of the tree, including deleted elements
     * @param node the current element being used to find height of the tree
     * @return the path length from the root to the deepest element
     */
    public int height(TreeNode node){
        // if tree is empty or the given node is a leaf node, return 0
        if (node == null || (node.leftChild == null && node.rightChild == null))
            return 0;

        int leftTreeHeight = height(node.leftChild); // integer variable to keep track of left subtree height
        int rightTreeHeight = height(node.rightChild); // integer variable to keep track of right subtree height

        if (leftTreeHeight > rightTreeHeight)
            return leftTreeHeight + 1; // return left subtree height if it's taller than the right subtree
        else
            return rightTreeHeight + 1; // return right subtree height if it's taller than the left subtree
    }

    /**
     * Method to return the count of elements in the tree, including deleted elements
     * @return the count of elements in the tree
     */
    public int size(){
        return numNodes;
    }
}

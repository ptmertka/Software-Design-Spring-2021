// Fig. 21.17: Tree.java
// TreeNode and Tree class declarations for a binary search tree.

// class TreeNode definition
class TreeNode<T extends Comparable<T>> {
    // package access members
    TreeNode<T> leftNode;
    T data; // node value
    TreeNode<T> rightNode;

    // constructor initializes data and makes this a leaf node
    public TreeNode(T nodeData) {
        data = nodeData;
        leftNode = rightNode = null; // node has no children
    }

    // locate insertion point and insert new node; ignore duplicate values
    public void insert(T insertValue) {
        // insert in left subtree
        if (insertValue.compareTo(data) < 0) {
            // insert new TreeNode
            if (leftNode == null)
                leftNode = new TreeNode<T>(insertValue);
            else // continue traversing left subtree recursively
                leftNode.insert(insertValue);
        }
        // insert in right subtree
        else if (insertValue.compareTo(data) > 0) {
            // insert new TreeNode
            if (rightNode == null)
                rightNode = new TreeNode<T>(insertValue);
            else // continue traversing right subtree recursively
                rightNode.insert(insertValue);
        }
    }


} // end class TreeNode

// class Tree definition
public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;

    // constructor initializes an empty Tree of integers
    public Tree() {
        root = null;
    }

    // insert a new node in the binary search tree
    public void insertNode(T insertValue) {
        if (root == null)
            root = new TreeNode<T>(insertValue); // create root node
        else
            root.insert(insertValue); // call the insert method
    }

    // begin preorder traversal
    public void preorderTraversal() {
        preorderHelper(root);
    }

    // recursive method to perform preorder traversal
    private void preorderHelper(TreeNode<T> node) {
        if (node == null)
            return;

        System.out.printf("%s ", node.data); // output node data
        preorderHelper(node.leftNode); // traverse left subtree
        preorderHelper(node.rightNode); // traverse right subtree
    }

    // begin inorder traversal
    public void inorderTraversal() {
        inorderHelper(root);
    }

    // recursive method to perform inorder traversal
    private void inorderHelper(TreeNode<T> node) {
        if (node == null)
            return;

        inorderHelper(node.leftNode); // traverse left subtree
        System.out.printf("%s ", node.data); // output node data
        inorderHelper(node.rightNode); // traverse right subtree
    }

    // begin postorder traversal
    public void postorderTraversal() {
        postorderHelper(root);
    }

    // recursive method to perform postorder traversal
    private void postorderHelper(TreeNode<T> node) {
        if (node == null)
            return;

        postorderHelper(node.leftNode); // traverse left subtree
        postorderHelper(node.rightNode); // traverse right subtree
        System.out.printf("%s ", node.data); // output node data
    }

    /**
     * Calls the outputTree Helper Function on the root of the binary tree
     * @return String: the output of the binary tree in a string format
     */
    public String outputTree(){
        return outputTreeHelper(0, root);
    }

    /**
     * Traverse the binary tree in order, starting from the rightmost node and going left, printing out the values
     * with 5 spaces per line from the left side of the terminal
     * @param totalSpaces int: The number of spaces in between the left side of the terminal and the root node's value
     * @param node TreeNode: The current node in the tree being analyzed by the function
     * @return String: the partial or complete string representation of the binary tree
     */
    private String outputTreeHelper(int totalSpaces, TreeNode<T> node){
        if (node == null){ //if the nodes to the right and left are empty, it passes a null node, so the base case such, and returns an empty string
            return "";
        }
        else{ //if the node itself is not null, it passed the child nodes of the root, first by passing the right node to the function, with five more spaces to be added from the left side of the terminal
            //it then prints the value of the node, which will be once it has gone all the way down the rightmost branch or printed all other in order nodes
            //it adds a multiple of 5 spaces from the left side of the terminal, to show the tree growning, as well as a new line so there is only one number per line
            //it then goes down the left side of the node's tree, so that the traversal order is in order of the tree.
            return outputTreeHelper(totalSpaces + 5, node.rightNode) + returnSpaces(totalSpaces) + node.data +"\n" + outputTreeHelper(totalSpaces + 5, node.leftNode);
        }
    }

    /**
     * Simple helper string to return a certain number of spaces, used to condense code
     * @param totalSpaces int: the total number of spaces to be returned
     * @return String: a string of spaces with a length of totalSpaces
     */
    private String returnSpaces(int totalSpaces){
        String returnVal = "";
        for(int i = 0; i <totalSpaces ; i++){ //basic for loop that goes from 0 to n=totalspaces,
            //making a string of spaces with length totalSpaces and returns it
            returnVal = returnVal + " ";
        }
        return returnVal;
    }



} // end class Tree

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

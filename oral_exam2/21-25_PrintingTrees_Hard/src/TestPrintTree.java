/**
 * Class TestPrintTree: Allows for the testing of the output tree function by creating the same
 * tree as was used in the textbook example
 */
public class TestPrintTree {
    /**
     * Main Function that creates the same tree as in the textbook example, and prints it out
     * @param args Optional list of strings that could be implemented, but wasn't
     */
    public static void main(String args[]){
        Tree<Integer> tree = new Tree<Integer>(); //creates a new Binary Tree and adds the values in the
        //same order as the textbook example
        tree.insertNode(49);
        tree.insertNode(28);
        tree.insertNode(83);
        tree.insertNode(18);
        tree.insertNode(40);
        tree.insertNode(71);
        tree.insertNode(97);
        tree.insertNode(11);
        tree.insertNode(19);
        tree.insertNode(32);
        tree.insertNode(69);
        tree.insertNode(92);
        tree.insertNode(99);

        System.out.println(tree.outputTree()); //Prints out the binary tree

    }
}

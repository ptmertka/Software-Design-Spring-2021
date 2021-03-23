import java.security.SecureRandom;

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
        SecureRandom randomNumber = new SecureRandom(); //creates a random variable to generate random numbers

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

        Tree<Integer> tree2 = new Tree<Integer>(); //creates two new binary trees to further test the outputs
        Tree<Integer> tree3 = new Tree<Integer>();

        for(int i = 0; i < 20 ; i++){
            tree2.insertNode(randomNumber.nextInt(100));
            tree3.insertNode(randomNumber.nextInt(100));
        }

        System.out.println(tree.outputTree()); //Prints out the binary tree from the example, and the 2 random ones
        System.out.println(tree2.outputTree());
        System.out.println(tree3.outputTree());
    }
}

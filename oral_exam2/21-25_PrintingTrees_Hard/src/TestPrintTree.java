public class TestPrintTree {
    public static void main(String args[]){
        Tree<Integer> tree = new Tree<Integer>();
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

        System.out.println(tree.outputTree());

    }
}

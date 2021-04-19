/**
 * Class Runner creates the Adjacency List and returns the proper values
 * @author Peter Mertka
 * @version 4.18.21
 */
public class Runner {
    /**
     * Main function that creates the adjacency list and prints the necessary values
     * @param args String Array: allows for option user inputs, not used here
     */
    public static void main(String args[]){
        AdjacencyList adj = new AdjacencyList(); //creates new AdjacencyList
        System.out.println(adj.getNecessaryValues()); //gets the necessary Values

        System.out.println(adj.toString()); //shows AdjacencyList
    }
}

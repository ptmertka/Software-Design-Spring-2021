import java.util.Arrays;

/**
 * Class runner Creates a BucketSort Object and sorts it and dispalys it to the terminal. This is done for
 * multiple examples
 */
public class Runner {
    /**
     * Main function that creates a number of unsorted arrayLists and displays them in a sorted order
     * after running bucket sort
     * @param args List of optional arguments, where you can put the number of arrays to be sorted
     */
    public static void main(String[] args){

        int[] myArray = {100, 3,62,10};

        BucketSort myBucket = new BucketSort(myArray);

        myBucket.sort();


        System.out.println(Arrays.toString(myBucket.getNumbers()));



    }
}

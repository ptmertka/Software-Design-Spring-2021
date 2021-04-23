import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class runner Creates a number of BucketSort Objects and sorts them and displays them to the terminal. This is done for
 * multiple examples
 */
public class BucketSortRunner {
    /**
     * Main function that creates a number of unsorted arrayLists and displays them in a sorted order
     * after running bucket sort
     * @param args List of optional arguments, where you can put the number of arrays to be sorted
     */
    public static void main(String[] args){

        ArrayList<BucketSort> listOfLists = new ArrayList<BucketSort>(); //list of BucketSort objects to be printed

        Random random1 = new Random(); //random object to get random numbers

        if(args.length != 0){ //if there is arguments to be used
            for (int i = 0; i < Integer.parseInt(args[0]); i++){ //generate what every argument number of BucketSort Objects
                //with randomly generated 15 number arrays

                int [] temp = new int[15];
                for (int j = 0; j < 15; j++){
                    temp[j] = random1.nextInt(1500);
                }
                BucketSort temp2 = new BucketSort(temp);

                listOfLists.add(temp2);

            }
        }
        else{ //if there are no arguments, just make one object
            int [] temp = new int[15];
            for (int j = 0; j < 15; j++){
                temp[j] = random1.nextInt(1500);
            }
            BucketSort temp2 = new BucketSort(temp);

            listOfLists.add(temp2);
        }

        for (int k = 0 ; k < listOfLists.size() ; k++){ //prints out each object's list before and after sort to prove it works
            System.out.println("The list before bucket sorting is\n");
            System.out.println(Arrays.toString(listOfLists.get(k).getNumbers()));
            listOfLists.get(k).sort();
            System.out.println("The list after bucket sorting is\n");
            System.out.println(Arrays.toString(listOfLists.get(k).getNumbers()));
        }



    }
}

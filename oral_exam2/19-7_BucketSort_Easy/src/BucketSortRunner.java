import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class runner Creates a BucketSort Object and sorts it and dispalys it to the terminal. This is done for
 * multiple examples
 */
public class BucketSortRunner {
    /**
     * Main function that creates a number of unsorted arrayLists and displays them in a sorted order
     * after running bucket sort
     * @param args List of optional arguments, where you can put the number of arrays to be sorted
     */
    public static void main(String[] args){

        ArrayList<BucketSort> listOfLists = new ArrayList<BucketSort>();

        Random random1 = new Random();

        if(args.length != 0){
            for (int i = 0; i < Integer.parseInt(args[0]); i++){

                int [] temp = new int[15];
                for (int j = 0; j < 15; j++){
                    temp[j] = random1.nextInt(1500);
                }
                BucketSort temp2 = new BucketSort(temp);

                listOfLists.add(temp2);

            }
        }
        else{
            int [] temp = new int[15];
            for (int j = 0; j < 15; j++){
                temp[j] = random1.nextInt(1500);
            }
            BucketSort temp2 = new BucketSort(temp);

            listOfLists.add(temp2);
        }

        for (int k = 0 ; k < listOfLists.size() ; k++){
            System.out.println("The list before bucket sorting is\n");
            System.out.println(Arrays.toString(listOfLists.get(k).getNumbers()));
            listOfLists.get(k).sort();
            System.out.println("The list after bucket sorting is\n");
            System.out.println(Arrays.toString(listOfLists.get(k).getNumbers()));
        }



    }
}

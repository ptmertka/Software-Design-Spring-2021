import java.util.ArrayList;

/**
 * Class BucketSort: contains the info for the bucketSort object that has the 1D array
 * as well as the 2D Array of arrayLists. Contains a constructor and one method, sort
 * @author Peter Mertka
 * @version 4.22.21
 */
public class BucketSort {

    /**
     Int Array: array of integers used to store the list and subsequent changes of order of numbers
     */
    private int[] numbers;

    /**
     * 2D array of ArrayLists of ints that is used to act as the buckets
     */
    private ArrayList<Integer>[] buckets;

    /**
     * Int: the number of numbers being sorted
     */
    private int n;



    /**
     * Constructor, takes in a array of numbers to be sorted, sets the instance variable properly, and assigns n a value
     * @param numbers Int array of numbers inputted to be sorted
     */
    public BucketSort(int[] numbers){
        this.numbers = numbers;
        n= this.numbers.length;
    }

    public void sort(){

        buckets = new ArrayList[n];
        int passNumber = 1;
        int currentDigit = 0;

        while (buckets[0].size() != n){


            for(int i = 0; i < numbers.length ; i++){

                int temp = numbers[i];

                for (int j = 0 ; j<passNumber ; j++){

                    if(j == passNumber -1){

                        currentDigit = temp % 10;
                    }
                    else{

                        temp = temp /10;
                    }
                }

                buckets[currentDigit].add(numbers[i]);


            }
            passNumber++;

            for (int k = 0; k < buckets.length ; k++ ){

            }
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class BucketSort: contains the info for the bucketSort object that has the 1D array
 * as well as the 2D ArrayList of arrayLists. Contains a constructor and one method, sort
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
    private ArrayList<ArrayList<Integer>> buckets;

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

    /**
     * Getter that returns the array of the bucketSort object
     * @return Array list of ints
     */
    public int[] getNumbers() {
        return numbers;
    }

    /**
     * Runs the bucketSort sorting Algorithm on the list of numbers the object has stored
     */
    public void sort(){

        buckets = new ArrayList<>(10); //creates the rows for digits 0-9

        for(int x = 0; x<10 ; x++){
            buckets.add(new ArrayList<Integer>()); //creates the arrayLists for each row
        }

        int passNumber = 1; //how many times the list has been run through
        int currentDigit = 0; //the digit of whatever number is being placed, caculated based on which pass it is

        while (buckets.get(0).size() != n){ //while the whole list has not been sorted into row zero

            for (int y = 0; y < buckets.size() ; y++){ //clear the arrayLists from the previous pass
                buckets.get(y).clear();
            }

            for(int i = 0; i < numbers.length ; i++){ //for every number in the array



                int temp = numbers[i]; //get that number from the array



                for (int j = 0 ; j<passNumber ; j++){ //for the number of passes, this loop ensure you get the right digit based on the pass number
                    //i.e, if this is the start of the 3rd pass through the buckets, this loop runs 3 times
                    //dividing the number by 10 until it is at the final time where the number divided by ten will get in this case the 100s place digit


                    if(j == passNumber -1){ //if this is the last iteration of the for loop

                        currentDigit = temp % 10; //use modulo 10 to get the digit

                    }
                    else{ //if not the final interation, continually floor divides by 10 thanks to integer division to get the desired digit

                        temp = temp /10;
                    }
                }

                buckets.get(currentDigit).add(numbers[i]); //adds the number to the proper bucket based on the digit being looked at, i.e tens, ones, thousands, etc


            }
            passNumber++; //increase the number of times the buckets have been passed through

            int index = 0; //index variable to put number back into the numbers array instance variable

            for (int k = 0; k < buckets.size() ; k++ ){ //for every row in buckets
                if (buckets.get(k).size() != 0){ //if the arrayList has numbers in it
                    for (int l = 0; l < buckets.get(k).size() ; l++){ //for all numbers in that row
                        numbers[index] = buckets.get(k).get(l); //adds that number into its new place in the numbers array
                        index++; //increases the index so the next number is placed in the new sequential order
                    }
                }


            }


        }
    }
}

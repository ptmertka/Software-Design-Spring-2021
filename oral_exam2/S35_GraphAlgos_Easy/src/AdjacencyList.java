import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class AdjacencyList, holds all the logic and methods to create the AdjacencyList for the list of words
 * Methods include how to see which words are one letter off, and methods to calculate the proper values necessary
 * @author Peter Mertka
 * @version 4.18.21
 */
public class AdjacencyList {

    /**
     * Hashmap of Strings and of LinkedLists of Strings: Stores the list of edges for each word
     */
    private HashMap<String, ArrayList<String>> adjl = new HashMap<String, ArrayList<String>>();

    /**
     * Constructor, reads in words.txt file and assigns the proper nodes and vertices to the adjacency list
     */
    public AdjacencyList(){

        try {

            BufferedReader reader = new BufferedReader(new FileReader("oral_exam2/S35_GraphAlgos_Easy/words/words.txt")); //creates a buffered reader to read in the words
            ArrayList<String> listOfWords = new ArrayList<String>();
            String word; //array list and string to hold every word

            while ((word = reader.readLine()) != null){

                listOfWords.add(word); //adds each word from the txt file to an array list of words
            }

            for(int i = 0 ; i < listOfWords.size() ; i++){ // for every word in the list, add an associated arrayList to that word

                adjl.put(listOfWords.get(i), new ArrayList<String>());

                word = listOfWords.get(i);//temporaily stores the word the for loop is on

                for (int j = 0; j< listOfWords.size() ; j++){ //iterates through the word list one more

                    String word2 = listOfWords.get(j); //temporaily stores the word the second for loop is on

                    if((!word.equals(word2)) && (oneLetterOff(word,word2))){ //if the words arent the same and they are 1 word off
                        adjl.get(word).add(word2);//adds the word as a vertice to the arrayList of the word itself
                    }
                }
            }



        }
        catch (IOException ioe){ //catches exception incase txt file cannot be opened
            ioe.printStackTrace();
        }
    }

    /**
     * Function that compares two strings and sees if they are off by only one edit distance
     * @param s1 String: first string to comapre
     * @param s2 String: second string to compare
     * @return Boolean: whether or not the strings are two letters off or not
     */
    boolean oneLetterOff(String s1, String s2){

        int numDiff = 0; //integer to store the number of differences

        if (!(s1.equals(s2))){ //if the words are not the same

            for(int i = 0; i < s1.length() ; i++){ //for each letter in the word, adds 1 if they dont match

                if (s1.charAt(i) != s2.charAt(i)){

                    numDiff++;
                }
            }
        }

        return numDiff == 1; //returns true if numDiffs is 1, false elsewise
    }


}


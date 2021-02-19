
import java.lang.reflect.Array;
import java.util.Random;
import java.io.*;


/**
 * Class keyFile
 * Meant to hold relevant information regarding a key file, including postion, key itself, and filename. Allows for the creation of key file
 * @author Peter Mertka
 * @version 2/9/2021
 */

public class KeyFile {


    /**
     * integer: the length of the key as chosen by the user
     */
    private int lengthKey;

    /**
     * integer array: an array that stores the key values themselves, randomaly generated numbers from 1-26
     */
    private int[] key;

    /**
     * integer: an number between 1 and the length of the key, signifying where to start encoding from
     */
    private int start;

    /**
     * String: the name of the keyFile, as chosen by the user
     */
    private String filename;

    /**
     * Random: random object that allows for the generation of random numbers
     */
    private Random random1 = new Random();


    /**
     * Class Constructor
     * Creates new keyFiles, taking in the length of the key and what to name the file
     * @param n integer: the length of the key to be created for this file
     * @param whatToName String: the name of the file as chosen by the user, keyFile will become this name .txt
     */
    public KeyFile(int n, String whatToName){
        lengthKey = n;
        filename = whatToName;

    }

    /**
     * Creates a randomly genarated key based upon the user's chosen length, key values are from 1-26 in value
     */
    public void generateKey(){

        key = new int[lengthKey]; //initializes the key instance variable as an int array of length lengthKEy

        start = random1.nextInt(lengthKey) + 1; //randomly chooses a start position between 1 and the max length of the key

        for(int i = 0; i< lengthKey; i++){ //for every empty slot in the key

            key[i] = random1.nextInt(26) + 1; //initialize that slot's value to a number between 1-26
        }
    }

    /**
     * Takes an integer array, aka the key array and converts it to a string so that it can be written to a file
     * @return String: a string representation of the integer array that acts as the key
     */
    private String arrayToString(){

        String convertedString = ""; //blank, generic string to hold converted value

        for(int i = 0; i < lengthKey - 1; i++){ //for every value in the key array, except the last one

            convertedString = convertedString + Integer.toString(key[i]) + "," ; //gets the value of the key, turns it into a character, and adds it to the return string, plus a comma
        }

        convertedString = convertedString + Integer.toString(key[lengthKey-1]); //adds the last number to the string, separately so that no comma is added

        return convertedString;
    }

    /**
     * Creates the actual txt file based on the instance variable of the object. Stores the file in a folder within the module itself
     */
    public void createFile() {

        try { //try block to catch IOException errors
            File file = new File("oral_exam1/S102_OneTimePad_Hard/keyFiles" + filename); //creates a txt file in the proper keyFiles folder

            file.createNewFile();//creates the file itself, throws an error if the file cannot be made

            FileWriter writer = new FileWriter(file); //allows the file to be written to

            writer.write(Integer.toString(start) + "\n"); //appends the start value of the key as astring
            writer.write(arrayToString()); //adds the key array as a string
            writer.flush(); //clears the buffer but doesn't close it
            writer.close(); //closes the bufferstream


        } catch (IOException ioe){ //if an exception happens, catches it, and tells the user
            System.out.println("Trouble reading and/or writing your file, please try again");
            System.out.println("Error Message: " + ioe.getMessage());
        }


    }











}

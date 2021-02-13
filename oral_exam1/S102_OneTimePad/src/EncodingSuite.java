import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Class Encoding Suite
 * Handles the tasks of collecting user input, encoding, and decoding. Also includes the main looping method that allows for user interaction
 * @author Peter Mertka
 * @version 2/11/2021
 */
public class EncodingSuite {


    /**
     * Allows for the creation of a key file given a length of key N and a string of what to name the file
     * Creates a new keyFile Object
     * @param n Interger: how long the key should be when the file is genereated in terms of # of numbers
     * @param whatToNameFile String: what the user wants to call their key File
     */
    public void createKeyFile(int n, String whatToNameFile){

        KeyFile keyfile1 = new KeyFile(n, whatToNameFile); //creates a new keyFile object using the parameters,
        keyfile1.generateKey(); //generates a new key for the keyFile
        keyfile1.createFile(); //Creates the txt keyFile


    }

    /**
     * Allows the encoder to create a new messageFile taking in a start position of the encrpytion, and what the encoded message is
     * @param n Integer: The position in the key where the message began encryption
     * @param encodedMessage String: The encoded message after being translated by the encoder
     * @param messageFileName String: The chosen name of the messageFile as chosen by the user
     */
    public void createMessageFile(int n, String encodedMessage, String messageFileName){

        MessageFile messageFile1 = new MessageFile(n,encodedMessage, messageFileName); //creates the new Message File object

        messageFile1.createFile(); //creates the actual messageFile txt

    }

    /**
     * Error checking and user input retrieving function. Gets a valid integer input from the user, that is greater than 1
     * @return Integer: "i", the valid integer input as chosen by the user
     */
    private int getValidIntInput(){

        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs

        while (i < 1){ //while the user hasn't entered a negative number

            try{
                i = scanner.nextInt(); //gets the user's input

            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number

                String badInput = scanner.next();
                System.out.println("Please enter a valid, positive number, greater than or equal to 1");
                continue; //move past bad input

            }

        }
        return i;
    }

    /**
     * Error checking method that is meant to test if a string contains punctuation or not for file creation
     * Prevents users from entering strings with invalid characters for files to prevent errors
     * @param test String: The userInput gotten from the getValidStringInput Function
     * @return Boolean returnVal: a variable that returns true if the string can be used for filename, false otherwise
     */
    private boolean checkIfStringValid(String test){

        boolean returnVal = true; //creates a base return value variable, as true

        for (int i = 0; i < test.length(); i++){ //for every character in the string
            if(!(Character.isLetterOrDigit(test.charAt(i)) == true || test.charAt(i) == ' ')){ //if a character is not a letter, digit or space, it is invalid,
                returnVal = false; //sets return val to false
            }

        }

        return returnVal;
    }

    /**
     * Similar to getValidIntInput, gets a valid string to user for filenames from the user.
     * Prevents the entry of file names with punctuation
     * @return String a: the valid string as entered by the user
     */
    private String getValidStringInput(){


        String a = "..."; //base value for the String a, ensures that the while loop is entered, as it is only punctuation

        Scanner scanner1 = new Scanner(System.in); //creates a new scanner to get userInput

        while(checkIfStringValid(a) == false){ //if the string still constains punctuation


            a = scanner1.nextLine(); //gets the new String name

            if(checkIfStringValid(a) == false){ //if the user is still incorrect, tells them to reenter a value
                System.out.println("Please enter a string without any punctuation");
            }

        }

        return a;
    }

    /**
     * Identical to arrayToString method from keyFile class, needed so that when the position is updated in a key file, the key itself is also rewritten
     * @param keyVals Integer Array: The array containing all of the key values as integers
     * @return String convertedString: The string representation of the key, as a list of comma separated values
     */
    private String arrayToString(int [] keyVals){

        String convertedString = ""; //empty string to hold conversion

        for(int i = 0; i < keyVals.length - 1; i++){ //for every value in the array

            convertedString = convertedString + Integer.toString(keyVals[i]) + "," ; //converts it to string, separated by commas of each value, except the last value
        }

        convertedString = convertedString + Integer.toString(keyVals[keyVals.length-1]); //last value is added separately to ensure comma is not added

        return convertedString;
    }

    /**
     * Main encoding method, takes in a keyFile and message and converts it to the encoded form of message, also updates the keyPosition in the key file txt
     * @param message String: what message the user wants to convert to a secret message
     * @param filepath String: the filepath of the keyFile method so that it can be accessed and rewritten to
     * @param keyVals Integer Array: an array of the integer values of the key from the key file
     * @param start Integer: where the encoding algorithm should begin to encrypt the message
     * @return String encodedMessage: The string of the encoded message after encryption
     */
    private String encode(String message, String filepath, int[] keyVals, int start){

        char messageAsChar[] = new char[message.length()];//creates a character array so that it can store the converted user message from string to a character array to access each letter and change/update them

        for (int i = 0; i < message.length(); i++){ //for every letter in the message

            messageAsChar[i] = message.charAt(i); //copy that character in the string to the character array
        }

        int position = start; //position is to show where in the list of keyValues the encryption method is

        for(int j = 0; j < messageAsChar.length; j++){ //for every letter in the character array

            if ( (int) messageAsChar[j]==32){ //if the character is a space

                position = position + 1; //moves the position in the keyvalues forward

                if (position > keyVals.length){ //if the position has excededd the length of the key, reset to the beginning of the key
                    position = 1;
                }
            }

            else {

                int temp = (int) messageAsChar[j] + keyVals[position-1]; //move the character to the new character by using ASCII Values of characters to switch letters by number values

                if (temp >90){ //if the character as an integer value is greater than 'Z'

                    temp = 64 + (temp - 90); //resets that letter to what it would be if Z was lined up against another alphabet
                    //I.E. Z + 5 would reset to E, as the 5th letter past Z if the alphabet repeated past Z
                }

                messageAsChar[j] = (char) temp; //converts the ASCII back to a character

                position = position + 1; //moves the position in the key forward

                if (position > keyVals.length){ //if the position in the key would move past the end of the key, moves the position back to the beginning
                    position = 1;
                }

            }

        }

        position = position -1; //puts the position back at whatever last value was used to encrypt, needed since the loop moves the position after converting the letter

        try { //Try Catch needed incase rewriting to the keyFile creates an error

            FileWriter writer = new FileWriter(new File(filepath)); //creates a new FileWriter object from the original keyFile file path

            writer.write(Integer.toString(position) + "\n"); //updates the new position of the key,
            writer.write(arrayToString(keyVals)); //recopys the key itself to the .txt
            writer.flush(); //cleans the writing buffer
            writer.close(); //closes the writing buffer stream

        }catch (IOException ioe){ //if an error is encountered, displays that it occured
            System.out.println("Error overwriting file");
            System.out.println("Error Message: " + ioe.getMessage());
        }

        String encodedMessage = new String(messageAsChar); //turns the character array that has now been converted back into a string



        return encodedMessage;


    }


    /**
     * Similar to encode, but works backwards. Takes an encoded messageFile and the keyFile used to encrypt it,and decodes the message while printing it
     * @param encodedMessage String: the encoded message as taken from the messageFile
     * @param keyVals IntegerArray: the key values as taken from the keyFile, used to decrypt
     * @param start Integer: Where the encrption code last used a value to encode, allowing decoding program to work backwards
     */

    private void decode(String encodedMessage, int[] keyVals, int start){

        char messageAsChar[] = new char[encodedMessage.length()]; //creates a new character array to hold the encoded emssage

        for (int i = 0; i < encodedMessage.length(); i++){ //turns the encoded message into a character array, copying each character from the string
            messageAsChar[i] = encodedMessage.charAt(i);
        }

        int position = start; //once again creates a position varaible to track where it is in the key

        for(int j = messageAsChar.length-1; j >= 0; j--){ //for every character in the encoded message, working backwards

            if ( (int) messageAsChar[j]==32){ //if the character is a space

                position = position - 1; //moves the position back a value, resetting to the end of the key if it goes past the first value

                if (position < 1){
                    position = keyVals.length;
                }
            }

            else {

                int temp = (int) messageAsChar[j] - keyVals[position-1]; //Uses the same method as encoded, turns the character to ASCII
                //and subrtracts of the keyValue used to encode so that the letter goes back to its original value

                if (temp < 65){ //if the letter as ASCII goes past 'A', goes back to the end of the alphabet to continue going backwards
                    temp = 91 + (temp - 65);
                }

                messageAsChar[j] = (char) temp; //turns the ASCII back to a character

                position = position - 1; //moves the position one space back in the key. resetting back to the end if it goes past the first value

                if (position < 1){
                    position = keyVals.length;
                }

            }

        }

        System.out.println("Your secret message is "); //prints out the secret message from the character Array
        for (char c : messageAsChar) {
            System.out.print(c);
        }
        System.out.println("\n");

    }

    /**
     * Main looping function of the program, where all other methods are called from. Loop to allow users to make multiple actions
     * Runs until user chooses to end the process.
     */

    public void runSuite()  {

        boolean runLoop = true; //boolean to allow while loop to run
        int userChoice = 0; //integer to store the user's choice of action

        String keyFilePath = "/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/keyFiles/"; //strings to hold the exact file path of the two file folders, as exactly specified on the machine
        String messageFilePath ="/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/messages/"; //learned the hard way that LINUX filepaths arent the same as windows

        System.out.println("Welcome to the encoding suite, what would you like to do today? You can create key files, message files, and decode messages"); //welcoming message

        while (runLoop){ //main while loop for all functionality

            System.out.println("Press 1 to create a key file, Press 2 to create a message file, and press 3 to decode a message file, and 4 to quit"); //gives users instructions and gets their input from the terminal
            userChoice = getValidIntInput();

            if (userChoice == 4){ //if its 4, exits the loop
                runLoop = false;
            }

            else if(userChoice == 1){ //if 1, begins the keyFile creation code

                int keyLength = 0; //variable to hold user's chosen key length
                String filename = ""; //varaible to hold user's chosen filename

                System.out.println("Please enter a number for how long the key should be, remember, longer is better!"); //gives instructions to user to enter a length of the key they want

                keyLength = getValidIntInput(); //gets user's input, checks for validity

                System.out.println("Please enter the name you would like the file to have, do NOT include any extension/filetype"); //gives users instructions to enter file name, with the stipulation of no punctuation

                filename = getValidStringInput(); //gets user's input, checks for validity

                filename = filename + ".txt"; //appends the .txt filename to the user's chose name

                System.out.println("Your file is called: " + filename); //informs user of what their file is called

                createKeyFile(keyLength, filename); //creates the keyFile using their desired keylength and filename


            }

            else if(userChoice == 2){ //if 2, begins executing the message code

                BufferedReader reader; //reader variable used to read from the keyFile txt

                String filename = ""; //string to hold the user's chosen filename for their keyFile that will be used to encode

                String messageFileName = ""; //string to hold the user's choice for their messageFile's name

                System.out.println("Please enter the name of the key file you would like to use, do NOT include the .txt at the end"); //gives users instructions to enter the filename of their keyFile they want to use

                filename = getValidStringInput(); //gets the valid string input

                filename = keyFilePath + filename + ".txt"; //adds the .txt to the filename along with the correct filepath to the keyFiles folder

                System.out.println("Please enter the name for the message file you are creating, do NOT include the .txt at the end"); //asks users to enter their chosen name for their message file with guidance

                messageFileName = getValidStringInput(); //gets the user's valid file name input


                try{

                    reader = new BufferedReader(new FileReader(filename)); //creates a BufferedReader object to read from the
                    String line1 = reader.readLine(); //gets the first line of the txt, which is the position of where to begin encoding
                    int start = Integer.parseInt(line1); //turns that string into a usable integer

                    String [] keyValueString = reader.readLine().split(","); //gets the second line from the file as an array of Strings, each character(s) being the number from the key

                    int [] keyValues = new int[keyValueString.length]; //creates an integer array to hold the keyValues

                    for (int i = 0; i < keyValueString.length; i++){ //turns the string representation of the keyvalues back into integers and stores them in an arry
                        keyValues[i] = Integer.parseInt(keyValueString[i]);
                    }

                    System.out.println("Please enter what message you would like to encrypt, make sure to not use any punctuation"); //gets the user's choice for message for what they want to encode

                    String message = getValidStringInput(); //get's their input, once again without punctuation

                    message = message.toUpperCase(); //converts the message to all upper case to make ASCII conversion work

                    String encodedMessage = encode(message, filename, keyValues, start); //gets the encoded form of the message

                    createMessageFile(start, encodedMessage, messageFileName); //creates the new message File


                }catch(IOException ioe){ //if an IO exception occured during execution

                    System.out.println("Error encountered finding file to get key from"); //returns that error to the user, saying getting the key file did not work
                    System.out.println("Error Message: " + ioe.getMessage());
                }




            }

            else if(userChoice == 3){ //if 3, begins the decoding code

                BufferedReader keyReader; //creates two Buffered Readers to read from each specific file
                BufferedReader messageReader;

                String keyFilename = ""; //gets the name of the keyFile being used to decode

                System.out.println("Please enter the name of the key file you would like to use, do NOT include the .txt at the end");

                keyFilename = getValidStringInput(); //gets the user's input after instructions, using validation

                keyFilename = keyFilePath + keyFilename + ".txt"; //adds the file path and .txt to the filename

                String messageFileName = ""; //holds the name of the messageFIle being used to decode

                System.out.println("Please enter the name of the message file you would like to use, do NOT include the .txt at the end");

                messageFileName = getValidStringInput(); //gets a valid user input after instructions

                messageFileName = messageFilePath + messageFileName + ".txt"; //appends the filepath and the .txt extension

                try{

                    keyReader = new BufferedReader(new FileReader(keyFilename)); //trys to open both files being user
                    messageReader = new BufferedReader(new FileReader(messageFileName));

                    String startAsString = keyReader.readLine(); //gets the new starting position to use for decoding
                    int start = Integer.parseInt(startAsString); //converts that start possition to an integer

                    String [] keyValueString = keyReader.readLine().split(",");//gets the keyvalues from the keyFile.txt and separates each value by the commas used into individual numbers
                    int [] keyValues = new int[keyValueString.length];  //creates an integer array to store the keyvals

                    for (int i = 0; i < keyValueString.length; i++){ //for every string chunk in the String array, converts the string to an intger and saves it in the keyVals array

                        keyValues[i] = Integer.parseInt(keyValueString[i]);
                    }

                    String startBeforeString = messageReader.readLine(); //gets the unecessary old starting point of the encrpytion
                    int startBeforeEncoding = Integer.parseInt(startBeforeString); //turns it into an intger

                    String encodedMessage = messageReader.readLine(); //gets the converted, encoded message

                    decode(encodedMessage, keyValues, start); //passes all the necessary info needed to decode


                }catch (IOException ioe){ //catches if there were any errors during the file opening or reading process
                    System.out.println("Error locating files");
                    System.out.println("Error Message: " + ioe.getMessage());
                }
            }

            else{ //if the user entered an integer of not the 4 valud values, tells them to enter something correctly
                System.out.println("Please make sure to enter a valid choice that fits one of our options");
            }
        }
    }
}

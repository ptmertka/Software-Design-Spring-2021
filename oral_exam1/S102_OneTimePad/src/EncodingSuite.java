import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EncodingSuite {
    //try catch with file name, asusume in the folder, try catch with file name

    //buffer reader, file path, file name, returns error if the file path is incorrect

    //first read in the integer

    //regular expression, get by comma separated



    public void createKeyFile(int n, String whatToNameFile){
        KeyFile keyfile1 = new KeyFile(n, whatToNameFile);

        keyfile1.createFile();


    }

    public void createMessageFile(int n, String encodedMessage){

        MessageFile messageFile1 = new MessageFile(n,encodedMessage);

        messageFile1.createFile();

    }
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

    private String getValidStringInput(){
        String a = "...";

        Scanner scanner1 = new Scanner(System.in);

        while((Pattern.matches("[\\p{IsPunctuation}\\p{Punct}]", a))){
            a = scanner1.next();

            if((Pattern.matches("[\\p{IsPunctuation}\\p{Punct}]", a))){
                System.out.println("Please enter a string without any punctuation");
            }

        }

        return a;
    }

    private String encode(String message, String filepath, int[] keyVals, int start){

    }

    private void decode(String encodedMessage, int[] keyVals, int start){

    }

    public void runSuite() throws FileNotFoundException {
        boolean runLoop = true;
        int userChoice = 0;

        String keyFilePath = "ptmertka_swd\\oral_exam1\\S102_OneTimePad\\keyFiles\\";
        String messageFilePath = "ptmertka_swd\\oral_exam1\\S102_OneTimePade\\messages\\";

        System.out.println("Welcome to the encoding suite, what would you like to do today? You can create key files, message files, and decode messages");
        while (runLoop){
            System.out.println("Press 1 to create a key file, Press 2 to create a message file, and press 3 to decode a message file, and 4 to quit");
            userChoice = getValidIntInput();
            if (userChoice == 4){
                runLoop = false;
            }

            else if(userChoice == 1){
                int keyLength = 0;
                String filename = "";

                System.out.println("Please enter a number for how long the key should be, remember, longer is better!");

                keyLength = getValidIntInput();

                System.out.println("Please enter the name you would like the file to have, do NOT include any extension/filetype");

                filename = getValidStringInput();

                filename = filename+ ".txt";

                createKeyFile(keyLength, filename);


            }

            else if(userChoice == 2){

                BufferedReader reader;

                String filename = "";

                System.out.println("Please enter the name of the key file you would like to use, do NOT include the .txt at the end");

                filename = getValidStringInput();

                filename = keyFilePath + filename + ".txt";

                try{
                    reader = new BufferedReader(new FileReader(filename));
                    String line1 = reader.readLine();
                    int start = Integer.parseInt(line1);

                    String [] keyValueString = reader.readLine().split(",");
                    int [] keyValues = new int[keyValueString.length];

                    for (int i = 0; i < keyValueString.length; i++){
                        keyValues[i] = Integer.parseInt(keyValueString[i]);
                    }

                    System.out.println("Please enter what message you would like to encrypt, make sure to not use any punctuation");

                    String message = getValidStringInput();

                    message = message.toUpperCase();

                    String encodedMessage = encode(message, filename, keyValues, start);

                    createMessageFile(start, encodedMessage);


                }catch(IOException ioe){

                    System.out.println("Error encountered finding file to get key from");
                }




            }

            else if(userChoice == 3){

                BufferedReader keyReader;
                BufferedReader messageReader;

                String keyFilename = "";

                System.out.println("Please enter the name of the key file you would like to use, do NOT include the .txt at the end");

                keyFilename = getValidStringInput();

                keyFilename = keyFilePath + keyFilename + ".txt";

                String messageFileName = "";

                System.out.println("Please enter the name of the message file you would like to use, do NOT include the .txt at the end");

                messageFileName = getValidStringInput();

                messageFileName = messageFilePath + messageFileName + ".txt";

                try{
                    keyReader = new BufferedReader(new FileReader(keyFilename));
                    messageReader = new BufferedReader(new FileReader(messageFileName));

                    String startAsString = keyReader.readLine();
                    int start = Integer.parseInt(startAsString);

                    String [] keyValueString = keyReader.readLine().split(",");
                    int [] keyValues = new int[keyValueString.length];

                    for (int i = 0; i < keyValueString.length; i++){
                        keyValues[i] = Integer.parseInt(keyValueString[i]);
                    }

                    String startBeforeString = messageReader.readLine();
                    int startBeforeEncoding = Integer.parseInt(startBeforeString);

                    String encodedMessage = messageReader.readLine();

                    decode(encodedMessage, keyValues, start);


                }catch (IOException ioe){
                    System.out.println("Error locating files");
                }
            }
            else{
                System.out.println("Please make sure to enter a valid choice that fits one of our options");
            }
        }
    }
}

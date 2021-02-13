import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class MessageFile
 * Creates a message file of an encoded message using a key. Holds relevant values like the number of message files created, the starting point of encryption, and the
 * encoded message once it is converted
 * @author Peter Mertka
 * @version 2/8/2021
 */
public class MessageFile {

    /**
     * Integer: the value position of where in the key that encryption began
     */
    private int start;

    /**
     * String: the chosen filename for the message file as chosen by the user
     */
    private String filename;
    /**
     * String: holds the text of the message after it has been encoded, message is chosen by the user
     */
    private String encodedMessage;

    /**
     * Class Constructor
     * Creates a new MessageFile Object, taking in a starting point of where encrpytion began, and the message after it has been encrypted
     * @param n Integer: The start position of the key when it began encoding
     * @param message String: the encoded message of the file being stored after encryption
     * @param filenameChosen String: The file name as chosen by the user for the message File
     */
    public MessageFile(int n, String message, String filenameChosen){
        start = n;
        encodedMessage = message;
        filename = filenameChosen;
    }

    /**
     * Creates the message file using the objects parameters, saving it to a specific message folder in the module
     */
    public void createFile(){

        try { //Try to catch a IOE exception


            File file = new File("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad_Hard/messages/" + filename +".txt"); //will contain.txt already
            //creates a proper txt file in the messages folder
            file.createNewFile();//tests that the program can open, returns an expcetion if not

            FileWriter writer = new FileWriter(file); //opens the txt file in order that it can be written to

            writer.write(Integer.toString(start) + "\n");  //writes the starting position of the key before encrpytion to the txt
            writer.write(encodedMessage); //writes the encoded message to the txt file
            writer.flush(); //flushes out the buffer, so all of the text is sent to the file, but doesn't close the writing stream
            writer.close(); //closes the buffer stream and the file

            System.out.println("Your file is called: " +  filename +".txt"); //tells the user what their message file is called


        } catch (IOException ioe){ //if an IO exception occurs, catches the error and displays the error text
            System.out.println("Trouble reading and/or writing your file, please try again");
            System.out.println("Error Message: " + ioe.getMessage());
        }


    }

}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class MessageFile
 * Creates a message file of an encoded message using a key. Holds relevant values like the number of message files created, the starting point of encryption, and the
 * encoded message once it is converted
 */
public class MessageFile {

    /**
     * Integer: the value position of where in the key that encryption began
     */
    private int start;

    /**
     * Static intger: represents how many message files are created. Since the user can create multiple message files, allows for them to have different names
     */
    private static int fileNum = 1;

    /**
     * String: holds the text of the message after it has been encoded, message is chosen by the user
     */
    private String encodedMessage;

    /**
     * Class Constructor
     * Creates a new MessageFile Object, taking in a starting point of where encrpytion began, and the message after it has been encrypted
     * @param n
     * @param message
     */
    public MessageFile(int n, String message){
        start = n;
        encodedMessage = message;
    }

    /**
     * Creates the message file using the objects parameters, saving it to a specific message folder in the module
     */
    public void createFile(){

        try { //Try to catch a IOE exception


            File file = new File("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/messages/" + "message" + Integer.toString(fileNum)+".txt"); //will contain.txt already

            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(Integer.toString(start) + "\n");
            writer.write(encodedMessage);
            writer.flush();
            writer.close();

            System.out.println("Your file is called: " +  "message" + Integer.toString(fileNum)+".txt");

            fileNum = fileNum + 1;
        } catch (IOException ioe){
            System.out.println("Trouble reading and/or writing your file, please try again");
            System.out.println("Error Message: " + ioe.getMessage());
        }


    }

}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MessageFile {

    private int start;

    private static int fileNum = 1;

    private String encodedMessage;

    public MessageFile(int n, String message){
        start = n;
        encodedMessage = message;
    }
    public void createFile(){
        try {
            File dir = new File("ptmertka_swd\\oral_exam1\\S102_OneTimePad\\messages");// not working to write a

            File file = new File(dir, "message" + Integer.toString(fileNum)+".txt"); //will contain.txt already
            fileNum = fileNum + 1;
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(Integer.toString(start) + "\n");
            writer.write(encodedMessage);
            writer.flush();
            writer.close();

            System.out.println("Your key file is stored at ptmertka_swd\\oral_exam1\\S102_OneTimePad\\messages");
        } catch (IOException ioe){
            System.out.println("Trouble reading and/or writing your file, please try again");
            System.out.println("Error Message: " + ioe.getMessage());
        }


    }

    public String getEncodedMessage() {
        return encodedMessage;
    }
}

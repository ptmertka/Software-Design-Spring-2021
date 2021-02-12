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
            //File dir = new File("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjectsptmertka_swd/oral_exam1/S102_OneTimePad/messages/");// not working to write a

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

    public String getEncodedMessage() {
        return encodedMessage;
    }
}

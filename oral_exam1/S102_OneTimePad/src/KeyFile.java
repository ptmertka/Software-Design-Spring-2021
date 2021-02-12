
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

    private int lengthKey;

    private int[] key;

    private int start;

    private String filename;

    Random random1 = new Random();

    public KeyFile(int n, String whatToName){
        lengthKey = n;
        filename = whatToName;

    }

    public void generateKey(){
        key = new int[lengthKey];
        start = random1.nextInt(lengthKey) + 1;
        for(int i = 0; i< lengthKey; i++){
            key[i] = random1.nextInt(26) + 1;
        }
    }
    private String arrayToString(){
        String convertedString = "";

        for(int i = 0; i < lengthKey - 1; i++){
            convertedString = convertedString + Integer.toString(key[i]) + "," ;
        }

        convertedString = convertedString + Integer.toString(key[lengthKey-1]);

        return convertedString;
    }

    public void createFile() {
        try {
            //File dir = new File("ptmertka_swd\\oral_exam1\\S102_OneTimePad\\keyFiles");// not working to write a
            System.out.println("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/keyFiles/" + filename);
            File file = new File("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/keyFiles/" + filename); //will contain.txt already

            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(Integer.toString(start) + "\n");
            writer.write(arrayToString());
            writer.flush();
            writer.close();

            System.out.println("/nfs/s-l011/local/vol02/p/ptmertka/IdeaProjects/ptmertka_swd/oral_exam1/S102_OneTimePad/keyFiles/" + filename);
        } catch (IOException ioe){
            System.out.println("Trouble reading and/or writing your file, please try again");
            System.out.println("Error Message: " + ioe.getMessage());
        }


    }











}

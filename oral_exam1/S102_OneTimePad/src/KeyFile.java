
import java.lang.reflect.Array;
import java.util.Random;

/**
 * Class keyFile
 * Meant to hold relevant information regarding a key file, including postion, key itself, and filename. Allows for the creation of key file
 * @author Peter Mertka
 * @version 2/9/2021
 */

public class KeyFile {

    private int lengthKey;

    private int[] key;

    private String filename;

    Random random1 = new Random();

    public KeyFile(int n, String whatToName){
        lengthKey = n;
        filename = whatToName;

    }

    private void generateKey(int n){
        key = new int[n];
        for(int i = 0; i<n; i++){
            key[i] = random1.nextInt(26) + 1;
        }
    }



}

/**
 * Encoding Suite Handler
 * Home of the main function of the code, allows the while loop to begin with a new encoding suite object
 * @author Peter Mertka
 * @version 2/11/2021
 */
public class EncodingSuiteHandler {
    /**
     * Main function, creates a new EncodingSuite variable and runs the main looping method
     * @param args String Array: would be used if starting parameters were used, remains blank otherwise
     */
    public static void main(String args[]){
        EncodingSuite programRunner = new EncodingSuite();

        programRunner.runSuite();
    }
}

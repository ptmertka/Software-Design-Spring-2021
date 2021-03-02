import javax.swing.JFrame;

/**
 * Runner Class for the Morse Code Frame, creates the frame itself, making it large, visiple, and exit upon the closing of the window
 * @author Peter Mertka
 * @version 2/28/2021
 */
public class MorseCodeTest {

    /**
     * Main function, creates the MorseCodeFrame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        MorseCodeFrame morseCodeFrame = new MorseCodeFrame(); //makes a new MorseCodeFrame Object
        morseCodeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows it to exit the program if the window closes
        morseCodeFrame.setSize(2000, 1000); //makes the pixel set to fill the screen mostly
        morseCodeFrame.setVisible(true); //makes the screen visible
    }
}

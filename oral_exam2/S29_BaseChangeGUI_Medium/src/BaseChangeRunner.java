import javax.swing.*;

/**
 * Class BaseChangeRunner
 * Creates the actual frame of the project, allows it to close the program upon exit of the window
 * sets the size of the frame and makes it visible.
 */
public class BaseChangeRunner {
    /**
     * Main function, creates the BaseChangerFrame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        BaseChangeFrame baseChangeFrame = new BaseChangeFrame(); //makes a new MorseCodeFrame Object
        baseChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows it to exit the program if the window closes
        baseChangeFrame.setSize(650, 650); //makes the pixel set to fill the screen mostly
        baseChangeFrame.setVisible(true); //makes the screen visible
    }
}


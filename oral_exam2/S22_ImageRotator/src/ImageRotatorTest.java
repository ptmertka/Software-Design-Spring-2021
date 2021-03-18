import javax.swing.*;

/**
 * Class ImageRotatorTest: class that actually calls and implements the GUI, making it visible and interactable
 * As well as able to close when exited, stoping the program.
 * @author Peter Mertka
 * @version 3/17/2021
 */
public class ImageRotatorTest {
    /**
     * Main function, creates the ImageRotatorFrame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        ImageRotationFrame imageRotationFrame = new ImageRotationFrame();
        imageRotationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageRotationFrame.setSize(500,500);
        imageRotationFrame.setVisible(true);
    }
}


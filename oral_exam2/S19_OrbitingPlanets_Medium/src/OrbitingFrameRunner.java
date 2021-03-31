import javax.swing.*;

/**
 * Runner class for the Frame, creating it and making it usuable. Holds main function
 */
public class OrbitingFrameRunner {
    /**
     * Main function, creates the ImageRotatorFrame itself, making it visible and usuable
     * @param args Optional list of string arguments not used in this program
     */
    public static void main(String args[]){
        OrbitingFrame orbitingFrame = new OrbitingFrame(); //creates a new frame, allows the program to end on close of the screen,
        orbitingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the window size to 900 x 900 and makes it visible
        orbitingFrame.setSize(900,900);
        orbitingFrame.setVisible(true);

    }
}

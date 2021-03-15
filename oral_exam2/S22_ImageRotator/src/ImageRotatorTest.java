import javax.swing.*;

public class ImageRotatorTest {
    /**
     * Main function, creates the MorseCodeFrame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        ImageRotationFrame imageRotationFrame = new ImageRotationFrame();
        imageRotationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageRotationFrame.setSize(500,500);
        imageRotationFrame.setVisible(true);
    }
}


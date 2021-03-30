import javax.swing.*;

public class OrbitingFrameRunner {
    /**
     * Main function, creates the ImageRotatorFrame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        OrbitingFrame orbitingFrame = new OrbitingFrame();
        orbitingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orbitingFrame.setSize(900,900);
        orbitingFrame.setVisible(true);

    }
}

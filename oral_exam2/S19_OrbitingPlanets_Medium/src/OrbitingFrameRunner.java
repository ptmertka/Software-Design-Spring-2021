import javax.swing.*;

public class OrbitingFrameRunner {
    /**
     * Main function, creates the Orbitting Frame itself, making it visible and usuable
     * @param args
     */
    public static void main(String args[]){
        OrbitingFrame orbitingFrame = new OrbitingFrame();
        orbitingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orbitingFrame.setSize(1005,1005);
        orbitingFrame.setVisible(true);

    }
}

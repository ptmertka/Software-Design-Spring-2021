import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OrbitingFrame extends JFrame {

    private final JPanel space;

    private final Timer timer;

    private ArrayList<Planet> planentList = new ArrayList<Planet>();

    public OrbitingFrame(){
        super("Orbiting Planets");

        space = new JPanel();


    }
    private class mouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
    private class SpacePanel extends JPanel{

    }

}



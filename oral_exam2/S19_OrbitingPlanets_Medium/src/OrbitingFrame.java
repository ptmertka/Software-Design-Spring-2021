import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class OrbitingFrame extends JFrame {

    private final SpacePanel space;

    private final Timer timer;

    private ArrayList<Planet> planentList = new ArrayList<Planet>();

    ExecutorService executorService;

    public OrbitingFrame(){
        super("Orbiting Planets");

        setLayout(new FlowLayout());

        space = new SpacePanel();
        space.setPreferredSize(new Dimension(1000,1000));

        executorService = Executors.newCachedThreadPool();

        add(space);

        PlanetMouseListener mouseListener = new PlanetMouseListener();

        space.addMouseListener(mouseListener);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                space.repaint();
            }
        });

        timer.start();





    }
    private class PlanetMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            if(planentList.size() < 8) {

                Planet planet = new Planet(250, 500, 10);
                planentList.add(planet);
                executorService.execute(planet);


            }
            else if(planentList.size() == 8){
                executorService.shutdown();
            }
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

        @Override
        public void paintComponent(Graphics g) {


            super.paintComponent(g);

            System.out.println("Paint Components was called");
            g.setColor(Color.yellow);
            g.drawOval((getWidth()/2)-25,(getHeight()/2)-25, 50,50);
            g.fillOval((getWidth()/2)-25,(getHeight()/2)-25, 50,50);

            g.setColor(Color.black);
            g.drawOval((getWidth()/2)-250,(getHeight()/2)-250, 500,500);

            for (Planet planet : planentList){
                g.setColor(Color.orange);
                g.drawOval(planet.getX()-planet.getRadius(), planet.getY()-planet.getRadius(), planet.getRadius()*2, planet.getRadius()*2);
                g.fillOval(planet.getX()-planet.getRadius(), planet.getY()-planet.getRadius(), planet.getRadius()*2, planet.getRadius()*2);
            }



        }
    }

}



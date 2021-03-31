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

/**
 * Class OrbitingFrame Creates the frame that allows for the
 * creation of the frame and panel for the orbiting planets
 * also contains 2 private classes to help with program implementation
 */
public class OrbitingFrame extends JFrame {

    /**
     * SpacePanel: Instance of a custom class that extends JPanel, has overwritten paintComponent
     * Where the planets and orbits are drawn
     */
    private final SpacePanel space;

    /**
     * Timer: Timer used to fire off events to repaint the screen as a way to get the
     * results of the threads
     */
    private final Timer timer;

    /**
     * ArrayList of Planets: A list to store the Planet threads for when the panel is repainted
     */
    private ArrayList<Planet> planentList = new ArrayList<Planet>();

    /**
     * List of colors so that each planet is a different color
     */
    private Color[] colorList = {Color.lightGray,Color.pink, Color.green, Color.red, Color.orange,
    Color.yellow, Color.cyan, Color.blue};

    /**
     * List of radii (ints) so that each planet is a different size
     */
    private int[] radiusList = {10, 15, 25, 20, 50, 45, 35, 30};

    /**
     * List of orbit radii (ints) so that each orbit is larger than the next
     */
    private int[] orbitList = {100, 125, 150, 175, 200, 225, 250, 275};

    /**
     * Executor Service: Service used to handle the running of the threads
     */
    ExecutorService executorService;

    /**
     * Class Constuctor
     * Initialises the components of the frame
     */
    public OrbitingFrame(){
        super("Orbiting Planets"); //calls super class constructor to name the frame

        setLayout(new FlowLayout()); //sets the layout to flow

        space = new SpacePanel(); //creates a new instance of my custom JPanel class
        space.setPreferredSize(new Dimension(800,800)); //sets it so that its locked at 800x800 in size

        executorService = Executors.newCachedThreadPool(); //creates a new cached thread pool to handle the running of the tasks

        add(space); //adds the panel to the frame

        PlanetMouseListener mouseListener = new PlanetMouseListener(); //creates a new mouseListener using private class

        space.addMouseListener(mouseListener); //adds mouseListener to the panel so that clicks are handled

        timer = new Timer(10, new ActionListener() { //creates a new timer that fires off events every 10 ms, repaint the screen to update the planets
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                space.repaint();
            }
        });

        timer.start(); //starts the timer to begin firing events





    }

    /**
     * Private MouseListener class handles the user's clicks, adding the implementation of when a user clicks on the screen
     */
    private class PlanetMouseListener implements MouseListener {
        /**
         * Catches user clicks and creates a new planet and adds it to the Executor Service
         * @param mouseEvent MouseEvent: a caught event fired by the user clicking the mouse
         */
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            //if the list of planets is less than 8 planets
            if(planentList.size() < 8) {
                int index = planentList.size(); //gets the number planet that it is (0 =1st planet, 1 = 2nd planent, etc
                Planet planet = new Planet(400, 400, radiusList[index], orbitList[index]); //creates a new unique planet representing our solar system
                //getting radius of planet and orbit from lists
                planentList.add(planet); //adds the new planet to the list of planets
                executorService.execute(planet); //adds the planet runnable to the executor service


            } //once the number of planets reaches the max, shutsdown the executor service from accepting new Runnables
            else if(planentList.size() == 8){
                executorService.shutdown();
            }
        }
        //other methods present but unused
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

    /**
     * Private, custom class that extends JPanel to have a custom implementation of paintComponent
     */
    private class SpacePanel extends JPanel{
        /**
         * Called with repaint by the timer, creates the orbits, sun, and planets every time its called
         * @param g Graphics object to represent what is being drawn on the Jpanel
         */
        @Override
        public void paintComponent(Graphics g) {


            super.paintComponent(g); //calls superclass function to clear screen before drawing

            g.setColor(Color.yellow); //draws a yellow sun at 400,300
            g.drawOval((getWidth()/2)-25,300-25, 50,50);
            g.fillOval((getWidth()/2)-25,300-25, 50,50);


            int index = 0; //index used to get info from lists, needed due to for each loop
            for (Planet planet : planentList){ //for every planet that has been created

                g.setColor(Color.black); //draws the orbit for the planet
                g.drawOval((getWidth()/2)-orbitList[index], 300-orbitList[index], orbitList[index]*2,orbitList[index]*2);

                g.setColor(colorList[index]);//chooses the color based on the planet, ie red for mars, green for earth, and draws it on the screen, getting its coordinates from the object itself
                g.drawOval(planet.getX()-planet.getRadius(), planet.getY()-planet.getRadius(), planet.getRadius()*2, planet.getRadius()*2);
                g.fillOval(planet.getX()-planet.getRadius(), planet.getY()-planet.getRadius(), planet.getRadius()*2, planet.getRadius()*2);
                index++; //increments index to represent the next planet
            }



        }
    }

}



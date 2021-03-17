import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class ImageRotationFrame
 * Extends JFrame in order to create a frame that holds the rest of GUI for the image rotator
 * Allows for users to rotate an image a specified number of degrees, while also being able to change speed or spin continously
 * @author Peter Mertka
 * @version 3/16/2021
 */
public class ImageRotationFrame extends JFrame {

    /**
     * JPanel: Holds the image that will be rotated, adjusts in size to the size of the image
     */
    private final JPanel imagePanel;

    /**
     * SpinnerModel: contains the bounds, range, step, and initial condition for the degree spinner
     */
    private final SpinnerModel degreeModel;

    /**
     * SpinnerModel: contains the bounds, range, step, and initial condition for the speed spinner
     */
    private final SpinnerModel speedModel;

    /**
     * JSpinner: the actual spinner that allows users to choose a degrees to rotate
     */
    private final JSpinner degreeSpinner;

    /**
     * JSpnner: the actual spinner that allows users to choose a speed to rotate at
     */
    private final JSpinner speedSpinner;

    /**
     * JLabel: stores the text to label the degree spinner
     */
    private final JLabel degreeLabel;

    /**
     * JLabel: stores the text to label the speed spinner
     */
    private final JLabel speedLabel;

    /**
     * JButton: button that the user presses to begin the rotation
     */
    private final JButton startButton;

    /**
     * JCheckBox: Check box that specifies if the user wants the image to spin continously or not
     */
    private final JCheckBox spinBox;

    /**
     * ImageLabel: Instance of custome ImageLabel object that allows for image to be saved as an Icon and rotated
     */
    private final ImageLabel imageLabel;

    /**
     * int: The number of degrees the image has been rotated
     */
    private int degrees = 0;

    /**
     * Timer: timer that helps to specify how frequently the image rotates by firing actionEvents at certain rates
     */
    private Timer timer;


    /**
     * Constructor for the ImageRotationFrame Class
     * initializes all the components of the Frame, adding them to the frame, as well as adding the listeners
     */
    public ImageRotationFrame(){
        super("Rotate an Image!"); //calls super constructor to name the frame

        setLayout(new FlowLayout()); //sets layout to flow so that all elements are easily added

        imagePanel = new JPanel(); //basic declaration of Panel

        degreeModel = new SpinnerNumberModel(90,1,360,1); //sets bounds of degree spinner, goes from 1-360
        //step value of 1 degree, default of 90

        speedModel = new SpinnerNumberModel(1,1,20,1);//sets the bounds of the speed spinner, goes from 1 to 20,
        //step value of 1 degree, default of 1
        degreeSpinner = new JSpinner(degreeModel); //attaches the degree model to the degree spinner

        speedSpinner = new JSpinner(speedModel); //attaches the speed model to the speed spinner

        degreeLabel = new JLabel("Degrees to Rotate"); //these two labels add text to go along with spinners

        speedLabel = new JLabel("Speed of Rotation");

        startButton = new JButton("Start Rotation"); //creates button with start message

        spinBox = new JCheckBox("Spin Continuously"); //creates the box to check to spin continously

        imageLabel = new ImageLabel(); //creates a new Image label, an instance of my custom class that extends JLabel

        add(imagePanel); //adds all the components to the frame
        add(degreeLabel);
        add(degreeSpinner);
        add(speedLabel);
        add(speedSpinner);
        add(startButton);
        add(spinBox);

        imagePanel.add(imageLabel); //adds the image to the JPanel, as the image is going to be attached to the label in the panel

        ButtonListener buttonListener = new ButtonListener(); //creates a new Button Listener
        startButton.addActionListener(buttonListener); //adds that listener to the Button so that it seeks out clicks of the button






    }

    /**
     * Class ButtonListener
     * Extends ActionListener, implements the functionality of the the button being pressed, allowing the image to spin
     */
    private class ButtonListener implements ActionListener{

        /**
         * Seeks out and catches actionEvents (buttonClicks) and handles them
         * @param actionEvent ActionEvent: an event triggered by the button being pressed
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int speed = 21 - (int) speedSpinner.getValue(); //gets the speed of the spinner, setting it to 21 minus its value
            //as that way, when speed is at max, speed represents 1 event/speed milliseconds

            // if the user doesn't want to spin continously (check box not selected)
            if(spinBox.isSelected()==false) {
                int degreesToSpin = (int) degreeSpinner.getValue(); //gets the degree value of the spinner
                int degreesOnClick = degrees; //gets the current value of degrees before the timer begins


                timer = new Timer(speed, new ActionListener() { //creates a new timer that fires off events at every speed milliseconds
                    @Override //new annoymous action listener is attached to handle the events fired by the timer
                    /**
                     *Performs an action every time the timer event actionEvent is fired, in this case
                     * the action is to increment degrees by 1 degree, so that it rotates 1 degree per event and
                     * repaints the image
                     */
                    public void actionPerformed(ActionEvent actionEvent) {
                        degrees = degrees + 1; //increments the degree by 1
                        imageLabel.repaint(); //repaints the image so that it is rotated
                        if(degrees > degreesToSpin + degreesOnClick){ //if the image has rotated past what the user specified
                            timer.stop(); //stops the timer so events stop firing
                        }
                    }
                });

                timer.start(); //starts the timer after its created
            }
            else{ //if the checkBox is clicked, the user wants to spin the image continously
                //creates a new timer and annyomous ActionListener

                timer = new Timer(speed, new ActionListener() {
                    @Override
                    /**
                     *Performs an action every time the timer event actionEvent is fired, in this case
                     * the action is to increment degrees by 1 degree, so that it rotates 1 degree per event and
                     * repaints the image
                     */
                    public void actionPerformed(ActionEvent actionEvent) {
                        degrees = degrees + 1; //increments degrees by 1 and repaints the image so it rotates
                        imageLabel.repaint();
                        if(spinBox.isSelected()==false){ //stops the timer if the user unchecks the box
                            timer.stop();
                        }
                    }
                });

                timer.start(); //starts the timer
            }

        }

    }

    /**
     * ImageLabel
     * Class that extends JLabel in order to override PaintCompent so that every time it is redrawn, it is rotated
     * by 1 degreee, as before each time repaint is called, degrees is incremented by 1
     */
    private class ImageLabel extends JLabel{
        /**
         * BufferedImage: Used to load the file in initially through ImageIO
         */
        BufferedImage image;

        /**
         * ImageIcon: used to store the images as a mutable/drawable icon so that it can also be attached to the label
         * so that it may appear on the screen
         */
        ImageIcon imageIcon;

        /**
         * Constructor for the ImageLabel class
         * Creates the bufferedImage and the ImageIcon for the label, using my chosen image
         */
        public ImageLabel(){
            try {
                image = ImageIO.read(new File("oral_exam2/S22_ImageRotator/Images/sonic.jpeg"));
            }
            catch(IOException Ioe){

            }
            imageIcon = new ImageIcon(image);

            this.setIcon(imageIcon);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.toRadians(degrees),width/2, height/2);
            g2d.drawImage(imageIcon.getImage(), 0, 0, this);
            g2d.dispose();



        }
    }

}

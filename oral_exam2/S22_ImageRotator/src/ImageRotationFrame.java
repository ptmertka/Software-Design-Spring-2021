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



    public ImageRotationFrame(){
        super("Rotate an Image!");

        setLayout(new FlowLayout());

        imagePanel = new JPanel();

        degreeModel = new SpinnerNumberModel(90,1,360,1);

        speedModel = new SpinnerNumberModel(1,1,20,1);

        degreeSpinner = new JSpinner(degreeModel);

        speedSpinner = new JSpinner(speedModel);

        degreeLabel = new JLabel("Degrees to Rotate");

        speedLabel = new JLabel("Speed of Rotation");

        startButton = new JButton("Start Rotation");

        spinBox = new JCheckBox("Spin Continuously");

        imageLabel = new ImageLabel();

        add(imagePanel);
        add(degreeLabel);
        add(degreeSpinner);
        add(speedLabel);
        add(speedSpinner);
        add(startButton);
        add(spinBox);

        imagePanel.add(imageLabel);

        ButtonListener buttonListener = new ButtonListener();
        startButton.addActionListener(buttonListener);






    }


    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int speed = 21 - (int) speedSpinner.getValue();


            if(spinBox.isSelected()==false) {
                int degreesToSpin = (int) degreeSpinner.getValue();
                int degreesOnClick = degrees;


                timer = new Timer(speed, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        degrees = degrees + 1;
                        imageLabel.repaint();
                        if(degrees > degreesToSpin + degreesOnClick){
                            timer.stop();
                        }
                    }
                });

                timer.start();
            }
            else{

                timer = new Timer(speed, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        degrees = degrees + 1;
                        imageLabel.repaint();
                        if(spinBox.isSelected()==false){
                            timer.stop();
                        }
                    }
                });

                timer.start();
            }

        }

    }

    private class ImageLabel extends JLabel{
        BufferedImage image;

        ImageIcon imageIcon;


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

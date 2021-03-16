import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRotationFrame extends JFrame {

    private final JPanel imagePanel;

    private final SpinnerModel degreeModel;

    private final SpinnerModel speedModel;

    private final JSpinner degreeSpinner;

    private final JSpinner speedSpinner;

    private final JLabel degreeLabel;

    private final JLabel speedLabel;

    private final JButton startButton;

    private final JCheckBox spinBox;

    private final ImageLabel imageLabel;

    private int degrees = 0;

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

            int degreesToSpin = (int) degreeSpinner.getValue();
            int degreesOnClick = degrees;

            timer = new Timer(speed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    degrees = degrees + 1;
                    imageLabel.repaint();
                }
            });

            timer.start();

        }

    }

    private class ImageLabel extends JLabel{
        BufferedImage image;

        ImageIcon imageIcon;


        public ImageLabel(){
            try {
                image = ImageIO.read(new File("oral_exam2/S22_ImageRotator/Images/iowa.png"));
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

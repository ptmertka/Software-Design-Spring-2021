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

    private final JLabel imageLabel;

    private BufferedImage image = null;


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



        try {
            image = ImageIO.read(new File("oral_exam2/S22_ImageRotator/Images/iowa.png"));
        }
        catch(IOException Ioe){

        }

        imageLabel = new JLabel(new ImageIcon(image));

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

        }

    }

}

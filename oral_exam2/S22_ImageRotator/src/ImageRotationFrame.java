import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

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

        add(imagePanel);
        add(degreeLabel);
        add(degreeSpinner);
        add(speedLabel);
        add(speedSpinner);
        add(startButton);
        add(spinBox);




    }
}

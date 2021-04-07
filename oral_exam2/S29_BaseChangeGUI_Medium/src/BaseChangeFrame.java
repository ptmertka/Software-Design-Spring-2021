import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class BaseChangeFrame
 * Creates the actual frame of the GUI, adding components, as well as handling the actual
 * conversion with the user of the BaseConverter class
 * @author Peter Mertka
 * @version 4/6/2021
 */
public class BaseChangeFrame extends JFrame {

    /**
     * JTextField: field where user can input number to be translated
     */
    private final JTextField inputField;

    /**
     * JTextField: field where the converted number or warning message will be output
     */
    private final JTextField outputField;

    /**
     * SpinnerModel: used to set the parameters of the inputBaseSpinner
     */
    private final SpinnerModel inputBaseModel;

    /**
     * SpinnerModel: used to set the parameters of the outputBaseSpinner
     */
    private final SpinnerModel outputBaseModel;

    /**
     * JSpinner: Spinner that allows users to choose any base from 2-32 as the base of their inputted number
     */
    private final JSpinner inputBaseSpinner;

    /**
     * JSpinner: Spinner that determines the base of the outputted number after conversion
     */
    private final JSpinner outputBaseSpinner;

    /**
     * JLabel: Used to give text to the JSpinner for the inputBase
     */
    private final JLabel inputLabel;

    /**
     * JLabel: Used to give text to the Jspinner for the outputBase
     */
    private final JLabel outputLabel;

    /**
     * JButton: Button that allows the user to tell the converter to process their input and validate their number before conversion
     */
    private final JButton actionButton;

    /**
     * Constructor, creates the actual GUI Components and adds them to the frame, as well as adding the
     * button listener to the button
     */
    public BaseChangeFrame(){
        super("Base Changer"); //super constructor of JFrame that names the frame

        setLayout(new FlowLayout()); //creates a new layout that allows all items to be added to the screen

        inputField = new JTextField("Enter your number to be converted"); //adds base text to the input field

        inputField.setEditable(true); //allows user to edit the field

        outputField = new JTextField("Converted number is output here, please enter your number in the correct base as chosen"); //sets default message for output text

        outputField.setEditable(false); //prevents users from editing the output field

        inputBaseModel = new SpinnerNumberModel(10,2,32,1); //creates ranges for input spinner

        inputBaseSpinner = new JSpinner(inputBaseModel); //adds model to input base spinner

        inputLabel = new JLabel("Base of Input"); //label for spinner

        outputBaseModel = new SpinnerNumberModel(2,2,32,1);//creates model for output spinner

        outputBaseSpinner = new JSpinner(outputBaseModel); //adds model to output base spinner

        outputLabel = new JLabel("Base of Output"); //label for spinner

        actionButton = new JButton("Convert Number"); //creates the button for conversion, adds text to appear on button

        ButtonListener buttonListener = new ButtonListener(); ///creates a new button listener, adds it to the button

        actionButton.addActionListener(buttonListener);

        add(inputField); //adds all the GUI components to the frame in the proper order
        add(inputBaseSpinner);
        add(inputLabel);
        add(outputBaseSpinner);
        add(outputLabel);
        add(actionButton);
        add(outputField);

    }

    /**
     * Private Class that handles events thrown by the button
     * Handles the actual conversion/input validation of the base changer
     * Updates the output text field
     */
    private class ButtonListener implements ActionListener{

        @Override
        /**
         * Handles the click events of teh button, creating the baseCoverter object,
         * and updating the outputTextField based on if number can be translated or not
         */
        public void actionPerformed(ActionEvent actionEvent) {
            String inputBase = inputBaseSpinner.getValue().toString(); //gets the values of the bases and numbers from the spinners and area
            int outputBase = (int) outputBaseSpinner.getValue();
            String number = inputField.getText();
            BaseConverter baseConverter = new BaseConverter(inputBase, outputBase, number); //creates a new base converter

            if ((int) inputBaseSpinner.getValue() == outputBase){
                outputField.setText(number); //if the bases equal, just translate the number over

            }else if(!baseConverter.checkIfValid()){ //if the number is not the correct form
                outputField.setText("Number inputted is in wrong form or contains an invalid character, please fix");
            }
            else{
                outputField.setText(baseConverter.translate());//gets the translated version of the number and outputs it
            }

        }
    }
}



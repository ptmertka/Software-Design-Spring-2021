import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CalculatorController {

    private CalculatorModel model = new CalculatorModel();

    private ArrayList<String> operators = new ArrayList<String>();


    @FXML
    private TextField displayArea;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button divide;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button multiply;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button minus;

    @FXML
    private Button zero;

    @FXML
    private Button clear;

    @FXML
    private Button equals;

    @FXML
    private Button plus;

    @FXML
    public void onPress(ActionEvent e){
        operators.add("X");
        operators.add("/");
        operators.add("-");
        operators.add("+");

        if(((Button)e.getSource()).getText().equals("=")){
            model.setSecondNum(Integer.parseInt(model.getCurrentText()));
            if(model.getOperator().equals("+")){
                model.setResult(model.getFirstNum()+model.getSecondNum());
                model.setCurrentText(model.getResult());
                displayArea.setText(model.getCurrentText());
            }
            else if (model.getOperator().equals("-")){
                model.setResult(model.getFirstNum()-model.getSecondNum());
                model.setCurrentText(model.getResult());
                displayArea.setText(model.getCurrentText());
            }
            else if (model.getOperator().equals("X")){
                model.setResult(model.getFirstNum()*model.getSecondNum());
                model.setCurrentText(model.getResult());
                displayArea.setText(model.getCurrentText());
            }
            else{
                model.setResult(model.getFirstNum()/model.getSecondNum());
                model.setCurrentText(model.getResult());
                displayArea.setText(model.getCurrentText());
            }
        }
        else if(((Button)e.getSource()).getText().equals("C")){
            model.setOperator("");
            model.setFirstNum(0);
            model.setSecondNum(0);
            model.setResult(0);
            model.setCurrentText("");
            displayArea.setText(model.getCurrentText());
        }
        else if (operators.contains(((Button)e.getSource()).getText()) ){
            model.setFirstNum(Integer.parseInt(model.getCurrentText()));
            model.setOperator(((Button)e.getSource()).getText());
            model.setCurrentText("");
            displayArea.setText(model.getCurrentText());
        }
        else{
            model.setCurrentText(model.getCurrentText() + ((Button)e.getSource()).getText());
            displayArea.setText(model.getCurrentText());
        }

    }

}
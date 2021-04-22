import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class MyTipController {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    
    private BigDecimal tipPercent = new BigDecimal(.15);    //15% default
    
    
    @FXML
    private Label percentLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipAmountTextField;

    @FXML
    private TextField totalAmountTextField;

    @FXML
    private Slider percentSlider;

    @FXML
    private void calculateButtonPressed(ActionEvent e) {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercent);
            BigDecimal total = amount.add(tip);

            tipAmountTextField.setText(currency.format(tip));
            totalAmountTextField.setText(currency.format(total));

        } catch (NumberFormatException ex) {

            amountTextField.setText("Enter Amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();     //puts cursor on amount field
        }
    }

    @FXML
    private void updateTip(){
        tipPercent = BigDecimal.valueOf(percentSlider.getValue() / 100.0);
        percentLabel.setText(percent.format(tipPercent));
    }

    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // listener for changes to tipPercentageSlider's value
        percentSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        tipPercent=
                                BigDecimal.valueOf(newValue.intValue() / 100.0);
                        percentLabel.setText(percent.format(tipPercent));
                    }
                }
        );
    }




}
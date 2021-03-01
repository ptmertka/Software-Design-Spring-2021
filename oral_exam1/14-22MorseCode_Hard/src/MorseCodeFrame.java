import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;

public class MorseCodeFrame extends JFrame {
    private final JTextArea englishTextField ;
    private final JTextArea morseCodeTextField ;
    private final JTextArea outputText;

    public MorseCodeFrame(){
        super("Morse Code Translator");

        setLayout(new FlowLayout());

        englishTextField = new JTextArea("Enter English Text Here", 10,20);
        morseCodeTextField = new JTextArea("Enter Morse Code Text here", 10,20);
        outputText = new JTextArea("Response will be displayed here", 10,20);

        outputText.setEditable(false);

        add(englishTextField);
        add(morseCodeTextField);
        add(outputText);

        EnglishTextListner handler = new EnglishTextListner();
        englishTextField.getDocument().addDocumentListener(handler);
        morseCodeTextField.getDocument().addDocumentListener(handler);




    }
    private class EnglishTextListner implements DocumentListener {


        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            if(documentEvent.getDocument() == englishTextField.getDocument()){
                morseCodeTextField.getDocument().removeDocumentListener();
                outputText.setText(englishTextField.getText());
            }
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            if(documentEvent.getDocument() == morseCodeTextField.getDocument()){
                outputText.setText(morseCodeTextField.getText());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {

        }
    }
}

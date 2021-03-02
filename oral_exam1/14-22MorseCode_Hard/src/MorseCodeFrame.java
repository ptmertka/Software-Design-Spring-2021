import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.ArrayList;


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

        TextListner handler = new TextListner();
        englishTextField.getDocument().addDocumentListener(handler);
        morseCodeTextField.getDocument().addDocumentListener(handler);




    }
    private class TextListner implements DocumentListener {
        HashMap<String, String> englishToMorse = new HashMap<String, String>();
        HashMap<String, String> morseToEnglish = new HashMap<String,String>();

        public TextListner(){
           String [] lowercase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l","m", "n",
           "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
           String [] uppercase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
           "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
           String [] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

           String [] morseLetters = {".-", "-...", "-.-.","-..", ".", "..-.", "--.", "....", "..", ".---",
                   "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
                   "-..-", "-.--", "--.."};
           String [] morseNumbers = {".----", "..---", "...--", "....-", ".....", "-....", "--...",
           "---..", "----.", "-----"};

           for(int i = 0; i < lowercase.length; i++){
               englishToMorse.put(lowercase[i], morseLetters[i]);
               englishToMorse.put(uppercase[i], morseLetters[i]);
               morseToEnglish.put(morseLetters[i], uppercase[i]);

           }
           for(int j = 0; j < numbers.length; j++){
               englishToMorse.put(numbers[j], morseNumbers[j]);
               morseToEnglish.put(morseNumbers[j], numbers[j] );
           }


        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
           if (documentEvent.getDocument() == englishTextField.getDocument()) {

               String englishText = englishTextField.getText();

               String [] splitText = englishText.split(" ");

               boolean badWord = false;
               ArrayList<String> textToAdd = new ArrayList<String>();
               for (String word : splitText){
                   char [] wordAsLetters = word.toCharArray();
                   for (char c : wordAsLetters){
                       if (Character.isLetterOrDigit(c)){
                           String s = String.valueOf(c);
                           String morseLetter = englishToMorse.get(s);
                           morseLetter = morseLetter + " ";
                           textToAdd.add(morseLetter);
                       }
                       else{
                           badWord = true;
                       }

                   }
                   textToAdd.add("  ");

               }

               if(badWord == true){
                   outputText.setText("You have entered an invalid character in the English Field, please delete it");
               }
               else{
                   StringBuilder morseText = new StringBuilder();
                   for(String a : textToAdd){
                       morseText.append(a);
                   }

                   morseCodeTextField.getDocument().removeDocumentListener(this);
                   morseCodeTextField.setText(String.valueOf(morseText));
                   morseCodeTextField.getDocument().addDocumentListener(this);
               }

            }
            else if (documentEvent.getDocument() == morseCodeTextField.getDocument()) {

               String morseCodeText = morseCodeTextField.getText();

               String [] splitText = morseCodeText.split("\\s{3,4}");

               boolean badWord = false;
               ArrayList<String> textToAdd = new ArrayList<String>();

               for( String word : splitText){
                   String letters [] = word.split(" ");
                   for (String letter : letters){
                       if(letter.matches("^[.-]+$")){
                           String englishLetter = morseToEnglish.get(letter);

                           if(englishLetter != null){
                               textToAdd.add(englishLetter);
                           }
                           else{
                               badWord = true;
                           }
                       }
                       else{
                           badWord = true;
                       }

                   }
                   textToAdd.add(" ");
               }

               if(badWord == true){
                   outputText.setText("You have entered an invalid character in the Morse Code Field, please delete it");
               }
               else{
                   StringBuilder englishText = new StringBuilder();
                   for(String a : textToAdd){
                       englishText.append(a);
                   }

                   englishTextField.getDocument().removeDocumentListener(this);
                   englishTextField.setText(String.valueOf(englishText));
                   englishTextField.getDocument().addDocumentListener(this);
               }


            }
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            if (documentEvent.getDocument() == englishTextField.getDocument()) {

                String englishText = englishTextField.getText();

                String [] splitText = englishText.split(" ");

                boolean badWord = false;
                ArrayList<String> textToAdd = new ArrayList<String>();
                for (String word : splitText){
                    char [] wordAsLetters = word.toCharArray();
                    for (char c : wordAsLetters){
                        if (Character.isLetterOrDigit(c)){
                            String s = String.valueOf(c);
                            String morseLetter = englishToMorse.get(s);
                            morseLetter = morseLetter + " ";
                            textToAdd.add(morseLetter);
                        }
                        else{
                            badWord = true;
                        }

                    }
                    textToAdd.add("  ");

                }

                if(badWord == true){
                    outputText.setText("You have entered an invalid character in the English Field, please delete it");
                }
                else{
                    StringBuilder morseText = new StringBuilder();
                    for(String a : textToAdd){
                        morseText.append(a);
                    }

                    morseCodeTextField.getDocument().removeDocumentListener(this);
                    morseCodeTextField.setText(String.valueOf(morseText));
                    morseCodeTextField.getDocument().addDocumentListener(this);
                }

            }
            else if (documentEvent.getDocument() == morseCodeTextField.getDocument()) {

                String morseCodeText = morseCodeTextField.getText();

                String [] splitText = morseCodeText.split("\\s{3,4}");

                boolean badWord = false;
                ArrayList<String> textToAdd = new ArrayList<String>();

                for( String word : splitText){
                    String letters [] = word.split(" ");
                    for (String letter : letters){
                        if(letter.matches("^[.-]+$")){
                            String englishLetter = morseToEnglish.get(letter);

                            if(englishLetter != null){
                                textToAdd.add(englishLetter);
                            }
                            else{
                                badWord = true;
                            }
                        }
                        else{
                            badWord = true;
                        }

                    }
                    textToAdd.add(" ");
                }

                if(badWord == true){
                    outputText.setText("You have entered an invalid character in the Morse Code Field, please delete it");
                }
                else{
                    StringBuilder englishText = new StringBuilder();
                    for(String a : textToAdd){
                        englishText.append(a);
                    }

                    englishTextField.getDocument().removeDocumentListener(this);
                    englishTextField.setText(String.valueOf(englishText));
                    englishTextField.getDocument().addDocumentListener(this);
                }


            }
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {

        }
    }
}

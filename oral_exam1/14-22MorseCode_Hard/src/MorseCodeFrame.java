import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Holds the creation and implementation of the swing elements to the frame
 * Contains the subclass for the Document Listener attached to the text fields
 */

public class MorseCodeFrame extends JFrame {

    /**
     * JTextArea for the english side of the translator
     */
    private final JTextArea englishTextField ;

    /**
     * JTextArea for the morse code side of the translator
     */
    private final JTextArea morseCodeTextField ;

    /**
     * JTextArea for the output field that either displays a success or error message
     */
    private final JTextArea outputText;

    /**
     * Constructor for the frame, calls the superclass constructor as well as adding listeners and definitions for the text areas
     */

    public MorseCodeFrame(){

        super("Morse Code Translator"); //calls the super class constructor to name the frame as a morse code translator

        setLayout(new FlowLayout()); //makes the layout a flow layout

        englishTextField = new JTextArea("Enter English Text Here", 10,20); // makes the fields 10 x 20, with default text in each
        morseCodeTextField = new JTextArea("Enter Morse Code Text here", 10,20);
        outputText = new JTextArea("Response will be displayed here", 10,20);

        outputText.setEditable(false); //makes it so the output text box is not editable

        add(englishTextField); //adds the fields to the frame
        add(morseCodeTextField);
        add(outputText);

        TextListener handler = new TextListener(); //creates a TextLister object of class TextListener, that implements a document listner
        englishTextField.getDocument().addDocumentListener(handler); //adds the listner to each of the two text fields
        morseCodeTextField.getDocument().addDocumentListener(handler);




    }

    /**
     * Class TextListener implemnts document listener, so that object of this class
     * are able to listen to text fields to check for changes to them, while also implementing the translation of the text
     */

    private class TextListener implements DocumentListener {

        /**
         * HashMap for the the english to morse code side of the translator, holding equivalent values from letters to morse code
         */
        HashMap<String, String> englishToMorse = new HashMap<String, String>();

        /**
         * HashMap for the morse code to english side of the translator, holding equivalent values from morse code to english
         */
        HashMap<String, String> morseToEnglish = new HashMap<String,String>();

        /**
         * Constructor of Text Listener object, created so that the hashmaps can be filled
         */
        public TextListener(){
            //These 5 arrays hold the values of upper and lower case letters, numbers, and their morse code counter parts, so they can be added to the hash maps effeciently
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

           //for every letter, adds its value and its equivalent morse code value and vice versa to the hash maps
           for(int i = 0; i < lowercase.length; i++){
               englishToMorse.put(lowercase[i], morseLetters[i]);
               englishToMorse.put(uppercase[i], morseLetters[i]);
               morseToEnglish.put(morseLetters[i], uppercase[i]);

           }
           //for every number, adds its value and its equivalent morse code value and vice versa to the hash map
           for(int j = 0; j < numbers.length; j++){
               englishToMorse.put(numbers[j], morseNumbers[j]);
               morseToEnglish.put(morseNumbers[j], numbers[j] );
           }


        }

        /**
         * Handles when any text is inserted into the text field
         * Takes the text, and attempts the proper translation fully or skips over an incorrect character
         * @param documentEvent DocumentEvent: an event created from a change to an associated text field
         */
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {

           if (documentEvent.getDocument() == englishTextField.getDocument()) { //if the event came from the english text box

               String englishText = englishTextField.getText(); //gets the text from the field

               String [] splitText = englishText.split(" "); //splits the text up by spaces, so that its in words

               boolean badWord = false; //boolean to check if a bad character was used

               ArrayList<String> textToAdd = new ArrayList<String>(); //array list to store the values of the translated letters

               for (String word : splitText){ //for every word

                   char [] wordAsLetters = word.toCharArray(); //converts the word to a character array

                   for (char c : wordAsLetters){ //for every letter

                       if (Character.isLetterOrDigit(c)){ //if the character is a letter or number (aka the only valid characters)
                           String s = String.valueOf(c); //make the character a string
                           String morseLetter = englishToMorse.get(s); //gets its equivalent in morse code
                           morseLetter = morseLetter + " "; //add a space to adhere to the morse translation
                           textToAdd.add(morseLetter); //add it to the array list of total text to add
                       }
                       else{
                           badWord = true; //if the character is not a valid one, tells the translator to skip over it
                       }

                   }
                   textToAdd.add("  "); //adds 3 spaces after very word

               }

               if(badWord == true){ //updates the output message if there was a bad character
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

                   outputText.setText("Translation Complete");
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

                   outputText.setText("Translation Complete");
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

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MorseCodeFrame extends JFrame {
    private final JTextField englishTextField ;
    private final JTextField morseCodeTextField ;
    private final JTextField outputText;

    public MorseCodeFrame(){
        super("Morse Code Translator");

        setLayout(new FlowLayout());

        englishTextField = new JTextField("Enter English Text Here", 200);
        morseCodeTextField = new JTextField("Enter Morse Code Text here", 200);
        outputText = new JTextField("Response will be displayed here", 200);

        outputText.setEditable(false);

        add(englishTextField);
        add(morseCodeTextField);
        add(outputText);

        EnglishTextListner handler = new EnglishTextListner();




    }
    private class EnglishTextListner implements KeyListener{

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}

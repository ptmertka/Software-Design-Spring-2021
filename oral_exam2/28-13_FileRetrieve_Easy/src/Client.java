import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class Client creates the GUI Frame for the client, allows the user to send messages to the server, in the form of filenames
 * Code once again comes mostly from in class example of ServerConnection
 * @author ptmertka
 * @version 4.15.21
 */
public class Client extends JFrame
{
    private JTextField enterField; // enters information from user
    private JTextArea displayArea; // display information to user
    private ObjectOutputStream output; // output stream to server
    private ObjectInputStream input; // input stream from server
    private String message = ""; // message from server
    private String chatServer; // host server for this application
    private Socket client; // socket to communicate with server

    // initialize chatServer and set up GUI

    /**
     * Constructor, creating the GUI of the client, taking in the IP address of the server
     * @param host String: the IP address of the server being connected to
     */
    public Client(String host)
    {
        super("Client");

        chatServer = host; // set server to which this client connects

        enterField = new JTextField(); // create enterField
        enterField.setEditable(false);
        enterField.addActionListener(
                new ActionListener()
                {
                    // send message to server
                    public void actionPerformed(ActionEvent event)
                    {
                        sendData(event.getActionCommand());
                        enterField.setText("");
                    }
                }
        );

        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea(); // create displayArea
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(300, 150); // set size of window
        setVisible(true); // show window
    }

    // connect to server and process messages from server

    /**
     * Function attempts to connect to the server, and if so, processes the connection and gets the input and output streams of the server
     */
    public void runClient()
    {
        try // connect to server, get streams, process connection
        {
            connectToServer(); // create a Socket to make connection
            getStreams(); // get the input and output streams
            processConnection(); // process connection
        }
        catch (EOFException eofException)
        {
            displayMessage("\nClient terminated connection");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            closeConnection(); // close connection
        }
    }

    // connect to server

    /**
     * Attempts to make a new socket connection to the exact port and IP address of the server, throws IOException if it doesn't
     * @throws IOException
     */
    private void connectToServer() throws IOException
    {
        displayMessage("Attempting connection\n");

        // create Socket to make connection to server
        client = new Socket(InetAddress.getByName(chatServer), 23669);

        // display connection information
        displayMessage("Connected to: " +
                client.getInetAddress().getHostName());
    }

    // get streams to send and receive data

    /**
     * If connection is made to server, gets the input and output streams of the server
     * @throws IOException
     */
    private void getStreams() throws IOException
    {
        // set up output stream for objects
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush(); // flush output buffer to send header information

        // set up input stream for objects
        input = new ObjectInputStream(client.getInputStream());

        displayMessage("\nGot I/O streams\n");
    }

    // process connection with server

    /**
     * Once connection is made and the streams are created, allows the user to send messages to the server
     * @throws IOException
     */
    private void processConnection() throws IOException
    {
        // enable enterField so client user can send messages
        setTextFieldEditable(true);

        do // process messages sent from server
        {
            try // read message and display it
            {
                message = (String) input.readObject(); // read new message
                displayMessage("\n" + message); // display message
            }
            catch (ClassNotFoundException classNotFoundException)
            {
                displayMessage("\nUnknown object type received");
            }

        } while (!message.equals("SERVER>>> TERMINATE"));
    }

    // close streams and socket

    /**
     * Once user chooses to disconnect from the server, closes streams and connection from the server
     */
    private void closeConnection()
    {
        displayMessage("\nClosing connection");
        setTextFieldEditable(false); // disable enterField

        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
            client.close(); // close socket
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    // send message to server

    /**
     * Takes a string and sends it as data to the server side
     * @param message String: the message, in this case a file name to send to the server
     */
    private void sendData(String message)
    {
        try // send object to server
        {
            output.writeObject("CLIENT>>> " + message);
            output.flush(); // flush data to output
            displayMessage("\nCLIENT>>> " + message);
        }
        catch (IOException ioException)
        {
            displayArea.append("\nError writing object");
        }
    }

    // manipulates displayArea in the event-dispatch thread

    /**
     * Displays messages in a thread Safe manner for the GUI with invokeLater
     * @param messageToDisplay String: what to be dispalyed to the Client GUI
     */
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }

    // manipulates enterField in the event-dispatch thread

    /**
     * Another thread safe function to turn on and off the enter field of the client GUI
     * @param editable Boolean: tells the client whether to turn on or off the ability to edit the enter field.
     */
    private void setTextFieldEditable(final boolean editable)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // sets enterField's editability
                    {
                        enterField.setEditable(editable);
                    }
                }
        );
    }
} 
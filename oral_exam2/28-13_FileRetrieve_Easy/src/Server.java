import java.io.*;
import java.net.ServerSocket;
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
 * Class server creates the server frame, as well as the socket for the server to run on
 * Also handles the filereading and parsing if the file exists, and if so, sends the text line by line to the client
 * Most code is from the in class example, with all file processing being created by me
 * @author ptmertka
 * @version 4.15.21
 */
public class Server extends JFrame
{
    private JTextField enterField; // inputs message from user
    private JTextArea displayArea; // display information to user
    private ObjectOutputStream output; // output stream to client
    private ObjectInputStream input; // input stream from client
    private ServerSocket server; // server socket
    private Socket connection; // connection to client
    private int counter = 1; // counter of number of connections

    // set up GUI

    /**
     * Constructor, makes the frame for the Server, allows for the GUI components to appear
     */
    public Server()
    {
        super("Server");

        enterField = new JTextField(); // create enterField
        enterField.setEditable(false);
        enterField.addActionListener(
                new ActionListener()
                {
                    // send message to client
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

    // set up and run server

    /**
     * Runs the server by creating the serverSocket, waits for the connection,
     * If it receives a connection, it gets the input and output streams, and then processes the connection
     */
    public void runServer()
    {
        try // set up server to receive connections; process connections
        {
            server = new ServerSocket(23669, 100); // create ServerSocket

            while (true)
            {
                try
                {
                    waitForConnection(); // wait for a connection
                    getStreams(); // get input & output streams
                    processConnection(); // process connection
                }
                catch (EOFException eofException)
                {
                    displayMessage("\nServer terminated connection");
                }
                finally
                {
                    closeConnection(); //  close connection
                    ++counter;
                }
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    // wait for connection to arrive, then display connection info

    /**
     * Displays the waiting message to the server, sets the server to accept new connections, and when it does
     * gets the IP address and host name of the client and displays it
     * @throws IOException
     */
    private void waitForConnection() throws IOException
    {
        displayMessage("Waiting for connection\n");
        connection = server.accept(); // allow server to accept connection
        displayMessage("Connection " + counter + " received from: " +
                connection.getInetAddress().getHostName());
    }

    // get streams to send and receive data

    /**
     * Once a connection has been made, gets the input and output streams of the connecting client
     * @throws IOException
     */
    private void getStreams() throws IOException
    {
        // set up output stream for objects
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush(); // flush output buffer to send header information

        // set up input stream for objects
        input = new ObjectInputStream(connection.getInputStream());

        displayMessage("\nGot I/O streams\n");
    }

    // process connection with client

    /**
     * Main function that handles the file processing. It takes in the clients message, wich is a file name
     * Makes a bufferedReader to attempt to parse the specified file, but if it does not exist, sends that error message to client and server
     * If the file does exist, goes line by line and sends the text to the client
     * @throws IOException
     */
    private void processConnection() throws IOException
    {
        String message = "Connection successful, please enter a filename";
        sendData(message); // send connection successful message

        // enable enterField so server user can send messages
        setTextFieldEditable(true);
        //creates a bufferedReader to read in a file, it defaults to the default text, not so that it reads it, but so the variable can be initialized
        BufferedReader reader = new BufferedReader(new FileReader("oral_exam2/28-13_FileRetrieve_Easy/textFiles/default.txt"));

        do // process messages sent from client
        {
            try // read message and display it
            {
                message = (String) input.readObject(); // read new message
                displayMessage("\n" + message); // display message
                String modifiedMessage = message.substring(10); //modifies the input from the user to take off the "CLIENT>>>"
                String filepath = "oral_exam2/28-13_FileRetrieve_Easy/textFiles/" + modifiedMessage + ".txt"; //makes a filepath string using the directory and the name of the file entered by the user
                reader = new BufferedReader(new FileReader(filepath)); //creates a BufferedReader object to read from the file path, returns an IOE exception if it fails

                String line; //empty string to store line by line

                while((line = reader.readLine()) != null){ //for each line in the file until it is null, sends each line of data to the client
                    sendData(line);
                }


            }
            catch (ClassNotFoundException classNotFoundException)
            {
                displayMessage("\nUnknown object type received");
                sendData("\nUnknown object type received");
            }
            catch (IOException ioe){ //IOE exception thrown by BufferedReader
                displayMessage("\n" + "File Does Not Exist\n" + ioe.getMessage()); //if the file doesn't exist, tells the server and client
                sendData("\n" + "File Does Not Exist\n" + ioe.getMessage());
            }
            finally { //closes the reader in order to ensure it is not left open
                reader.close();
            }


        } while (!message.equals("CLIENT>>> TERMINATE"));
    }

    // close streams and socket

    /**
     * Once the client disconnects from the server, function closes the input and output, as well as the disconnecting the socket connection
     */
    private void closeConnection()
    {
        displayMessage("\nTerminating connection\n");
        setTextFieldEditable(false); // disable enterField

        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
            connection.close(); // close socket
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    // send message to client

    /**
     * Sends data from the Server side to the client side
     * @param message String: the message to be sent to the client
     */
    private void sendData(String message)
    {
        try // send object to client
        {
            output.writeObject("SERVER>>> " + message);
            output.flush(); // flush output to client
            displayMessage("\nSERVER>>> " + message);
        }
        catch (IOException ioException)
        {
            displayArea.append("\nError writing object");
        }
    }

    // manipulates displayArea in the event-dispatch thread

    /**
     * Takes in a string so that it can update the GUI through the invokeLater method, making updates thread safe.
     * @param messageToDisplay
     */
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay); // append message
                    }
                }
        );
    }

    // manipulates enterField in the event-dispatch thread

    /**
     * Also makes another function thread safe, this time making the enterField editable or non editable
     * @param editable Boolean: whether or not the field should be editable or not
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
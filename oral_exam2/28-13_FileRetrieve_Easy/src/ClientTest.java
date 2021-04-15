// Fig. 28.6: ClientTest.java
// Class that tests the Client.


import javax.swing.JFrame;

/**
 * ClientTest creates a Client object, and allows the frame to be seen, while also determining what IP address to connect to from the Arguments
 * Code comes from the in class example
 */
public class ClientTest 
{
   /**
    * Creates the frame of the client, reads in the IP address from the configuration if there is one entered there. Runs the client once created
    * @param args List of strings that contains an IP address of server if it exists
    */
   public static void main(String[] args)
   {
      Client application; // declare client application

      // if no command line args
      if (args.length == 0)
         application = new Client("127.0.0.1"); // connect to localhost
      else
         application = new Client(args[0]); // use args to connect

      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.runClient(); // run client application
   } 
} 

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

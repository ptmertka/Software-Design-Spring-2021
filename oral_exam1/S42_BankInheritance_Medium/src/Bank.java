import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main driver of the class, allowing a user (A banker) to interact with the account system
 */
public class Bank {
    /**
     * Method to get a valid, positive, integer user input
     * Static so that it can be called anywhere user input is taken
     * @return i, integer: a valid user input, non negative
     */
    public static int getValidInput(){
        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs
        while (i < 0){ //while the user hasn't entered a negative number
            try{
                i = scanner.nextInt(); //gets the user's input
            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                String badInput = scanner.next();
                System.out.println("Please enter a valid, positive number");
                continue; //move past bad input

            }

        }
        return i;
    }

    public static void main(String args[]){
        System.out.println("Welcome to the Banking App!\n");

        boolean operation = true; //boolean to control loop

        ArrayList<Account> accountList = new ArrayList<Account>(); // creates an array list to store accounts

        while(operation){
            System.out.println("Please select what you would like to do\n");
            System.out.println("1. Create New Account\n +" +
                    "2. Interact Wtih Account\n" +
                    "3. View Bank Details\n");

            int userChoice = getValidInput(); //gets users choice for interaction


            if(userChoice == 1){ //if the user is going to create an account
                System.out.println("Please enter what type of account you would like to create\n" +
                        "1. Individual Account" +
                        "2. Company Account");

                userChoice = getValidInput();

                if(userChoice == 1){
                    String type = "Individual";
                }
                else if (userChoice == 2){
                    String type = "Company";
                }

            }




        }

    }
}

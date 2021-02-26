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
    public static int getValidInput(int boundary){
        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs
        while ((i < 1) || (i > boundary) ){ //while the user hasn't entered a negative number or is exceeding the bound
            try{
                i = scanner.nextInt(); //gets the user's input
            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                String badInput = scanner.next();
                System.out.println("Please enter a valid, integer between 1 and " + boundary);
                continue; //move past bad input

            }

        }
        return i;
    }

    /**
     * Converts an inputted, positive double to cents amount so that program can run in cents
     * @return Int: the amount entered in cents
     */
    public static int convertAndGetDouble(){
        double amount = -1.1;
        Scanner scanner = new Scanner(System.in);
        while (amount <0){ //while the user hasn't entered a negative number
            try{
                amount = scanner.nextDouble(); //gets the user's input
                System.out.println("Please enter a valid, positive number");
            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                String badInput = scanner.next();
                System.out.println("Please enter a valid, positive number");
                continue; //tells code to move past the error

            }
        }
        int centsAmount = (int) (amount * 100); //converts the double to cents and then returns it
        return centsAmount;
    }

    /**
     * Displays the total amount of each type of the account
     * @return String: A string that contains all the relevant account info of the bank
     */
    public String displayAccountsInfo(){
        String returnVal = "Total Number of Accounts:\n " +
                Account.getTotalAccounts() +"\n" +
                "Total Individual Accounts: \n" +
                (SavingsAccount.getNumSavingsAccounts() + CheckingAccount.getNumCheckingIndividual() + LoanAccount.getNumLoanAccountsIndividual()) + "\n" +
                "Total Company Accounts:\n" +
                (CheckingAccount.getNumCheckingCompany() +LoanAccount.getNumLoanAccountsCompany()) + "\n" +
                "Number of Savings Accounts:\n" +
                SavingsAccount.getNumSavingsAccounts() + "\n" +
                "Number of Individual Checking Accounts:\n" +
                CheckingAccount.getNumCheckingIndividual() + "\n" +
                "Number of Individual Loan Accounts:\n" +
                LoanAccount.getNumLoanAccountsCompany() + "\n" +
                "Number of Company Checking Accounts:\n" +
                CheckingAccount.getNumCheckingCompany() + "\n" +
                "Number of Company Loan Accounts:\n" +
                LoanAccount.getNumLoanAccountsCompany() +"\n";
            return returnVal;

    }

    public static void main(String args[]){
        System.out.println("Welcome to the Banking App!\n");

        boolean operation = true; //boolean to control loop

        ArrayList<Account> accountList = new ArrayList<Account>(); // creates an array list to store accounts

        Scanner scanner = new Scanner(System.in); //scanner to get string input

        while(operation){ //loop that runs while banker has the app open

            System.out.println("Please select what you would like to do\n"); //gives the banker options to choose how to operate the system
            System.out.println("1. Create New Account\n" +
                    "2. Interact Wtih Account\n" +
                    "3. View Bank Details\n"+
                    "4. Exit\n");

            int userChoice = getValidInput(4); //gets users choice for interaction


            if(userChoice == 1){ //if the user is going to create an account
                System.out.println("Please enter what type of account you would like to create\n" +
                        "1. Individual Account\n" +
                        "2. Company Account\n");

                userChoice = getValidInput(2);

                String type = ""; //variable to hold the users choice of type
                if(userChoice == 1){
                     type = "Individual";
                }
                else if (userChoice == 2){
                    type = "Company";
                }

                System.out.println("Please enter the name of the owner of the account");

                String owner = scanner.nextLine();

                if (type.equals("Individual")){ //if the user is making an individual account, gives all 3 options
                    System.out.println("Please choose what type of account you would like to create:\n" +
                            "1. Savings Account\n" +
                            "2. Checking Account\n" +
                            "3. Loan Account\n");
                    userChoice = getValidInput(3); //gets users choice


                }
                else{ //if the user is making a company account, only makes two accounts
                    System.out.println("Please choose what type of account you would like to create:\n" +
                            "1. Checking Account\n" +
                            "2. Loan Account\n");
                    userChoice = getValidInput(2); //gets users choice
                    userChoice++; //increases the user choice by 1 to match up with account creating code
                }

                System.out.println("Please enter the balance for this account in dollars");


                int centsAmount = convertAndGetDouble();//gets the double amount, converts it to cents for the balance



                if(userChoice ==1){ //if user is making a savings account
                    System.out.println("Please enter the interest rate for this account as a percent, integer percents only");
                    int rate = getValidInput(100); //gets the interest rate for the account

                    Account tempAccount = new SavingsAccount(owner, centsAmount, type, rate); //creates the new savings account
                    accountList.add(tempAccount); //adds the account to the arrayList
                    System.out.println(tempAccount.toString()); //prints out the account
                }
                else if (userChoice == 2){ //creates a new checking account
                    System.out.println("Please enter an overdraft limit for this account as a dollar amount");

                    int overdraft = convertAndGetDouble();

                    Account tempAccount = new CheckingAccount(owner, centsAmount, type, overdraft);
                    accountList.add(tempAccount); //creates the new checking account and adds it to the array list
                    System.out.println(tempAccount.toString()); //prints out the account
                }
                else if (userChoice == 3){ //creates a new loan account
                    System.out.println("Please enter the interest rate for this account as a percent");
                    int rate = getValidInput(100); //gets the interest rate for the account

                    Account tempAccount = new LoanAccount(owner, centsAmount, type, rate); //creates the new savings account
                    accountList.add(tempAccount); //adds the account to the arrayList
                    System.out.println(tempAccount.toString()); //prints out the account
                }






            }
            else if(userChoice == 4){ //if the user chooses to exit, exits
                operation = false;
            }




        }

    }
}

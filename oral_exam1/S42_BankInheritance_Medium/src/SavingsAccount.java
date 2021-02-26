/**
 * Subclass of Account Class, adds the Saving account functionality of accruing interest
 */
public class SavingsAccount extends Account{
    /**
     * Int: The interest rate of the account as a percentage
     */
    private int interestRate;

    /**
     * Static int: the number of savings accounts made in the bank
     */
    private static int numSavingsAccounts = 0;

    /**
     * Subclass constructor, calls the super class constructor as well as updating the interest rate and number of accounts
     * @param name String: the name of the Owner of the account
     * @param balanceOfAccount Int: The balance of the account in cents
     * @param type String: the type of the account
     * @param interest int: The percentage of interest for the account
     */
    public SavingsAccount(String name, int balanceOfAccount, String type, int interest){
        super(name, balanceOfAccount, type); //super class constructor
        interestRate = interest; //sets interest rate
        numSavingsAccounts = numSavingsAccounts+ 1; //increments number of savings accounts
    }


    /**
     * Adds the interest of the account to the balance
     */
    public void addInterest(){

        double rate = interestRate/100.000 ; //sets the interest rate to be in a non percent representation
        double temp = getBalance() + (getBalance() * rate); //adds the interest to the balance
        int newBalance = (int) temp; //casts it back to being an integer to account for cents
        setBalance(newBalance); //updates the balance of the account
    }

    /**
     * Getter for the number of the Savings Accounts
     * @return Int: The number of savings accounts
     */
    public static int getNumSavingsAccounts() {
        return numSavingsAccounts;
    }

    /**
     * Setter for the number of Savings Accounts in the bank
     * @param numSavingsAccounts Int: The new number to set the number of accounts to
     */
    public static void setNumSavingsAccounts(int numSavingsAccounts) {
        SavingsAccount.numSavingsAccounts = numSavingsAccounts;
    }

    /**
     * Allows users to withdraw from the account, with error checking for bad inputs
     * @param cents int: the amount being withdrawn in cents
     * @return String: The error or success message for the operation
     */
    @Override
    public String withdraw(int cents) {
        String returnString = ""; //string to hold return message of code

        if ((cents <= getBalance()) && (cents >=0)){ //if the user is withdrawn an amount smaller or equal to balance
            setBalance(getBalance()-cents);//removes the withdrawl
            returnString = "Withdrawl Succsesful!"; //sets the return message
        }
        else if (cents > getBalance()){ //if the user is trying to withdraw more than possible
            returnString ="You cannot withdraw more than your account balance"; //sets return message
        }
        else{ //third case is the withdraw amount is negative
            returnString = "You cannot withdraw less than 0 cents";  //sets return message
        }
        return returnString;
    }

    /**
     * Allows the user to deposit into the account, checking for bad inputs
     * @param cents Int; the amount being deposited
     * @return String: the return message either of success or error
     */
    @Override
    public String deposit(int cents) {
        String returnString = ""; //sets a string for the return message

        if (cents >=0){ //if the user is adding 0 or more cents
            setBalance(getBalance()+cents); //adds the amount and sets the balance to the same plus the deposit
            returnString = "Deposit Succseful";
        }
        else{ //if the user tries adding negative funds, sets an error message
            returnString = "You cannot deposit a negative value";
        }

        return returnString;
    }

    @Override
    public String toString() {
       return "Savings Account: \n" +
               "---------------------------\n" +
               "Account Owner: " + getAccountUserName() + "\n" +
               "Account Number: " + getAccountNumber() + "\n" +
               "Account Type: " + getAccountType() + "\n" +
               "Balance: $" + (double) getBalance()/100.0 + "\n" +
               "Interest Rate: " + interestRate + "%\n" +
               "--------------------------\n";

    }
}

/**
 * Extends the account super class, adding an overdraft limit that allows users to withdraw past zero, but not past their limit
 */
public class CheckingAccount extends Account{
    /**
     * Int: The limit in cents of how far the user can draw over 0 dollars in their account
     */
    private int overdraftLimit;

    /**
     * Static int: The number of Individual Checking Accounts made in the bank
     */
    private static int NumCheckingIndividual = 0;

    /**
     * Static int: The number of Company Checking Accounts made in the bank
     */
    private static int numCheckingCompany = 0;

    /**
     * Class constructor, calls superclass constructor before initializing the overdraft limit and incrimenting the number of accounts
     * @param name String: Name of the user of the account
     * @param balanceOfAccount int: The balance of the account to set in cents
     * @param type String: the type of the account
     * @param overdraft int: the overdraft limit of the account in cents
     */
    public CheckingAccount(String name, int balanceOfAccount, String type, int overdraft){
        super(name,  balanceOfAccount, type); //calls super cosntructor
        overdraftLimit = overdraft; //sets the over draft limit
        if(type.equals("Individual")){ //if this account is for an individual, updates the number of individual account, else the number of company accounts
            NumCheckingIndividual++;
        }else{
            numCheckingCompany++;
        }


    }

    /**
     * Overridden withdraw function to allow for the overdraft limit to be applied, drawing over zero
     * @param cents int: the amount being withdrawn in cents
     * @return String; the error or success message of the method
     */
    @Override
    public String withdraw(int cents){

        String returnMessage; //sets the string of the return message

        if ((cents >= 0) && (getBalance()-cents >= (overdraftLimit* -1))){ //if the withdraw will work, now including the overdraft limit
            setBalance(getBalance()-cents); //applies the withdraw and sets the return message
            returnMessage = "Withdraw Succesful";
        }
        else if (getBalance()-cents < (overdraftLimit * -1)){ //returns the error of the user is going to over draft, reminding the user of the limit
            returnMessage = "You cannot withdraw past your overdraft Limit of $" + (double) overdraftLimit/100 ;
        }
        else{ //sets the return message if the user tries to withdraw a negative number
            returnMessage = "You cannot withdraw a negative amount of money";
        }
        return returnMessage;
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

    /**
     * Getter the number of checking accounts for individuals
     * @return
     */
    public static int getNumCheckingIndividual() {
        return NumCheckingIndividual;
    }

    /**
     * Setter for the number of individual checking accounts in the bank
     * @param numCheckingIndividual Int: the new number of checking accounts in the bank to update to
     */
    public static void setNumCheckingIndividual(int numCheckingIndividual) {
        NumCheckingIndividual = numCheckingIndividual;
    }

    /**
     * Getter for the number of the checking accounts for companies
     * @return Int: the number of company checking accounts
     */
    public static int getNumCheckingCompany() {
        return numCheckingCompany;
    }

    /**
     * Setter for the number of company checking accounts
     * @param numCheckingCompany Int: The new number of checking accounts for companys
     */
    public static void setNumCheckingCompany(int numCheckingCompany) {
        CheckingAccount.numCheckingCompany = numCheckingCompany;
    }

    @Override
    public String toString() {
        return "Checking Account: \n" +
                "---------------------------\n" +
                "Account Owner: " + getAccountUserName() + "\n" +
                "Account Number: " + getAccountNumber() + "\n" +
                "Account Type: " + getAccountType() + "\n" +
                "Balance: $" + (double) (getBalance()/100.0) + "\n" +
                "Overdraft Limit: $" + (double) (overdraftLimit/100.0) + "\n" +
                "----------------------------\n";
    }
}

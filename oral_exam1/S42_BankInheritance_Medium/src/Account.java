/**
 * Holds the basic functionality for the 3 subclasses of the account types
 * Relevant information like account holder, number, and type are all included
 */
public class Account {

    /**
     * String: The name of the User who own's the account
     */
    private String accountUserName;

    /**
     * Int: The account number associated with the account
     */
    private int accountNumber;

    /**
     * String: The type of the account, either company or individual
     */
    private String accountType;

    /**
     * Int: The balance in the account, treated as a int to represent cents
     */
    private int balance = 0;

    /**
     * Static int: the number of savings accounts made in the bank
     */
    private static int numSavingsAccounts = 0;

    /**
     * Static int: The number of Individual Checking Accounts made in the bank
     */
    private static int NumCheckingIndividual = 0;

    /**
     * Static int: The number of Company Checking Accounts made in the bank
     */
    private static int numCheckingCompany = 0;

    /**
     * Static int: The number of Individual Loan Accounts made in the bank
     */
    private static int numLoanAccountsIndividual = 0;

    /**
     * The number of company Loan accounts made in the bank
     */
    private static int numLoanAccountsCompany = 0;

    /**
     * Class constructor for the Account class
     * @param name The name of the account owner
     * @param number The number of the account
     * @param balanceOfAccount the balance of the account
     * @param type The account type
     */
    public Account(String name, int number, int balanceOfAccount, String type){
        accountUserName = name;
        accountNumber = number;
        balance = balanceOfAccount;
        accountType = type;
    }

    /**
     * Getter for the account UserName
     * @return String: the name of the account owner
     */
    public String getAccountUserName() {
        return accountUserName;
    }

    /**
     * Setter for the accountUserName
     * @param accountUserName String: The name of the user of the account
     */
    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    /**
     * Getter for the account Number
     * @return int: The account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Setter for the account number
     * @param accountNumber Int: the new number of the account
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Getter for the account type, either company or individual
     * @return String: The type of the account
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Setter for the account type
     * @param accountType String: The type of the account to be set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter for the balance of the account in cents
     * @return
     */
    public int getBalance() {
        return balance;
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
        Account.numSavingsAccounts = numSavingsAccounts;
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
        Account.numCheckingCompany = numCheckingCompany;
    }

    /**
     * Getter for the number of individual loan accounts
     * @return Int: The number of individual loan accounts
     */
    public static int getNumLoanAccountsIndividual() {
        return numLoanAccountsIndividual;
    }

    /**
     * Setter for the number of individual loan accounts
     * @param numLoanAccountsIndividual Int: The new number of individual loan accounts
     */
    public static void setNumLoanAccountsIndividual(int numLoanAccountsIndividual) {
        Account.numLoanAccountsIndividual = numLoanAccountsIndividual;
    }

    /**
     * Getter for the number of loan accounts for companies
     * @return Int: The number of company loan accounts
     */
    public static int getNumLoanAccountsCompany() {
        return numLoanAccountsCompany;
    }

    /**
     * Setter for the number of loan accounts for companies
     * @param numLoanAccountsCompany Int: The new number of company loan accounts
     */
    public static void setNumLoanAccountsCompany(int numLoanAccountsCompany) {
        Account.numLoanAccountsCompany = numLoanAccountsCompany;
    }

    /**
     * Allows users to withdraw from the account, with error checking for bad inputs
     * @param cents int: the amount being withdrawn in cents
     * @return String: The error or success message for the operation
     */
    public String withdraw(int cents){
        String returnString = ""; //string to hold return message of code

        if ((cents <= balance) && (cents >=0)){ //if the user is withdrawn an amount smaller or equal to balance
            balance = balance - cents; //removes the withdrawl
            returnString = "Withdrawl Succsesful!"; //sets the return message
        }
        else if (cents > balance){ //if the user is trying to withdraw more than possible
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
    public String deposit(int cents){
        String returnString = ""; //sets a string for the return message

        if (cents >=0){ //if the user is adding 0 or more cents
            balance = balance + cents; //adds the amount and sets the balance to the same plus the deposit
            returnString = "Deposit Succseful";
        }
        else{ //if the user tries adding negative funds, sets an error message
            returnString = "You cannot deposit a negative value";
        }

        return returnString;

    }



}

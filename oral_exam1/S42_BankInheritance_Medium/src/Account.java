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

    public String withdraw(int cents){
        String returnString = ""; //string to hold return message of code
        if ((cents <= balance) && (cents >=1)){
            balance = balance - cents;
            returnString = "Withdrawl Succsesful!";
        }
        else if (cents > balance){
            returnString ="You cannot withdraw more than your account balance";
        }
        else{
            returnString = "You cannot withdraw less than 1 cent";
        }
        return returnString;
    }

    public String deposit(int cents)

}

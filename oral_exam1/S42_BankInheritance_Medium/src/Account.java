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
     * Getter for the
     * @return
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static int getNumSavingsAccounts() {
        return numSavingsAccounts;
    }

    public static void setNumSavingsAccounts(int numSavingsAccounts) {
        Account.numSavingsAccounts = numSavingsAccounts;
    }

    public static int getNumCheckingIndividual() {
        return NumCheckingIndividual;
    }

    public static void setNumCheckingIndividual(int numCheckingIndividual) {
        NumCheckingIndividual = numCheckingIndividual;
    }

    public static int getNumCheckingCompany() {
        return numCheckingCompany;
    }

    public static void setNumCheckingCompany(int numCheckingCompany) {
        Account.numCheckingCompany = numCheckingCompany;
    }

    public static int getNumLoanAccountsIndividual() {
        return numLoanAccountsIndividual;
    }

    public static void setNumLoanAccountsIndividual(int numLoanAccountsIndividual) {
        Account.numLoanAccountsIndividual = numLoanAccountsIndividual;
    }

    public static int getNumLoanAccountsCompany() {
        return numLoanAccountsCompany;
    }

    public static void setNumLoanAccountsCompany(int numLoanAccountsCompany) {
        Account.numLoanAccountsCompany = numLoanAccountsCompany;
    }

}

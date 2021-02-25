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
     * @param number Int: the number of the account
     * @param balanceOfAccount Int: The balance of the account in cents
     * @param type String: the type of the account
     * @param interest int: The percentage of interest for the account
     */
    public SavingsAccount(String name, int number, int balanceOfAccount, String type, int interest){
        super(name, number, balanceOfAccount, type); //super class constructor
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
}

/**
 * Extends the account superclass to implement a interest rate on the loan and allows payments on the balance
 */
public class LoanAccount extends Account{

    /**
     * Int; the interest rate of the loan in a percentage
     */
    private int interestRate;

    /**
     * Static int: The number of Individual Loan Accounts made in the bank
     */
    private static int numLoanAccountsIndividual = 0;

    /**
     * The number of company Loan accounts made in the bank
     */
    private static int numLoanAccountsCompany = 0;

    /**
     * Class constructor, calls the super class constrctor before incrementing number of accounts
     * @param name String: Name of the account owner
     * @param number int: The account number
     * @param balanceOfAccount int: The balance of the account in cents
     * @param type String: the type of the account
     * @param rate int: the interest rate of the account as a percent
     */
    public LoanAccount(String name, int number, int balanceOfAccount, String type, int rate){
        super(name, number, balanceOfAccount, type);
        interestRate = rate; //sets the interest rate
        if(type == "Individual"){ //updates the number of accounts based on type
            numLoanAccountsIndividual++;

        }else{
            numLoanAccountsCompany++;
        }


    }

    /**
     * Allows the user to make a payment on their loan account
     * @param cents The amount being paid in cents
     * @return String: the positive/negative return message
     */
    @Override
    public String withdraw(int cents){
        String returnMessage = ""; //return message for the function

        if (cents >= 0 && (getBalance() - cents >= 0)){ //if the payment on the balance is valud
            setBalance(getBalance() - cents); //applies the payment
            returnMessage = "Payment Succseful";

        }
        else if (getBalance() - cents < 0){ //if the payment is going to overpay the loan

            returnMessage = "You cannot payoff more than the balance of the account";

        }
        else{ //case assumes cents is less than zero
            returnMessage = "You cannot make a negative payment";
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
            setBalance(getBalance() + cents); //adds the amount and sets the balance to the same plus the deposit
            returnString = "Deposit Succseful";
        }
        else{ //if the user tries adding negative funds, sets an error message
            returnString = "You cannot deposit a negative value";
        }

        return returnString;
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
        LoanAccount.numLoanAccountsIndividual = numLoanAccountsIndividual;
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
        LoanAccount.numLoanAccountsCompany = numLoanAccountsCompany;
    }
}



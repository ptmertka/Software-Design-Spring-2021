/**
 * Extends the account superclass to implement a interest rate on the loan and allows payments on the balance
 */
public class LoanAccount extends Account{

    /**
     * Int; the interest rate of the loan in a percentage
     */
    private int interestRate;

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
        interestRate = rate;
        if(type == "Individual"){
            setNumLoanAccountsIndividual(getNumLoanAccountsIndividual() + 1);

        }else{
            setNumLoanAccountsCompany(getNumLoanAccountsCompany() + 1);
        }


    }

    /**
     * Allows the user to make a payment on their loan account
     * @param cents The amount being paid in cents
     * @return String: the positive/negative return message
     */
    public String makePayment(int cents){
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
     * Adds the interest of the account to the balance
     */
    public void addInterest(){

        double rate = interestRate/100.000 ; //sets the interest rate to be in a non percent representation
        double temp = getBalance() + (getBalance() * rate); //adds the interest to the balance
        int newBalance = (int) temp; //casts it back to being an integer to account for cents
        setBalance(newBalance); //updates the balance of the account
    }
}



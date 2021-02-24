/**
 * Extends the account super class, adding an overdraft limit that allows users to withdraw past zero, but not past their limit
 */
public class CheckingAccount extends Account{
    /**
     * Int: The limit in cents of how far the user can draw over 0 dollars in their account
     */
    private int overdraftLimit;

    /**
     * Class constructor, calls superclass constructor before initializing the overdraft limit and incrimenting the number of accounts
     * @param name String: Name of the user of the account
     * @param number int: The number of the account
     * @param balanceOfAccount int: The balance of the account to set in cents
     * @param type String: the type of the account
     * @param overdraft int: the overdraft limit of the account in cents
     */
    public CheckingAccount(String name, int number, int balanceOfAccount, String type, int overdraft){
        super(name, number, balanceOfAccount, type); //calls super cosntructor
        overdraftLimit = overdraft; //sets the over draft limit
        if(type.equals("Individual")){ //if this account is for an individual, updates the number of individual account, else the number of company accounts
            setNumCheckingIndividual(getNumCheckingIndividual() + 1);
        }else{
            setNumCheckingCompany(getNumCheckingCompany() + 1);
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
}

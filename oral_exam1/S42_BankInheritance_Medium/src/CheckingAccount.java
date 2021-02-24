public class CheckingAccount extends Account{

    private int overdraftLimit;

    public CheckingAccount(String name, int number, int balanceOfAccount, String type, int overdraft){
        super(name, number, balanceOfAccount, type);
        overdraftLimit = overdraft;
        if(type == "Individual"){
            setNumCheckingIndividual(getNumCheckingIndividual() + 1);
        }else{
            setNumCheckingCompany(getNumCheckingCompany() + 1);
        }


    }
    @Override
    public String withdraw(int cents){
        String returnMessage;
        if ((cents >= 0) && (getBalance()-cents >= (overdraftLimit* -1))){
            setBalance(getBalance()-cents);
            returnMessage = "Withdraw Succesful";
        }
        else if (getBalance()-cents < (overdraftLimit * -1)){
            returnMessage = "You cannot withdraw past your overdraft Limit of $" + (double) overdraftLimit/100 ;
        }
        else{
            returnMessage = "You cannot withdraw a negative amount of money";
        }
        return returnMessage;
    }
}

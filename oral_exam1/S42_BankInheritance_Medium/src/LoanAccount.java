public class LoanAccount extends Account{

    private int interestRate;

    public LoanAccount(String name, int number, int balanceOfAccount, String type, int rate){
        super(name, number, balanceOfAccount, type);
        interestRate = rate;
        if(type == "Individual"){
            setNumLoanAccountsIndividual(getNumLoanAccountsIndividual() + 1);

        }else{
            setNumLoanAccountsCompany(getNumLoanAccountsCompany() + 1);
        }


    }
}

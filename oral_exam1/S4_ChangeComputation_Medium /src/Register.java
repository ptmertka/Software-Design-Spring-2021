import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class Register:
 * Holds the main fucntionality of the register object for the assignment,
 * Contains the number of each type of bill and coin in the register and allows the user to make change based on user purchases
 * @author Peter Mertka
 * @version 2/5/2021
 */
public class Register {

    /**
     * integer: the number of twenty dollar bills in the register, integer:
     */
    private int numTwenties;

    /**
     * integer: the number of ten dollar bills in the register
     */
    private int numTens;

    /**
     * integer: the number of five dollar bills in the register
     */
    private int numFives;

    /**
     * integer: the number of ones dollar bills in the register
     */
    private int numOnes;

    /**
     * integer: the number of quarters in the register
     */
    private int numQuarters;

    /**
     * integer: the number of dimes in the register
     */
    private int numDimes;

    /**
     * integer: the number of nickels in the register
     */
    private int numNickels;

    /**
     * integer: the number of pennies in the register
     */
    private int numPennies;

    /**
     * Random: This random object allows for a random number of bills to be generated in the register each time an object is made
     */
    Random random1 = new Random();


    /**
     * Class constructor
     * Creates a new register object, with each bill and coin being initilized to a random number between 0 and 15
     */
    public Register() {

        numTwenties = random1.nextInt(16); //randomly generate value between 0-15, represents number of 20's in register
        numTens = random1.nextInt(16); //randomly generate value between 0-15, represents number of 10's in register
        numFives = random1.nextInt(16); //randomly generate value between 0-15, represents number of 5's in register
        numOnes = random1.nextInt(16); //randomly generate value between 0-15, represents number of 1's in register
        numQuarters = random1.nextInt(16); //randomly generate value between 0-15, represents number of quarters in register
        numDimes = random1.nextInt(16); //randomly generate value between 0-15, represents number of dimes in register
        numNickels = random1.nextInt(16); //randomly generate value between 0-15, represents number of nickels in register
        numPennies = random1.nextInt(16); //randomly generate value between 0-15, represents number of pennies in register

    }

    /**
     * Calculates the total amount of money in the register and prints out how many of each bill and coin you have
     * @return integer: cash value of all money in register in cents
     */
    int checkCashInRegister(){
        int totalInRegister = 0;
        totalInRegister = totalInRegister + (numTwenties * 2000); //every line takes the number of each item in register and multiplies it by its value
        //By doing this, it gets the cash value in the register, adding it to a running total
        System.out.println("You have " + numTwenties + " twenty dollar bill(s)"); //This function also prints out the total amount of each bill and coin in the register
        totalInRegister = totalInRegister + (numTens * 1000);
        System.out.println("You have " + numTens + " ten dollar bill(s)");
        totalInRegister = totalInRegister + (numFives * 500);
        System.out.println("You have " + numFives + " five dollar bill(s)");
        totalInRegister = totalInRegister + (numOnes * 100);
        System.out.println("You have " + numOnes + " one dollar bill(s)");
        totalInRegister = totalInRegister + (numQuarters * 25);
        System.out.println("You have " + numQuarters + " quarter(s)");
        totalInRegister = totalInRegister + (numDimes * 10);
        System.out.println("You have " + numDimes + " dimes(s)");
        totalInRegister = totalInRegister + (numNickels * 5);
        System.out.println("You have " + numNickels + " nickel(s)");
        totalInRegister = totalInRegister + (numPennies * 1);
        System.out.println("You have " + numPennies + " pennies(s)");
        return totalInRegister; //returns the running total

    }

    /**
     *Calculates the correct change needed based on the transaction, trying to find the most efficient change possible
     * Returns the money to the customer and throws an error if not enough bills to provide change
     * @param amountOwed integer: amount of total change owed in cents
     * @param twenties integer: number of twenty dollar bills used by customer to pay
     * @param tens integer: number of ten dollar bills used by customer to pay
     * @param fives integer: number of five dollar bills used by customer to pay
     * @param ones integer: number of one dollar bills used by customer to pay
     * @param quarters integer: number of quarters used by customer to pay
     * @param dimes integer: number of dimes used by customer to pay
     * @param nickels integer: number of nickels used by customer to pay
     * @param pennies integer: number of pennies used by customer to pay
     */
    public void returnChange(int amountOwed,int twenties, int tens, int fives, int ones, int quarters, int dimes, int nickels, int pennies){

        boolean canCompute = true; //boolean temp variable that tells the loop if there is enough of the right money in the register to give exact change
        int twentiesUsed = 0; // all of these int variables are used to track how much of each denomination is used in producing change
        int tensUsed = 0;
        int fivesUsed = 0;
        int onesUsed = 0;
        int quartersUsed = 0;
        int dimesUsed = 0;
        int nickelsUsed = 0;
        int penniesUsed = 0;


        int i = 0; //counter variable used to represent number of each item used as necessary
            checkCashInRegister();

            if (amountOwed >= 2000) { //for each repeated chunk, it checks to see if the denomination of bill can be used to lower the amount owed

                while(!((amountOwed - (i * 2000) >= 0) && (amountOwed - (i * 2000) < 2000)) && (numQuarters-i >0)) {//if it can, it checks to see how many of each bill to use, using a while loop

                    //if any amount of a denomination is 0, the loop simply doesn't execute, if there is 1 or more bills, it goes through 1-number available, until a number gets
                    //you to a level where the amount remaining when that many bills is subtracted off is less than the bill value itself, or all of the bill/coin is used.
                    i++;
                }

                twentiesUsed = i; //sets the number of bills being used to i
                amountOwed = amountOwed - (2000 * i); //subtracts off the number of bills being used value to lower the total amount owed

            } //this process is repeated for every value of change in the cash register

            if (amountOwed >= 1000) {

                i = 0;
                while(!((amountOwed - (i * 1000) >= 0) && (amountOwed - (i * 1000) < 1000)) && (numTens-i >0)) {

                    i++;
                }

                tensUsed = i;
                amountOwed = amountOwed - (1000 * i);
            }

            if (amountOwed >= 500){

                i = 0;
                while(!((amountOwed - (i * 500) >= 0) && (amountOwed - (i * 500) < 500)) && (numFives-i >0)) {

                    i++;
                }

                fivesUsed = i;
                amountOwed = amountOwed - (500 * i);
            }

            if (amountOwed >= 100){

                i = 0;
                while(!((amountOwed - (i * 100) >= 0) && (amountOwed - (i * 100) < 100)) && (numOnes-i >0)) {

                    i++;
                }

                onesUsed = i;
                amountOwed = amountOwed - (100 * i);
            }

            if (amountOwed >= 25){

                i = 0;
                while(!((amountOwed - (i * 25) >= 0) && (amountOwed - (i * 25) < 25)) && (numQuarters-i >0)) {

                    i++;
                }

                quartersUsed = i;
                amountOwed = amountOwed - (25 * i);
            }

            if (amountOwed >= 10){

                i = 0;
                while(!((amountOwed - (i * 10) >= 0) && (amountOwed - (i * 10) < 10)) && (numDimes-i >0)) {

                    i++;
                }

                dimesUsed = i;
                amountOwed = amountOwed - (10 * i);
            }

            if (amountOwed >= 5){

                i = 0;
                while(!((amountOwed - (i * 5) >= 0) && (amountOwed - (i * 5) < 5)) && (numNickels-i >0)) {

                    i++;
                }

                nickelsUsed = i;
                amountOwed = amountOwed - (5 * i);
            }

            if (amountOwed >= 1){

                i = 0;
                while(!((amountOwed - (i * 1) >= 0) && (amountOwed - (i * 1) < 1)) && (numPennies-i >0)) {

                    i++;
                }

                penniesUsed = i;
                amountOwed = amountOwed - (1 * i);
            }

            if( amountOwed != 0){ //if there is still an amount owed left greater than a penny, there is insufficient bills in the register to give exact change

                canCompute = false; //sets canCompute to false as a flag to the inability to calculate change
            }



        if (canCompute == false){ //if the register cannot return change, it displays an error explaining so

            System.out.println("I'm sorry, your change can cannot be calculated due to lack of proper bills");
            removeMoneyFromRegister( twenties, tens,  fives,  ones, quarters,  dimes,  nickels, pennies); //removes bills added before calculation to return to customer

        }

        else{ //if proper bills can be used, this else displays the amount used and subtracts their number from each denomination to sybolize removing it from the register

            System.out.println("Your change is " + twentiesUsed + " twenty dollar bill(s)");
            numTwenties = numTwenties - twentiesUsed;
            System.out.println("Your change is " + tensUsed + " ten dollar bill(s)");
            numTens = numTens - tensUsed;
            System.out.println("Your change is " + fivesUsed + " five dollar bill(s)");
            numFives = numFives-fivesUsed;
            System.out.println("Your change is " + onesUsed + " one dollar bill(s)");
            numOnes = numOnes - onesUsed;
            System.out.println("Your change is " + quartersUsed + " quarters(s)");
            numQuarters = numQuarters - quartersUsed;
            System.out.println("Your change is " + dimesUsed + " dimes(s)");
            numDimes = numDimes - dimesUsed;
            System.out.println("Your change is " + nickelsUsed + " nickels(s)");
            numNickels = numNickels - nickelsUsed;
            System.out.println("Your change is " + penniesUsed + " pennies(s)");
            numPennies = numPennies - penniesUsed;

        }

    }

    /**
     * adds the bills used by the customer to pay to the register so that they can be used for change if necessary
     *
     * @param twenties integer: number of twenty dollar bills used by customer to pay
     * @param tens integer: number of ten dollar bills used by customer to pay
     * @param fives integer: number of five dollar bills used by customer to pay
     * @param ones integer: number of one dollar bills used by customer to pay
     * @param quarters integer: number of quarters used by customer to pay
     * @param dimes integer: number of dimes used by customer to pay
     * @param nickels integer: number of nickels used by customer to pay
     * @param pennies integer: number of pennies used by customer to pay
     */
    public void addMoneyToRegister(int twenties, int tens, int fives, int ones, int quarters, int dimes, int nickels, int pennies){

        numTwenties = numTwenties + twenties; //based on parameters input by user, adds specific number of each bill paid with to the cash register
        numTens = numTens + tens;
        numFives = numFives + fives;
        numOnes = numOnes + ones;
        numQuarters = numQuarters + quarters;
        numDimes = numDimes + dimes;
        numNickels = numNickels + nickels;
        numPennies = numPennies+ pennies;
    }

    /**
     *Takes the bills out of the register if the cashier is unable to make proper change, returning the bills to the customer
     * @param twenties integer: number of twenty dollar bills used by customer to pay
     * @param tens integer: number of ten dollar bills used by customer to pay
     * @param fives integer: number of five dollar bills used by customer to pay
     * @param ones integer: number of one dollar bills used by customer to pay
     * @param quarters integer: number of quarters used by customer to pay
     * @param dimes integer: number of dimes used by customer to pay
     * @param nickels integer: number of nickels used by customer to pay
     * @param pennies integer: number of pennies used by customer to pay
     */
    public void removeMoneyFromRegister(int twenties, int tens, int fives, int ones, int quarters, int dimes, int nickels, int pennies){

        numTwenties = numTwenties - twenties; //based on parameters input by user, removes specific number of each bill paid with from the cash register
        numTens = numTens - tens; //this is done if there is not enough of all bills or a specifc combination so that exact change cannot be given.
        numFives = numFives - fives;
        numOnes = numOnes - ones;
        numQuarters = numQuarters - quarters;
        numDimes = numDimes - dimes;
        numNickels = numNickels - nickels;
        numPennies = numPennies - pennies;
    }

    /**
     * Sums up the bills and coins used by the customer and convers the number to cents
     * @param twenties integer: number of twenty dollar bills used by customer to pay
     * @param tens integer: number of ten dollar bills used by customer to pay
     * @param fives integer: number of five dollar bills used by customer to pay
     * @param ones integer: number of one dollar bills used by customer to pay
     * @param quarters integer: number of quarters used by customer to pay
     * @param dimes integer: number of dimes used by customer to pay
     * @param nickels integer: number of nickels used by customer to pay
     * @param pennies integer: number of pennies used by customer to pay
     * @return
     */
    public int sumBillsAndCoins(int twenties, int tens, int fives, int ones, int quarters, int dimes, int nickels, int pennies){
        int total = 0;
        total = total + (twenties * 2000); //takes an custom input of bills and coins and sums them all together
        total = total + (tens * 1000);
        total = total + (fives * 500);
        total = total + (ones * 100);
        total = total + (quarters *25);
        total = total + (dimes * 10);
        total = total + (nickels * 5);
        total = total + (pennies * 1);
        return total;
    }

    /**
     * Converts the price of the item from a double dollar value to an integer value of cents
     * @param value double: the dollar amount of the price of the item being bought
     * @return valInCents integer: the value of the item being bought in cents
     */
    public int changeToCents(double value){
        int valInCents = (int) (value * 100);
        return  valInCents;
    }

    /**
     * Function that handles purchases by taking in bills and calculating whether a sale can take place
     * @param twenties integer: number of twenty dollar bills used by customer to pay
     * @param tens integer: number of ten dollar bills used by customer to pay
     * @param fives integer: number of five dollar bills used by customer to pay
     * @param ones integer: number of one dollar bills used by customer to pay
     * @param quarters integer: number of quarters used by customer to pay
     * @param dimes integer: number of dimes used by customer to pay
     * @param nickels integer: number of nickels used by customer to pay
     * @param pennies integer: number of pennies used by customer to pay
     */

    public void handleSale(int twenties, int tens, int fives, int ones, int quarters, int dimes, int nickels, int pennies, double priceOfItem){
        addMoneyToRegister(twenties,tens,fives,ones,quarters, dimes, nickels, pennies); //adds the money being used to pay to the register, making it available to be used in change
        int newPriceOfItem = changeToCents(priceOfItem);
        int cashRecieved = sumBillsAndCoins(twenties,tens,fives,ones,quarters, dimes, nickels, pennies); //calculates the sum of money being used by the customer to pay


        if(cashRecieved - newPriceOfItem <0){ //if the customer doesn't provide sufficient funds, return an error message
            System.out.println("I'm sorry, you have provided in sufficient funds"); //prints out error
            removeMoneyFromRegister( twenties, tens,  fives,  ones, quarters,  dimes,  nickels, pennies); //removes bills added before calculation to return to customer
        }
        else {
            returnChange(cashRecieved - newPriceOfItem, twenties, tens, fives, ones, quarters, dimes, nickels, pennies); //takes in the bills recieved and the amount of change owed and returns change if possible
        }


    }

    /**
     * Method to get a valid, positive, integer user input
     * @return i, interger: a valid user input, non negative
     */
    private int getValidInput(){
        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs
        while (i < 0){ //while the user hasn't entered a negative number
            try{
                i = scanner.nextInt(); //gets the user's input
            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                String badInput = scanner.next();
                System.out.println("Please enter a valid, positive number");
                continue; //move past bad input

            }

        }
        return i;
    }

    /**
     * Main loop that handles purchases, runs to handle multiple purchases per the user input
     */
    public void beginOperation(){

        Scanner scanner = new Scanner(System.in); //scanner to handle inputs

        boolean takingOrders = true; //creates variable to track if user is still taking orders

        System.out.println("Welcome to your cash register!"); //welcome message

        while (takingOrders){  //while the user is still handling orders

            System.out.println("What is the price of the item being purchased?"); //takes in the price of the item as a double

            double i = -1.0; //variable to handle try catch

            while (i <0){ //while the user hasn't entered a negative number
                try{
                    i = scanner.nextDouble(); //gets the user's input
                }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                    String badInput = scanner.next();
                    System.out.println("Please enter a valid, positive number");
                    continue; //tells code to move past the error

                }
            }

            double itemPrice = i; //sets the items price to the users input

            System.out.println("How many twenties is the customer paying with?"); //gets a valid user input for each type of bill and coin
            int twenties = getValidInput();

            System.out.println("How many tens is the customer paying with?");
            int tens = getValidInput();

            System.out.println("How many fives is the customer paying with?");
            int fives = getValidInput();

            System.out.println("How many ones is the customer paying with?");
            int ones = getValidInput();

            System.out.println("How many quarters is the customer paying with?");
            int quarters = getValidInput();

            System.out.println("How many dimes is the customer paying with?");
            int dimes = getValidInput();

            System.out.println("How many nickels is the customer paying with?");
            int nickels = getValidInput();

            System.out.println("How many pennies is the customer paying with?");
            int pennies = getValidInput();

            handleSale(twenties,tens,fives,ones,quarters, dimes, nickels, pennies, itemPrice); //tells register to handle sale, is either succesful or not

            double totalInRegister = checkCashInRegister(); //gets the amount in the register

            System.out.println("You have a total of " + (double)(totalInRegister/100.0) + " in the register"); //prints the value in the register, repromts for a new sale

            System.out.println("Would you like to enter another sale? Enter 1 for yes and 0 for no");

            int response = getValidInput();  //gets users response

            if (response == 0){
                takingOrders = false; //exits while loop
            }


        }
    }




}

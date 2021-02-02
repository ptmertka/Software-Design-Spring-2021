import java.util.Random;


public class Register {
    //creates a Random object in order generate a random number of bills and change in the drawer.
    Random random1 = new Random();

    int numTwenties = random1.nextInt(16); //randomly generate value between 0-15, represents number of 20's in register
    int numTens = random1.nextInt(16); //randomly generate value between 0-15, represents number of 10's in register
    int numFives = random1.nextInt(16); //randomly generate value between 0-15, represents number of 5's in register
    int numOnes = random1.nextInt(16); //randomly generate value between 0-15, represents number of 1's in register
    int numQuarters = random1.nextInt(16); //randomly generate value between 0-15, represents number of quarters in register
    int numDimes = random1.nextInt(16); //randomly generate value between 0-15, represents number of dimes in register
    int numNickels = random1.nextInt(16); //randomly generate value between 0-15, represents number of nickels in register
    int numPennies = random1.nextInt(16); //randomly generate value between 0-15, represents number of pennies in register

    double checkCashInRegister(){
        double totalInRegister = 0.0;
        totalInRegister = totalInRegister + (numTwenties * 20); //every line takes the number of each item in register and multiplies it by its value
        totalInRegister = totalInRegister + (numTens * 10); //By doing this, it gets the cash value in the register, adding it to a running total
        totalInRegister = totalInRegister + (numFives * 5);
        totalInRegister = totalInRegister + (numOnes);
        totalInRegister = totalInRegister + (numQuarters * .25);
        totalInRegister = totalInRegister + (numDimes * .1);
        totalInRegister = totalInRegister + (numNickels * .05);
        totalInRegister = totalInRegister + (numPennies * .01);
        return totalInRegister; //returns the running total

    }
    void returnChange(double amountOwed){

        boolean canCompute = true; //boolean temp variable that tells the loop if there is enough of the right money in the register to give exact change
        int twentiesUsed = 0; // all of these int variables are used to track how much of each denomination is used in producing change
        int tensUsed = 0;
        int fivesUsed = 0;
        int onesUsed = 0;
        int quartersUsed = 0;
        int dimesUsed = 0;
        int nickelsUsed = 0;
        int penniesUsed = 0;



            if (amountOwed >= 20) { //for each repeated chunk, it checks to see if the denomination of bill can be used to lower the amount owed

                for (int i = 1; i <= numTwenties; i++) { //if it can, it checks to see how many of each bill to use, using a for loop
                    //if any amount of a denomination is 0, the loop simply doesn't execute, if there is 1 or more bills, it goes through 1-number available, until a number gets
                    //you to a level where the amount remaining when that many bills is subtracted off is less than the bill value itself, or all of the bill/coin is used.

                    if (((amountOwed - (i * 20) >= 0) && (amountOwed - (i * 20) < 20)) || (i == numTwenties)) {//once this condition is met, where a bill can be used for change
                        twentiesUsed = i; //sets the number of bills being used to i
                        amountOwed = amountOwed - (20 * i); //subtracts off the number of bills being used value to lower the total amount owed
                        break; //once this value is found, breaks out of the loop to prevent unecessary iterations.
                    }
                }
            } //this process is repeated for every value of change in the cash register

            if (amountOwed >= 10) {
                for (int i = 1; i <= numTens; i++) {
                    if (((amountOwed - (i * 10) > 0) && (amountOwed - (i * 10) < 10)) || (i == numTens)) {
                        tensUsed = i;
                        amountOwed = amountOwed - (10 * i);
                        break;
                    }
                }
            }
            if (amountOwed >= 5){
                for (int i = 1; i <= numFives; i++) {
                    if (((amountOwed - (i * 5) > 0) && (amountOwed - (i * 5) < 5)) || (i == numFives)) {
                        fivesUsed = i;
                        amountOwed = amountOwed - (5*i);
                        break;
                    }
                }
            }
            if (amountOwed >= 1){
                for (int i = 1; i <= numOnes; i++) {
                    if (((amountOwed - (i * 1) > 0) && (amountOwed - (i * 1) < 1)) || (i == numOnes)) {
                        onesUsed = i;
                        amountOwed = amountOwed - (1*i);
                        break;

                    }
                }
            }

            if (amountOwed >= .25){
                for (int i = 1; i <= numQuarters; i++) {
                    if (((amountOwed - (i * .25) > 0) && (amountOwed - (i * .25) < .25)) || (i == numQuarters)) {
                        quartersUsed = i;
                        amountOwed = amountOwed - (.25*i);
                        break;

                    }
                }
            }
            if (amountOwed >= .10){
                for (int i = 1; i <= numDimes; i++) {
                    if (((amountOwed - (i * .1) > 0) && (amountOwed - (i * .1) < .1)) || (i == numDimes)) {
                        dimesUsed = i;
                        amountOwed = amountOwed - (.1*i);
                        break;

                    }
                }
            }

            if (amountOwed >= .05){
                for (int i = 1; i <= numNickels; i++) {
                    if (((amountOwed - (i * .05) > 0) && (amountOwed - (i * .05) < 5)) || (i == numNickels)) {
                        nickelsUsed = i;
                        amountOwed = amountOwed - (.05*i);
                        break;

                    }
                }
            }

            if (amountOwed >= .01){
                for (int i = 1; i <= numPennies; i++) {
                    if (((amountOwed - (i * .01) > 0) && (amountOwed - (i * .01) < .01)) || (i == numPennies)) {
                        penniesUsed = i;
                        amountOwed = amountOwed - (.01*i);
                        break;

                    }
                }
            }

            if( amountOwed > 0.01){ //if there is still an amount owed left greater than a penny, there is insufficient bills in the register to give exact change
                canCompute = false; //sets canCompute to false as a flag to the inability to calculate change
            }



        if (canCompute == false){ //if the register cannot return change, it displays an error explaining so
            System.out.println("I'm sorry, your change can cannot be calculated due to lack of proper bills");
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




}

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver to handle the execution/ printing, and user input for the Scoreboard classes that consist of sport and their subclasses
 * @author Peter Mertka
 * @version 2/18/2021
 */
public class ScoreboardDriver {

    /**
     * Gets a valid user input based on the sport and its bounds of valid inputs
     * @param bounds Ing array: The min and max value for the validn inputs for the sports
     * @return int: the chosen valid value of th user
     */
    public static int getValidInputInt(int [] bounds){
        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs
        while ((i < bounds[0]) || (i > bounds[1])){ //while the user hasn't entered an input in the valid range of the sport
            try{
                i = scanner.nextInt(); //gets the user's input
            }catch(InputMismatchException e){ //if the user enters an invalid input, return an error, get a new number
                String badInput = scanner.next();
                System.out.println("Please enter a valid number within the bounds of the options");
                continue; //move past bad input

            }

        }
        return i;
    }

    /**
     * Gets a string input of the user so that they can name the teams
     * @return String: whatever string the user chooses for a team's name
     */
    public static String getStringInput(){
        String a = "";
        Scanner scanner = new Scanner(System.in); //creates scanner object and string to store input
        a = scanner.nextLine(); //gets the next line, and then returns it, no error checking since user can enter anything for name of steams
        return a;
    }


    public static void main(String [] args){

       System.out.println("Welcome! What sport would you like to run?"); //prints out opening messages, and has user choose the type of game they want and the team names
       System.out.println("1. Football \n 2. Basketball \n 3. Hockey \n 4. Baseball");

       int [] bounds = {1,4}; //creates valid bounds of 1-4
       int choice = getValidInputInt(bounds);
       System.out.println("Please enter the name of the home team");
       String homeTeam = getStringInput();
       System.out.println("Please enter the name of the away team:");
       String awayTeam = getStringInput();

       Sport sport1 = new Football(homeTeam, awayTeam); //makes a sport object to be assigned a specific reference based on the user's chocie
       if(choice ==1){
           sport1 = new Football(homeTeam,awayTeam);
       }
       else if (choice == 2){
           sport1 = new Basketball(homeTeam, awayTeam);
       }
       else if (choice == 3){
           sport1 = new Hockey(homeTeam, awayTeam);
       }
       else if (choice == 4){
           sport1 = new Baseball(homeTeam,awayTeam);
       }


       while(sport1.getGameOver()){
           System.out.println(sport1.displayScore()); //prints out the score, time, and user options
           System.out.println(sport1.displayPeriod());
           System.out.println(sport1.displayOptions());


           System.out.println("What would you like to choose?");
           choice = getValidInputInt(sport1.getInputBounds()); //gets a valid input based on the sports bounds

           sport1.applyScoreChoice(choice); //inside this method, game will evenutally end by user choice once things happen in the normal order of the game



       }

       System.out.println("Thank you for playing");


    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class Football extends Sport{


    /**
     * Class constructor for Class Football, setting values based on user inputs
     * @param home String: the name of the home team to be set
     * @param away String: the name of the away team to be set
     */
    public Football(String home, String away){
        setAwayTeam(away); //passes the strings back up to the parent classes instance variables
        setHomeTeam(home);
        String [] ScoringMethods = {"Touchdown", "Extra Point", "Two Point Conversion", "Safety", "Field goal"}; //specific string array sets the scoring methods
        setScoringMethods(ScoringMethods);
    }

    @Override
    public void advanceTimePeriod() {
        setPeriod(getPeriod() + 1); //increments the period by one, in this case by quarters

        if((getPeriod() > 4) && (getAwayScore() != getHomeScore())){ //if the game ends in regulation and there is not a tie
            setGameOver(true); //end the game
        }
        else if(getPeriod() > 7){
            setGameOver(true); //Only allows 3OT's per game
        }


    }

    /**
     * Gets a valid user input based on the number of options available for scoring in the football game
     * @return int: the valid user input of their choice
     */
    @Override
    public int getValidInput() {

        int i = -1;
        Scanner scanner = new Scanner(System.in); //scanner to handle inputs
        while ((i < 1) || (i > 11)){ //while the user hasn't entered a number less than 1 or greater than 11
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
     * Takes the user's choice of scoring method and applies its functionaliy based on the scoring action in football
     * @param n Int:  the choice of the user of what to do with the game
     */
    @Override
    public void applyScoreChoice(int n) {
        if (n == 1){
            setHomeScore(getHomeScore() + 6); //adds 6 points to the home team for a touchdown
        }
        else if (n == 2){
            setHomeScore(getHomeScore() + 1); //adds 1 point to the home team for an extra point
        }
        else if (n==3 || n==4){
            setHomeScore(getHomeScore() + 2); //adds 2 points to the home team for either a safety or two point conversion

        }
        else if (n == 5){
            setHomeScore(getHomeScore() + 3); //adds 3 points to the home team for a field goal
        }
        else if(n ==6){
            setAwayScore(getAwayScore() + 6); //adds 6 points to the away team for a touchdown
        }
        else if(n == 7){
            setAwayScore((getAwayScore() + 1)); //adds 1 point to the away team for an extra point
        }
        else if (n == 8 || n == 9){
            setAwayScore(getAwayScore() + 2); //adds 2 points to the away team for either a two point conversion or a safety
        }
        else if(n == 10){
            setAwayScore(getAwayScore() + 3); //adds 3 points to the away team for a field goal
        }
        else{
            advanceTimePeriod(); //increments the period of time, which will be option 11
        }

    }

    /**
     * Shows all of the scoring options and time options based on scoring methods and rules of football
     */
    @Override
    public void displayOptions() {
        String[] scoringMethods  = getScoringMethods(); // gets the array of scoring methods so that they can be printed off

        for(int i = 0; i < scoringMethods.length; i++){
            System.out.println(i + ". " + getHomeTeam() + " " + scoringMethods[i]);
        }
        System.out.println("11. Advance to next quarter");
    }

    /**
     * Displays the time period of the game, with specific cathces for the game being over, and overtimes
     */
    @Override
    public void displayPeriod() {

        if(getPeriod() <= 4){ //handles normal quarters
            System.out.println("Quarter " + getPeriod());
        }
        else if(getGameOver()){ //prints if game is over
            System.out.println("Game Over");
        }
        else if(getPeriod() == 5){ //handles the next 3 overtimes
            System.out.println("First Overtime");
        }
        else if(getPeriod() == 6){
            System.out.println("Second Overtime");
        }
        else{
            System.out.println("Third Overtime");
        }

    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class Football
 * Holds sport specifc implementations of both the timing system (quarters) and scoring system (touchdowns, field goals etc) of the sport of football
 * @author Peter Mertka
 * @version 2/16/2021
 */
public class Football extends Sport{


    /**
     * Class constructor for Class Football, setting values based on user inputs
     * @param home String: the name of the home team to be set
     * @param away String: the name of the away team to be set
     */
    public Football(String home, String away){
        setAwayTeam(away); //passes the strings back up to the parent classes instance variables
        setHomeTeam(home);
        setPeriodLength("15 Minutes"); //sets the period length
        int []  validBounds = {1, 11}; //valid choices for the user
        setInputBounds(validBounds);
        String [] ScoringMethods = {"Touchdown", "Extra Point", "Two Point Conversion", "Safety", "Field goal"}; //specific string array sets the scoring methods
        setScoringMethods(ScoringMethods);
    }

    /**
     * Moves the games time period forward, while also ending the game when necessary
     */
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
     * returns a string showing all of the scoring options and time options based on scoring methods and rules of football
     * @return String: a message showing all possible scoring/timekeeping options for the user
     */
    @Override
    public String displayOptions() {
        String a = ""; //empty String to return
        String[] scoringMethods  = getScoringMethods(); // gets the array of scoring methods so that they can be printed off

        for(int i = 0; i < scoringMethods.length; i++){ //adds the scoring methods for the home team to the return string
            a = a + i + 1 + ". " + getHomeTeam() + " " + scoringMethods[i] + "\n";
        }
        for(int j = 0; j < scoringMethods.length; j++){ //adds the scoring methods for the away team to the return string
            a = a + j + 6 + ". " + getAwayTeam() + " " + scoringMethods[j] + "\n";
        }
        a = a + "11. Advance to next quarter\n"; // adds the quarter advance option to the return string

        return a;
    }

    /**
     * Returns a string that displays the time period of the game, with specific cathces for the game being over, and overtimes
     * @return String: A message of what time period the game is in
     */
    @Override
    public String displayPeriod() {

        String a = "";

        if(getPeriod() <= 4){ //handles normal quarters
            a = a + "Quarter " + getPeriod() + "\n";
        }
        else if(getGameOver()){ //prints if game is over
            a = a + "Game Over\n";
        }
        else if(getPeriod() == 5){ //handles the next 3 overtimes
            a = a + "First Overtime\n";
        }
        else if(getPeriod() == 6){
            a = a + "Second Overtime\n";
        }
        else{
            a = a + "Third OVertime\n";
        }
        return a;
    }

}

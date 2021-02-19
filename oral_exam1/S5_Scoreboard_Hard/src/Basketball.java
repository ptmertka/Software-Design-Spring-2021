/**
 * Class Basketball
 * Contains the specific implementation of the scoring rules and timing system of the game of basketball
 * @author Peter Mertka
 * @version 2/16/2021
 */
public class Basketball extends Sport{

    /**
     * Class Constructor, creates a new basketball object by setting the super class variables with a specific value for scoringMethods
     * @param home String: name of the home team
     * @param away String: name of the away team
     */
    public Basketball(String home, String away){ //initializes the superclass variables by using getters and setters,
        super(); //calls parent constructor
        setHomeTeam(home); //get set methods used over having a constructor in Sport so that scoringMethods can be specified within the object itself
        setAwayTeam(away); //cannot create or use a specified value of class basketball without calling contructor in sport if it existed, so that is why no default construct exists in Sport
        setPeriodLength("15 Minutes");
        int [] validBounds = {1, 9}; //specific valid inputs for the basketball scoring options
        setInputBounds(validBounds);
        String [] scoreMethods = {"3 point shot", "2 point shot","Layup", "Free Throw"};
        setScoringMethods(scoreMethods);
    }

    /**
     * Shows the time period of the game being played, either one of the 4 quarters, one of 3 OTs, or game over
     */
    @Override
    public void advanceTimePeriod() {

        setPeriod(getPeriod() + 1); //increments the period by 1 when asked to

        if((getPeriod() > 4) && (getHomeScore() != getAwayScore())){ //if the period advances to 5 and the game isn't tied, the game is over
            setGameOver(true);
        }
        else if (getPeriod() > 7){ //if the game is tied after 3 overtimes, game will end
            setGameOver(true);
        }

    }

    /**
     * Takes in the users choice and applies the correct scoring method or option
     * @param n Int:  the choice of the user of what to do with the game
     */
    @Override
    public void applyScoreChoice(int n) {
        if (n == 1){
            setHomeScore(getHomeScore() + 3); //adds 3 points to the home team for a 3 pointer
        }
        else if (n == 2 || n==3){
            setHomeScore(getHomeScore() + 2); //adds 2 points to the home team for an 2 point shot or lay up
        }
        else if (n==4){
            setHomeScore(getHomeScore() + 1); //adds 1 point to the home team for a free throw

        }
        else if (n == 5){
            setAwayScore(getAwayScore() + 3); //adds 3 points to the away team for a 3 pointer
        }
        else if(n ==6 || n==7){
            setAwayScore(getAwayScore() + 2); //adds 2 points to the away team for a 2 point shot or a lay up
        }
        else if(n == 8){
            setAwayScore((getAwayScore() + 1)); //adds 1 point to the away team for an free throw
        }
        else if (n == 9){
            advanceTimePeriod(); //advances the game to the next quarter
        }


    }
    /**
     * Returns a string showing all of the scoring options and time options based on scoring methods and rules of basketball
     * @return String: a message showing all the options for the user to select
     */
    @Override
    public String displayOptions() {
        String a = ""; //empty String to return
        String[] scoringMethods  = getScoringMethods(); // gets the array of scoring methods so that they can be printed off

        for(int i = 0; i < scoringMethods.length; i++){ //adds the scoring methods for the home team to the return string
            a = a + (i + 1) + ". " + getHomeTeam() + " " + scoringMethods[i] + "\n";
        }
        for(int j = 0; j < scoringMethods.length; j++){ //adds the scoring methods for the away team to the return string
            a = a + (j + 5) + ". " + getAwayTeam() + " " + scoringMethods[j] + "\n";
        }
        a = a + "9. Advance to next quarter \n"; // adds the quarter advance option to the return string

        return a;
    }

    /**
     * Displays the time period of the game, with specific catches for the game being over, and overtimes
     * @return String: the message showing what time period that game is at
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
            a = a + "Third Overtime\n";
        }
        return a;
    }

}

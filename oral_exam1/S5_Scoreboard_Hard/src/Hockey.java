/**
 * Holds the overwritten rules of the sport of hockey so that scoring and timekeeping adhere to the sports rules
 * @author Peter Mertka
 * @version 1/17/2021
 */
public class Hockey extends Sport{

    /**
     * Class constructor for Class Hockey, setting values based on user inputs
     * @param home String: the name of the home team to be set
     * @param away String: the name of the away team to be set
     */
    public Hockey(String home, String away){
        setHomeTeam(home); //sets the home and away teams, the period length, and specific scoring methods
        setAwayTeam(away);
        setPeriodLength("20 Minutes");
        int [] validBounds = {1,9};
        setInputBounds(validBounds); //sets the specifc bounds for a valid input in the hockey calss
        String [] scoreMethods = {"Goal", "Powerplay Goal", "Shorthanded Goal", "Empty Netter"};
        setScoringMethods(scoreMethods);
    }


    /**
     * Moves the games time period forward by 1 period, ending the game when necessary
     */
    @Override
    public void advanceTimePeriod() {
        setPeriod(getPeriod() + 1); //advances to the next period

        if ((getPeriod() > 3) && getAwayScore() != getHomeScore() ){ //if the game finishes at the end of the 3rd period, and the score is not tied, ends the game
            setGameOver(true);
        }
        else if(getPeriod() > 6){ //only allows 3OTs for a game
            setGameOver(true);
        }
    }

    /**
     * Taking a users input, applies the correct scoring method or timekeeping option
     * @param n Int:  the choice of the user of what to do with the game
     */
    @Override
    public void applyScoreChoice(int n) {

        if (n <= 4){
            setHomeScore(getHomeScore() + 1); //adds a goal to the home team in whatever manner the user wants
        }
        else if(n <= 8){
            setAwayScore(getAwayScore() +1);//adds a goal to the away team in whatever manner the user wants
        }
        else if (n==9){
            advanceTimePeriod(); //moves the game ahead one period
        }


    }

    /**
     * Returns a string of all the possible options or scorng/timekeeping
     * @return String: the message of all possible options for the user to select
     */
    @Override
    public String displayOptions() {
        String a = "";

        String[] scoringMethods  = getScoringMethods(); // gets the array of scoring methods so that they can be printed off

        for(int i = 0; i < scoringMethods.length; i++){ //adds the scoring methods for the home team to the return string
            a = a + i + 1 + ". " + getHomeTeam() + " " + scoringMethods[i] + "\n";
        }
        for(int j = 0; j < scoringMethods.length; j++){ //adds the scoring methods for the away team to the return string
            a = a + j + 5 + ". " + getAwayTeam() + " " + scoringMethods[j] + "\n";
        }
        a = a + "9. Advance to next quarter \n"; // adds the quarter advance option to the return string

        return a;
    }

    /**
     * Returns a string that shows what time period the game is in
     * @return String:  a message showing what time period the game is in
     */
    @Override
    public String displayPeriod() {
        String a = "";

        if(getPeriod() <= 3){ //handles normal quarters
            a = a + "Quarter " + getPeriod() + "\n";
        }
        else if(getGameOver()){ //prints if game is over
            a = a + "Game Over\n";
        }
        else if(getPeriod() == 4){ //handles the next 3 overtimes
            a = a + "First Overtime\n";
        }
        else if(getPeriod() == 5){
            a = a + "Second Overtime\n";
        }
        else{
            a = a + "Third Overtime\n";
        }
        return a;
    }
}

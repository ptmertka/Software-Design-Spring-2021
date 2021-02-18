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
        setHomeTeam(home); //get set methods used over having a constructor in Sport so that scoringMethods can be specified within the object itself
        setAwayTeam(away); //cannot create or use a specified value of class basketball without calling contructor in sport if it existed, so that is why no default construct exists in Sport
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

    @Override
    public int getValidInput() {
        return 0;
    }

    @Override
    public void applyScoreChoice(int n) {

    }

    @Override
    public void displayOptions() {

    }

    @Override
    public void displayPeriod() {

    }
}

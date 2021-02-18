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

    @Override
    public void applyScoreChoice(int n) {

        if (n <= 4){
            setHomeScore(getHomeScore() + 1); //adds a goal to the home team in whatever manner the user wants
        }


    }

    @Override
    public String displayOptions() {
        return null;
    }

    @Override
    public String displayPeriod() {
        return null;
    }
}

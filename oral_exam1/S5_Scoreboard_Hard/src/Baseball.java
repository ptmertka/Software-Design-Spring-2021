public class Baseball extends Sport{

    /**
     * Integer: representing the number of outs in an inning
     */
    private int outs = 0;

    /**
     * Boolean: telling whether or not a runner is on first or not
     */
    private boolean onFirst = false;

    /**
     * Boolean: telling whether or not a runner is on second
     */
    private boolean onSecond = false;

    /**
     * Boolean: telling wheter or not a runner is on third
     */
    private boolean onThird = false;

    /**
     * Boolean: telling whether or not it is the top of an inning, starts of true
     */
    private boolean topOfInning = true;

    public Baseball(String home, String away){
        setHomeTeam(home);
        setAwayTeam(away); //sets the home and away teams, the period length, and the unique scoring methods of baseball
        setPeriodLength("3 outs");
        String [] scoreMethods = {"Single", "Double", "Triple", "Homerun", "Strikeout", "Flyout", "Lineout", "Popout", "Walk"};
        int [] inputBound = {1,9};
        setInputBounds(inputBound); //sets the input bounds for the user input
        setScoringMethods(scoreMethods);

    }

    /**
     * Advances the inning of the game as long as there 3 outs, and ends the game when conditions are met
     */
    @Override
    public void advanceTimePeriod() {

        if (outs == 3){ //inning can only advance if 3 outs are gotten

            if (topOfInning){ //if its the top of the inning, switches to the bottom
                topOfInning = false;
            }
            else{ //otherwise, it goes back to the top of the iinning and advances the inning
                topOfInning = true;
                setPeriod(getPeriod() + 1);
            }
            if (getPeriod() >=9 && (getAwayScore() < getHomeScore()) && (topOfInning == false)){ //if the home team is winning in the bottom of the 9th or later, game is over
                setGameOver(true);
            }
            else if(topOfInning && getPeriod() >= 10 && getAwayScore() > getHomeScore()){ //if the game is past the bottom of the 9th and the away team is leading, the away team wins, game is over
                setGameOver(true);

            }


        }



    }

    /**
     * Takes in the user's input ant applies the correct game action, moving runners as well
     * @param n Int:  the choice of the user of what to do with the game
     */
    @Override
    public void applyScoreChoice(int n) {
        int runsScored = 0;
        if (n==1){
            if(onThird && onSecond && onFirst){
                runsScored = 1;
            }
            else if (onThird && onSecond && !onFirst){
                onFirst = true;
            }
            else if (!onThird && onSecond && onFirst){
                onThird = true;
            }
            else if (onThird && !onSecond && onFirst){
                onSecond = true;
            }
            else if (onThird && !onSecond && !onFirst){
                onFirst = true;
            }
            else if (!onThird && onSecond && !onFirst){
                onFirst = true;
            }
            else if(!onThird && !onSecond && onFirst){
                onSecond = true;
            }
            else{
                onFirst = true;
            }
        }
        if (n==2){
            if(onThird && onSecond && onFirst){
                runsScored = 2;
                onFirst = false;
            }
            else if (onThird && onSecond && !onFirst){
                runsScored = 2;
                onThird = false;
            }
            else if (!onThird && onSecond && onFirst){
                runsScored = 1;
                onThird = true;
                onFirst = false;
            }
            else if (onThird && !onSecond && onFirst){
                runsScored = 1;
                onSecond = true;
                onFirst = false;
            }
            else if (onThird && !onSecond && !onFirst){
                runsScored = 1;
                onThird = false;
                onSecond = true;
            }
            else if (!onThird && onSecond && !onFirst){

                runsScored = 1;
            }
            else if(!onThird && !onSecond && onFirst){
                onSecond = true;
                onThird = true;
                onFirst = false;
            }
            else{
                onSecond = true;
            }
        }
        if(n==3){
            if(onThird && onSecond && onFirst){
                runsScored = 3;
                onFirst = false;
                onSecond = false;
            }
            else if (onThird && onSecond && !onFirst){
                runsScored = 2;
                onSecond =false;
            }
            else if (!onThird && onSecond && onFirst){
                runsScored = 2;
                onThird = true;
                onFirst = false;
                onSecond = false;
            }
            else if (onThird && !onSecond && onFirst){
                runsScored = 2;
                onSecond = false;
                onFirst = false;
            }
            else if (onThird && !onSecond && !onFirst){
                runsScored = 1;
                onSecond = false;
            }
            else if (!onThird && onSecond && !onFirst){

                runsScored = 1;
                onSecond = false;
                onThird = false;
            }
            else if(!onThird && !onSecond && onFirst){
                runsScored = 1;

                onThird = true;
                onFirst = false;
            }
            else{
                onThird = true;
            }
        }
        if(n==4){
            if(onThird && onSecond && onFirst){
                runsScored = 4;
                onFirst = false;
                onSecond = false;
                onThird = false;
            }
            else if (onThird && onSecond && !onFirst){
                runsScored = 3;
                onThird = false;
                onSecond = false;
            }
            else if (!onThird && onSecond && onFirst){
                runsScored = 3;
                onSecond = false;
                onFirst = false;
            }
            else if (onThird && !onSecond && onFirst){
                runsScored = 3;
                onThird = false;
                onFirst = false;
            }
            else if (onThird && !onSecond && !onFirst){
                runsScored = 2;
                onThird = false;

            }
            else if (!onThird && onSecond && !onFirst){

                runsScored = 2;
                onSecond = false;
            }
            else if(!onThird && !onSecond && onFirst){
                runsScored = 2;
                onFirst = false;
            }
            else{
                runsScored = 1;
            }
        }
        else if (n>=5 && n<=8){
            outs = outs + 1;
        }
        else if (n == 9){
            if(onThird && onSecond && onFirst){
                runsScored = 1;

            }
            else if (onThird && onSecond && !onFirst){

                onFirst = true;
            }
            else if (!onThird && onSecond && onFirst){
                onThird = true;

            }
            else if (onThird && !onSecond && onFirst){

                onSecond = true;

            }
            else if (onThird && !onSecond && !onFirst){
                onFirst = true;
            }
            else if (!onThird && onSecond && !onFirst){

                onFirst = true;
            }
            else if(!onThird && !onSecond && onFirst){
                onSecond = true;

            }
            else{
                onFirst = true;
            }
        }

    }

    /**
     * Displays the game options for the user for the game of baseball, displaying only the team that is batting
     * @return
     */
    @Override
    public String displayOptions() {
        String teamNameToUse = "";
        String a = "";
        if(topOfInning){
            teamNameToUse = getAwayTeam(); //shows which team is batting based on the part of the inning
        }
        else{
            teamNameToUse = getHomeTeam();
        }

        for (int i = 0; i < getScoringMethods().length ; i++){ // for every option for scoring
            a =a + i + ". " + getScoringMethods()[i] + "\n";
        }

        return a;
    }

    /**
     * Overridden display score to display more detailed score for the game, including outs and baserunners.
     * @return
     */
    @Override
    public String displayScore() {
        return super.displayScore();
    }

    @Override
    public String displayPeriod() {
        return null;
    }
}

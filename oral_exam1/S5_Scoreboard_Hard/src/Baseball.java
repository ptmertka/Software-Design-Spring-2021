/**
 * Holds the overwritten methods specific to the game of baseball, along with new instance variables
 * @author Peter Mertka
 * @version 2/18/2021
 */
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
        super();
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
                outs = 0;//resets the number of outs
                onFirst = false; //clears the bases between innings
                onSecond = false;
                onThird = false;
            }
            else{ //otherwise, it goes back to the top of the iinning and advances the inning
                topOfInning = true;
                outs = 0; //resets number of outs
                onFirst = false; //clears the bases between innings
                onSecond = false;
                onThird = false;
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
        if (topOfInning){
            setAwayScore(getAwayScore() + runsScored); //adds the runs scored to the proper team based on which one is batting
        }
        else{
            setHomeScore(getHomeScore() + runsScored);
        }
        advanceTimePeriod(); //checks if inning is over

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
            a =a + (i+1) + ". " + getScoringMethods()[i] + "\n";
        }

        return a;
    }

    /**
     * Overridden display score to display more detailed score for the game, including outs and baserunners.
     * @return String showing the score of the game, along with the runners on base, and outs
     */
    @Override
    public String displayScore() {


        String a = "";

        a = a + getHomeTeam() + ": " + getHomeScore() + " - " + getAwayTeam() + ": " +getAwayScore() + " \n"; //displays the score, the outs in the inning and what runners are on base
        a = a + "There are " + outs + " outs in the inning \n";
        if(onFirst){
            a = a + "There is a runner on first \n";
        }
        if(onSecond){
            a = a + "There is a runner on second \n";
        }
        if(onThird){
            a = a + "There is a runner on third \n";
        }

        //this is the base displayScore with now the added functionality above



        if(getGameOver()){
            if (getHomeScore() >getAwayScore()){
                a = a + getHomeTeam() + " Wins!\n";
            }
            else if (getAwayScore() > getHomeScore()){
                a = a + getAwayTeam() + " Wins!\n";
            }

        }
        return a;



    }

    /**
     * Shows what the period of the game is
     * @return
     */
    @Override
    public String displayPeriod() {
        String a = "";
        if (topOfInning){
            a = a +"It is the top of inning number " + getPeriod() + "\n"; //tells you which inning it is and whether or not its the top of th einning or not
            a = a + getAwayTeam() + " are up to bat\n";
        }
        else{
            a =a + "It is the bottom of inning number " + getPeriod() + "\n";
            a = a + getHomeTeam() + " are up to bat\n";
        }
        return a;
    }
}

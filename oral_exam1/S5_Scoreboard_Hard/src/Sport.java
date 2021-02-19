/**
 * Class Sport
 * Holds the basic outline and generic form for all the sports classes. Holds getters and setters for basic instance variables
 * Implementation of method functionality will be present in subclasses, thus class Sport is an abstract class
 * @author Peter Mertka
 * @version 2/15/2021
 */




public abstract class Sport {


    /**
     * String: The name the home team
     */
    private String homeTeam;

    /**
     * String: The name of the away team
     */

    private String awayTeam;

    /**
     * Integer: The score of the home team, always begins at zero
     */

    private int homeScore ;

    /**
     * Integer: The score of the away team, always begins at zero
     */

    private int awayScore;

    /**
     * Integer: The time period of the game being played, wheter it is a quarter, a period, etc, always begins at one
     */

    private int period;

    /**
     * String: The length of the period in the sport, either a time or outs for baseball
     */

    private String periodLength;

    /**
     * String array[]: An array of strings representing the methods of scoring for each sport
     */

    private String[] scoringMethods;

    /**
     * Boolean: holds whether the game is over or not, default is false
     */

    private Boolean gameOver = false;

    /**
     * Integer Array: pair of values that represent the minimum and maximum value for the scanner when picking values based on sport
     */

    private int[] inputBounds;

    public Sport(){
        awayScore = 0;
        homeScore = 0;
        gameOver = false;
        period = 1;
    }
    /**
     * Getter for the name of the home team
     * @return String: Name of the home team
     */
    public String getHomeTeam(){
        return homeTeam;
    }

    /**
     * Setter for the name of the home team
     * @param name String: chosen by the user to name the home team
     */
    public void setHomeTeam(String name){
        homeTeam = name;
    }

    /**
     * Getter for the name of the away team
     * @return String: name of the away team
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * Setter for the name of the away team
     * @param name String: name of the away team as chosen by the user
     */
    public void setAwayTeam(String name) {
        awayTeam = name;
    }

    /**
     * Getter for the score of the home team
     * @return int: the score of the home team
     */
    public int getHomeScore() {
        return homeScore;
    }

    /**
     * Setter for the homeScore, allows child class to augment score
     * @param n int: what to change score to
     */
    public void setHomeScore(int n){
        homeScore = n;
    }

    /**
     * Setter for the awayScore, allows child class to augment score
     * @param n int: what to change the score to
     */
    public void setAwayScore(int n){
        awayScore = n;
    }

    /**
     * Getter for the score of the away team
     * @return int: score of the away team
     */
    public int getAwayScore() {
        return awayScore;
    }

    /**
     * Getter for the period of the game at the time
     * @return int: The current time period of the game as it is occuring
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Setter for the period of the game, allowing child classes to update the time of the game
     * @param periodOfGame int: the next period to be set in the game
     */
    public void setPeriod(int periodOfGame) {
        period = periodOfGame;
    }

    /**
     * Getter for the period length of the sport
     * @return String: the period length of the sport
     */
    public String getPeriodLength() {
        return periodLength;
    }

    /**
     * Setter for the period length of the sport
     * @param period String: the length of the period of the sport
     */
    public void setPeriodLength(String period) {
        periodLength = period;
    }

    /**
     * Getter for the gameOver Boolean
     * @return Boolean: True or False as to whether the game is over or not
     */
    public Boolean getGameOver() {
        return gameOver;
    }

    /**
     * Setter for the gameOver boolean
     * @param x boolean: allows for the game being over to be set
     */
    public void setGameOver(boolean x){
        gameOver = x;

    }

    /**
     * Getter for the list of scoring methods, each list will be sport specific
     * @return A list of strings, storing the ways to score in the sport.
     */
    public String[] getScoringMethods() {
        return scoringMethods;
    }

    /**
     * Setter for the scoringMethods instance variable, allowing the child class to set the string of
     * @param howToScore
     */
    public void setScoringMethods(String[] howToScore){
        scoringMethods = howToScore;

    }

    /**
     * Getter for the InputBounds of a specific sport
     * @return Int Array: The upper and lower bounds of the valid inputs of a sport
     */
    public int[] getInputBounds() {
        return inputBounds;
    }

    /**
     * Setter for the inputBounds of a sport
     * @param inputBounds Int Array: The array containing the bounds of valid input
     */
    public void setInputBounds(int[] inputBounds) {
        this.inputBounds = inputBounds;
    }

    /**
     * Generic function used to display the score of the game in one neat line
     */
    public String displayScore(){

        String i = "";
        i = i + "Score: " + homeTeam + ": " + Integer.toString(homeScore) + " - " + awayTeam + ": " + Integer.toString(awayScore) + "\n";


        if(gameOver){
            if (homeScore > awayScore){
                i = i + homeTeam + " Wins!\n";
            }
            else if (awayScore > homeScore){
                i = i + awayTeam + " Wins!\n";
            }
            else{
               i = i + "The game has ended in a tie\n";
            }
        }

        return i;
    }

    /**
     * Abstract function that will advance the time period of the game, specific to each game's rules
     */
    public abstract void advanceTimePeriod();

    /**
     * Abstract function that will take a user's choice and apply the proper functionality to the score/game
     * @param n Int:  the choice of the user of what to do with the game
     */
    public abstract void applyScoreChoice(int n);

    /**
     * Abstract function that will display to the user their options for interacting with the game, made abstract due to differences betwen games
     */
    public abstract String displayOptions();

    /**
     * Abstract function that will display the period of the game, but within each class, will allow for specific changes based on rules of games
     */
    public abstract String displayPeriod();


}

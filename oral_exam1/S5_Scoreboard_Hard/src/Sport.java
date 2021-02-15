/**
 * Class Sport
 * Holds the basic outline and generic form for all the sports classes. Holds getters and setters for basic instance variables
 * Implementation of method functionality will be present in subclasses
 * @autor Peter Mertka
 * @version 2/15/2021
 */




public class Sport {

    /**
     * String: The name the home team
     */
    private String homeTeam;

    /**
     * String: The name of the away team
     */

    private String awayTeam;

    /**
     * Integer: The score of the home team
     */

    private int homeScore;

    /**
     * Integer: The score of the away team
     */

    private int awayScore;

    /**
     * Integer: The time period of the game being played, wheter it is a quarter, a period, etc
     */

    private int period;

    /**
     * String array[]: An array of strings representing the methods of scoring for each sport
     */

    private String[] scoreingMethods;

    /**
     * Boolean: holds whether the game is over or not
     */

    private Boolean gameOver;


}

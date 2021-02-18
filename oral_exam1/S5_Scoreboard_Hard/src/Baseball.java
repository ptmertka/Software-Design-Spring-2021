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
        setAwayTeam(away);
        setPeriodLength("3 outs");
        String [] scoreMethods = {"Single", "Double", "Triple", "Homerun", "Bunt", "Strikeout", "Flyout", "Lineout", "Popout", "Walk"}
    }

    @Override
    public void advanceTimePeriod() {

    }

    @Override
    public void applyScoreChoice(int n) {

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

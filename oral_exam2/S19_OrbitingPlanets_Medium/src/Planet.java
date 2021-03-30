/**
 * Class that is the thread object of the frame
 * Creates planets that have x and y coordiantes of the oval that is the planet
 * While running, runs an infinite loop that increments the coordinates by incrementing the degrees around the planet by 1
 *
 */
public class Planet implements Runnable{

    /**
     * Int: the x coordinate of the planet
     */
    private int x = 0;

    /**
     * Int: the y coordinate of the planet
     */
    private int y =0;

    /**
     * Int: the radius of the planet as a circle
     */
    private int radius = 0;

    /**
     * Int: the degreees around a circle that the planet is in its orbit
     */
    private int degrees = 0;

    /**
     * Constructor used to create the planets
     * @param x the x coordinate to be set to
     * @param y the y coordinate to be set to
     * @param radius the radius of the planet
     */
    public Planet(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Getter for the x coordinate
     * @return Int: The x coordiante of the planet
     */
    public int getX(){
        return x;
    }

    /**
     * Getter for the y coordinate
     * @return Int: the y coordinate of the planet
     */
    public int getY(){
        return y;
    }

    /**
     * Getter for the radius of the planet
     * @return Int: the radius of the planet object
     */
    public int getRadius(){
        return radius;
    }

    /**
     * Setter for the x coordinate
     * @param x Int: The value of x to be changed
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Setter for the y coordinate
     * @param y Int; The value of y to be changed to
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Setter for the radius of the planet
     * @param radius Int: the new radius to be set to
     */
    public void setRadius(int radius){
        this.radius=radius;
    }

    /**
     * Method needed to make this class a runnable
     * Increments the x and y coordinates of the planet around an orbit with an infinite loop
     */
    @Override
    public void run() {
        try { //in try block in case of interrupt during sleep
            while (true) {
                x = (int) (500 + (250 * Math.cos(Math.toRadians(degrees)))); //gets the new coordinates of the planet using sin and cos
                y = (int) (500 + (250 * Math.sin(Math.toRadians(degrees)))); //multiplies by the radius of orbit 250, and adds 500 so that it is centered
                degrees++; //increments degrees
                Thread.sleep(10); //puts the thread to sleep to allow for other threads
            }
        }
        catch (InterruptedException e){ //if thread is interrupted, prints its stack trace
            e.printStackTrace();;
        }
    }
}

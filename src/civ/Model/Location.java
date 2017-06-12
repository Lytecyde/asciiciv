package civ.Model;

import civ.gui.View;

/**
 * Created by miku on 31/05/2017.
 */
public class Location {

    public static int x;
    public static int y;

    public int getX() {
        return x;
    }

    public int getY() { return y; }

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Location movement(DirectionType n){
        //Location locationAfterMove = null;
        switch (n){
            case NORTH:
                x = x;
                y = isLegalYDecrease()?y - 1:y;
                break;
            case EAST:
                x = isLegalXIncrease()?x + 1:x;
                y = y;
                break;
            case SOUTH:
                x = x;
                y = isLegalYIncrease()?y + 1:y;
                break;
            case WEST:
                x =  isLegalXdecrease()?x - 1:x;
                y = y;
                break;
            default:

        }



        //return locationAfterMove;
        return this;
    }

    private boolean isNull(Object object) {
        return (object == null);
    }

    public boolean isLegalMove() {
        return isLegalXdecrease() &&
                isLegalXIncrease() &&
                isLegalYDecrease() &&
                isLegalYIncrease();
    }

    private boolean isLegalXdecrease() {
        return this.x > 0;
    }

    private boolean isLegalXIncrease() {
        return this.x < View.rows - 1;
    }

    private boolean isLegalYDecrease() {
        return this.y > 0;
    }

    private boolean isLegalYIncrease() {
        return this.y < View.cols -1 ;
    }

}

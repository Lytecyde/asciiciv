package civ.Model;

import civ.Control.Civilization;
import civ.gui.View;

import javax.lang.model.type.NullType;

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

    public void movement(DirectionType n){
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
        return this.x < View.cols - 1;
    }

    private boolean isLegalYDecrease() {
        return this.y > 0;
    }

    private boolean isLegalYIncrease() {
        return this.y < View.rows -1 ;
    }

}

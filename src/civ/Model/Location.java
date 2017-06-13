package civ.Model;

import civ.gui.View;

/**
 * Created by miku on 31/05/2017.
 */
public class Location {

    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() { return y; }

    public Location[] history = new Location[2];

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Location(){

    }

    public Location movement(DirectionType n){
        history[0] = this;
        history[1] = new Location();
        switch (n){
            case NORTH:
                history[1].x = history[0].x;
                history[1].y = isLegalYDecrease()?history[0].y - 1:history[0].y;
                return history[1];

            case EAST:
                history[1].x = isLegalXIncrease()?history[0].x + 1:history[0].x;
                history[1].y = history[0].y;
                return history[1];
            case SOUTH:
                history[1].x = history[0].x;
                history[1].y = isLegalYIncrease()?history[0].y + 1:history[0].y;
                return history[1];
            case WEST:
                history[1].x = isLegalXdecrease()?history[0].x - 1:history[0].x;
                history[1].y = history[0].y;
                return history[1];
            default:
                return history[1];
        }

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

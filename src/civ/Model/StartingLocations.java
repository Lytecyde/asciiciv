package civ.Model;

import civ.Control.Player;
import civ.gui.View;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static civ.Model.Data.cols;
import static civ.Model.Data.rows;

/**
 * Created by miku on 09/07/2017.
 */
public class StartingLocations {
    public static LinkedList<Location> promisedLands =
            new LinkedList<Location>();
    public static LinkedList<Location> startingSpots =
            new LinkedList<Location>();
    private WorldMap wm ;
    private LinkedList<Player> list;




    public StartingLocations(WorldMap map, LinkedList<Player> list) {
        wm = map;
        this.list = list;
        startingSpots = makeStartingLocations(list);
    }

    public LinkedList<Location> getLandLocations(){
        LinkedList<Location> promisedLands = new LinkedList<Location>();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                while (isLand(x, y)){
                    promisedLands.add(new Location(x,y));
                    break;
                }
            }
        }
        return promisedLands;
    }

    private boolean isLand(int x, int y) {
        JLabel[][] wmMapCells = wm.getMapCells();
        JLabel mapLabel = wmMapCells[x][y];
        return !(mapLabel.getBackground() == Color.cyan);
    }

    public Location pickStartingLocation(){
        Random randomised = new Random();
        LinkedList<Location> allLands = getRemainingLandLocations();
        Location start = allLands.get(randomised.nextInt(allLands.size()));
        setRemainingLandLocations(start);
        return start;
    }

    private void setRemainingLandLocations(Location start) {
        promisedLands.remove(start);
    }

    private LinkedList<Location> getRemainingLandLocations() {
        return promisedLands;
    }

    public LinkedList<Location> makeStartingLocations(LinkedList<Player> list){
        startingSpots = new LinkedList<Location>();
        promisedLands.addAll(getLandLocations());
        for(Player player: list) {
            Location start = pickStartingLocation();
            player.startingSpot = start;
            startingSpots.add(start);
        }
        return startingSpots;
    }

    public LinkedList<Location> getStartingspots(){
        return startingSpots;
    }

}

package civ.Model;

/**
 * Created by miku on 30/05/2017.
 */
public class City {
    ID identification = new ID();
    String ownerNation;
    Buildings infrastructure;
    MapCoordinates cityCoordinates;
    int x,y;


    public City(String nationName){
        ownerNation = nationName;
        infrastructure = new Buildings();
        setLocation();
        cityCoordinates = new MapCoordinates(x,  y);
    }

    public void setLocation() {
        //TODO going to give them values
        x = 0;
        y = 0;
    }


}

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


    public City(Unit founderUnit){
        ownerNation = founderUnit.commandingNation;
        infrastructure = new Buildings();
        setLocation();
        cityCoordinates = new MapCoordinates(x,  y);
    }

    public City(String nationName) {

    }

    public void setLocation() {
        //founding
    }


    public MapCoordinates getLocation() {
        return new MapCoordinates(x,y);
    }
}

package civ.Model;

/**
 * Created by miku on 30/05/2017.
 */
public class City {
    ID identification = new ID();
    String ownerNation;

    public City(String nationName){
        ownerNation = nationName;
    }
}

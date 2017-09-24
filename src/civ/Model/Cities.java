package civ.Model;

import civ.Control.Civilization;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Cities {
    public LinkedList<City> list = new LinkedList<>();

    public void addNewCity(String nationName){
        list.add(new City(nationName));
    }
}

package civ.Control;

import civ.Model.*;

import java.awt.*;

/**
 * Created by miku on 30/05/2017.
 */

public class Player {

    public ID identification = new ID();
    int population;
    int incomePerTurn;
    public int funds = 100;

    public int unitCount;
    //Lists
    public Units units;
    Advances advances = new Advances();
    public Cities cities;
    public Corporations corporations = new Corporations();
    //Values
    public int happiness;
    public int education;
    public int tax;
    public int pollution = 0;
    boolean rocketBuilt = false;
    public Location startingSpot;
    public Color colors;
    public int unitIndex;


    public Player(ID id){
        identification = id;
        colors = Data.colors[id.id];//
        units = new Units(this);
        unitCount = units.list.size();
        System.out.println("ID" + id.fullName + id.id + "power:" + unitCount);
        cities = new Cities();
    }

}

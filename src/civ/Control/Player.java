package civ.Control;

import civ.Model.*;

import java.awt.*;

/**
 * Created by miku on 30/05/2017.
 */

public class Player {
    public String nationName;
    ID identification = new ID();
    int population;
    int incomePerTurn;
    public int funds = 100;

    public int unitCount;
    //Lists
    public Units units;
    Advances advances = new Advances();
    public Cities cities = new Cities();
    public Corporations corporations = new Corporations();
    //Values
    public int happiness;
    public int education;
    public int tax;
    public int pollution = 0;
    boolean rocketBuilt = false;
    public Location startingSpot;
    public Color colors;

    public Player(){

        nationName = "Romans";
        identification.fullName = "Cesar";
        identification.id = 0;
        population = 2 ;
        incomePerTurn = 2;
        education = 0;
        units = new Units();
        unitCount = units.list.size();
        colors = Color.WHITE;
    }

    public Player(String nationName){

        this.nationName = nationName;
        identification.fullName = "Mik Seljamaa :)";
        identification.id = identification.id++;
        units = new Units(this);
        unitCount = units.list.size();
        colors= Data.colors[identification.id];
    }



}

package civ.Control;

import civ.Model.*;

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

    public Player(){

        nationName = "Romans";
        units = new Units();
        unitCount = units.list.size();
        identification.fullName = "Cesar";

        identification.id = 0;
        population = 2 ;
        incomePerTurn = 2;
        education = 0;
    }

    public Player(String nationName){
        new Player();
        this.nationName = nationName;
        identification.fullName = "Mik";
        identification.id = identification.id++;
    }



}

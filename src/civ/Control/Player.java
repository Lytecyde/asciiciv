package civ.Control;

import civ.Model.*;

import java.util.HashMap;

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
        System.out.println("LOG: player created as default");
        units = new Units();
        unitCount = units.list.size();
        identification.name  = "Cesar";
        nationName = "Romans";
        identification.id = 0;
        population = 2 ;
        incomePerTurn = 2;
        education = 0;
    }
    public Player(String nationName){
        this.nationName = nationName;
        createSetupUnits();
    }
    private void createSetupUnits(){
        Unit firstTribe = new Unit(UnitType.SETTLER);
        units.list.add(firstTribe);
        //TODO for use later:
        //Unit firstWarrior = new Unit(UnitType.WARRIOR);
        //units.list.add(firstWarrior);
    }

}

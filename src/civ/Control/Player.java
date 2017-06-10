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
    public int funds;
    //static int percentage = 100;
    public int unitCount;
    public Units units;
    Advances advances = new Advances();
    int pollution = 0;
    boolean rocketBuilt = false;
    public Cities cities = new Cities();
    public Corporations corporations = new Corporations();
    public int happiness;
    public int education;

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
    }

}

package civ.Control;

import civ.Model.Advances;
import civ.Model.Cities;
import civ.Model.ID;
import civ.Model.Units;

import java.util.HashMap;

/**
 * Created by miku on 30/05/2017.
 */

public class Player {
    public String nationName;
    private ID identification = new ID();
    int population;
    int incomePerTurn;
    int funds;
    static int percentage = 100;
    public int unitCount;
    Units units  =  new Units();
    Advances advances = new Advances();
    int pollution = 0;
    boolean rocketBuilt = false;
    Cities cities = new Cities();

    public Player(){
        System.out.println("LOG: player created as default");
        unitCount = 0;
        identification.name  = "Cesar";
        nationName = "Romans";
        identification.id = 0;
        population = 2 ;
        incomePerTurn = 2;
    }
    public Player(String nationName){
        this.nationName = nationName;
    }

}

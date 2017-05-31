package civ.Model;

import civ.Control.Civilization;
import civ.Control.Player;


import java.util.HashMap;


/**
 * Created by miku on 30/05/2017.
 */
public class Unit {
    private Location location;
    private ID identification;
    private String ownerNation;
    private String commandingNation;
    private String homeCity;
    //powers
    private int power;
    private int strength;
    private int defence;
    private int move;
    //acquired properties
    private boolean veteran;
    private boolean mechanised;
    private boolean naval;
    private boolean airborne;
    private boolean infected;
    private boolean fortified;
    //setSkills
    public static boolean foundCity;
    public static boolean build;
    public static boolean diplomacy;
    public static boolean trade;
    public static boolean medical;
    public static boolean legal;
    public static boolean preach;
    public static boolean business;
    public static boolean create;
    public static boolean teach;
    public static boolean farm;
    public static boolean explore;
    //
    private int food;
    public final int foodUse = 1;
    private int cost;
    private int morale;


    private HashMap<Player, Integer> natives;



    private void setSkills(){
        foundCity = true;
        build = false;
        diplomacy = false;
        trade = false;
        medical = false;
        legal = false;
        preach = false;
        business = false;
        create = false;
        farm = false;
        teach = false;
        explore = false;
    }

    public Unit(UnitType unitType){
        identification.id = Civilization.currentPlayer.unitCount;
        Civilization.currentPlayer.unitCount++;
        identification.type = unitType.name();
        identification.name = ownerNation + identification.type + Integer.toString(identification.id);

        switch(unitType){
            case SETTLER:


                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                foundCity = true;
                food = 1;
                cost = 0;
                morale = 1 + Data.scapegoatingEffect;

            break;

            case BUILDER:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                build= true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case WARRIOR:
                power = 1;
                strength = 2;
                defence = 2;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case FARMER:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                farm = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case MONK:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                preach = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case SCHOLAR:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                teach = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case TRADER:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                trade = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case EXPLORER:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                explore = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case ARTIST:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                create = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case DIPLOMAT:
                power = 1;
                strength = 1;
                defence = 1;
                move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                diplomacy = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            default:

                break;

        }
    }

    public int getId() {
        return identification.id;
    }

    public String getType() {
        return identification.type;
    }
}

package civ.Model;

import civ.Control.Civilization;
import civ.Control.Player;


import java.util.HashMap;




/**
 * Created by miku on 30/05/2017.
 */
public class Unit {
    private Location location;
    public ID identification = new ID();
    private String commandingNation;
    private String homeCity;
    //acquired properties
    private boolean veteran;
    private boolean mechanised;
    private boolean naval;
    private boolean airborne;
    private boolean infected;
    private boolean fortified;
    private boolean vigil;
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
    public final int foodUse = 1;


    private HashMap<Player, Integer> natives;
    private boolean working;

    private void initialiseProperties(){
        veteran = false;
        mechanised = false;
        naval = false;
        airborne = false;
        infected = false;
        fortified = false;
        vigil = false;
        working = false;
    }

    private void setSkills(){
        foundCity = false;
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

        identification.id = Civilization.totalUnitCount;
        Civilization.totalUnitCount++;

        Player current = Civilization.getCurrentPlayer();
        String ownerNation = current.nationName;
        identification.type = unitType.name();
        identification.name = ownerNation + identification.type + Integer.toString(identification.id);

        switch(unitType){
            case SETTLER:
                int power = 1;
                int strength = 1;
                int defence = 1;
                int move = 1;

                veteran = false;
                mechanised = false;
                infected = false;
                naval = false;
                airborne = false;

                setSkills();
                foundCity = true;
                int food = 1;
                int cost = 0;
                int morale = 1 + Data.scapegoatingEffect;

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

    public boolean isInfected(){ return infected; }

    public void contractsInfection() { infected = true; }

    public void infectionHealed() { infected = false; }

    public void fortify() { fortified = true; }

    public void unfortify() { fortified = false; }

    public boolean isFortified() { return fortified; }

    public void embark() { naval = true; }

    public void disembark() { naval = false; }

    public boolean isNaval() {return naval;}

    public void setAirborne() { airborne = true;}

    public void finishFlight() { airborne = false; }

    public boolean isAirborne() { return airborne; }

    public void setVigil() { vigil = true; }

    public void activate() { vigil = false; }

    public boolean isOnVigil() { return vigil; }

    public void onTransport() { mechanised = true; }

    public void onFoot() { mechanised = false; }

    public boolean isMechanised() { return mechanised; }

    public void working() { working = true; }

    public void ready() { working = false; }

    public boolean isWorking() { return working; }

    public boolean isVeteran() {return veteran;}
}

package civ.Model;

import civ.Control.Civilization;
import civ.Control.Player;

import java.awt.*;


/**
 * Created by miku on 30/05/2017.
 */
public class Unit {
    //public String ownerNation;
    public Location location = new Location();
    public ID identification = new ID();
    public String commandingNation;
    public String homeCity;
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

    public static char chit = 254;

    private boolean working;
    public Color colors;

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




    public Unit(UnitType unitType, Player currentPlayer){

        identification.unitCode = Civilization.totalUnitCount;
        Civilization.totalUnitCount++;
        identification.id = currentPlayer.unitIndex++;
        setNameAndLocation(currentPlayer);
        this.colors = currentPlayer.colors;
        identification.type = unitType.name();
        identification.fullName = currentPlayer.identification.fullName;
        System.out.println("unit made" +
        identification.type +
        identification.fullName);

        initialiseProperties();
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
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
                chit = Data.landChit;
                setSkills();
                diplomacy = true;

                food = 1;
                cost = 1;
                morale = 1;
                break;
            case NULL:
                identification.type = "";
                identification.fullName = "";
                break;
            default:

                break;

        }
    }

    private void setNameAndLocation(Player currentPlayer) {
        identification.fullName = currentPlayer.identification.fullName;
        location = currentPlayer.startingSpot;
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

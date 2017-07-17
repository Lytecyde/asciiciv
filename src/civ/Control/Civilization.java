package civ.Control;

import civ.Model.*;
import civ.gui.Setup;
import civ.gui.View;

import java.util.LinkedList;

import static civ.Control.Policies.*;

/**
 * Created by miku on 30/05/2017.
 */
public class Civilization {

    private static Data gameData =  new Data();
    public static String type;
    public static Player currentPlayer;
    public static int gameMapSizeX;
    public static int gameMapSizeY;
    public static int worldGreenLevel;      //pollution
    public static int worldPeaceScore;
    private static int turnCount;
    public static int totalUnitCount;
    public static LinkedList<Player> listOfPlayers = new LinkedList<Player>();
    public static int numberOfPlayers;
    public static int year = 0;
    public static StartingLocations startingLocations;
    private static RoundTable r;
    private static WorldMap map;
    public static void main(String[] args) {
        //TODO MASHED : FIX THE ORDER things go
        new Data();
        new Setup();
        makeMap();
        //make players
        numberOfPlayers =  Data.numberOfPlayers;
        r = new RoundTable(numberOfPlayers);
        listOfPlayers = r.listOfPlayers;
        startingLocations = new StartingLocations(map, listOfPlayers);
        clearListOfPlayers();
        listOfPlayers = assignStartingSpots();
        saveToData(listOfPlayers);
        testPlayerColors();
        allPlayersAllUnitsDataSet();
        firstPlayerSetup();
        View v = new View(map);
        v.updateUnitBoardWithCurrentPlayerUnit();
        roundLoop();
    }

    public Civilization(){
        turnCount = 0;
        totalUnitCount = 0;
    }

    public static void allPlayersAllUnitsDataSet(){
        for(Player p: listOfPlayers){
            p.units.setID(p);
        }
    }

    private static void clearListOfPlayers() {
        listOfPlayers = new LinkedList<Player>();
        Data.listOfPlayers = new LinkedList<Player>();
    }

    private static void makeMap() {
        map = new WorldMap(MapType.VISIBLE);
    }

    private static void saveToData(LinkedList<Player> listOfPlayers) {
        Data.listOfPlayers.addAll(listOfPlayers);
    }

    private static LinkedList<Player> assignStartingSpots() {
        int index = 0;
        LinkedList<Player> lp = new LinkedList<Player>();
        LinkedList<Location> listOfStartingspots = startingLocations
                .getStartingspots();
        for(Player p :r.listOfPlayers){
            p.startingSpot = listOfStartingspots.get
                    (index++);
            lp.add(p);
        }
        return lp;
    }

    private static void testPlayerColors(){
        for(Player p: Data.listOfPlayers){
            System.out.println(p.identification.fullName +
                    p.colors.toString() +
                    p.startingSpot.x +
                    "x  " +
                    p.startingSpot.y +
                    "y  "
            );
        }
    }

    private static void testPlayerCount() {
        numberOfPlayers =  Data.listOfPlayers.size();
        System.out.println("list of players in Data member count " +
                numberOfPlayers);
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }



    private static void firstPlayerSetup() {
        //listOfPlayers = new LinkedList<>();
        //listOfPlayers.addAll(Data.listOfPlayers);
        currentPlayer = listOfPlayers.getFirst();
    }

    private static void roundLoop() {
        for(Player player: Data.listOfPlayers) {
            gameTurn(player);
            checkAllEndings();
        }
    }

    private static void checkAllEndings() {
         new Endings();
    }

    private static void gameTurn(Player player) {
        //events.Calamities, events.Jubilees
        ministries(player);
        unitLeadership(player);
        cityManagement(player);
    }

    private static void ministries(Player currentplayer) {
        //money/gold
        financial(currentPlayer);
        //culture
        cultural(currentPlayer);
        //science
        science(currentPlayer);
        //happiness
        interior(currentPlayer);
        //population,migration
        social(currentPlayer);
    }

    private static void endTurn() {
        Civilization.turnCount += 1;
    }

    private static void cityManagement(Player currentPlayer) {

    }

    private static void unitLeadership(Player currentPlayer) {

    }

    static void adjustFunds(int incomePerTurn) {
        Civilization.currentPlayer.funds += incomePerTurn;
    }

    public static Player getNext(Player current){
        int nextIndex =1;
        while(!r.listOfPlayers.isEmpty()) {
            int currentIndex = r.listOfPlayers.indexOf(current);
            nextIndex = currentIndex + 1;
            nextIndex = nextIndex % listOfPlayers.size();
            break;
        }
        return listOfPlayers.get(nextIndex);
    }
}

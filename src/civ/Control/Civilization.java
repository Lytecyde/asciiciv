package civ.Control;

import civ.Model.Data;
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
    public static Player currentPlayer = new Player();
    public static int gameMapSizeX;
    public static int gameMapSizeY;
    public static int worldGreenLevel;      //pollution
    public static int worldPeaceScore;
    private static int turnCount;
    public static int totalUnitCount;
    public static LinkedList<Player> listOfPlayers;
    public static int numberOfPlayers;
    public static int year = 0;
    private static RoundTable r;
    public static void main(String[] args) {
        //System.out.println("msg: civ main");
        new Data();
        new Setup();
        numberOfPlayers =  Data.numberOfPlayers;
        r = new RoundTable(numberOfPlayers);
        startingPlayerSetup();
        testPlayerCount();
        Data.listOfPlayers.addAll(r.listOfPlayers);
        testPlayerColors();
        View v = new View();
        v.updateUnitBoard();
        roundLoop();
    }

    private static void testPlayerColors(){
        for(Player p: Data.listOfPlayers){
            System.out.println(p.nationName + p.colors.toString());
        }
    }

    private static void testPlayerCount() {
        numberOfPlayers =  Data.listOfPlayers.size();
        System.out.println("listof players in Data member count " +
                numberOfPlayers);
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Civilization(){
        turnCount = 0;
        totalUnitCount = 0;
    }

    private static void startingPlayerSetup() {
        listOfPlayers = new LinkedList<>();
        listOfPlayers.addAll(Data.listOfPlayers);
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

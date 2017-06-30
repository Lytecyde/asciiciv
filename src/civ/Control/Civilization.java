package civ.Control;

import civ.Model.Data;
import civ.gui.Setup;
import civ.gui.View;

import static civ.Control.Policies.*;

/**
 * Created by miku on 30/05/2017.
 */
public class Civilization {
    static RoundTable players;
    private static Data gameData =  new Data();
    public static String type;
    public static Player currentPlayer;
    public static int gameMapSizeX;
    public static int gameMapSizeY;
    public static int worldGreenLevel;
    public static int worldPeaceScore;
    private static int turnCount;
    public static int totalUnitCount;

    public static int year = 0;
    public static void main(String[] args) {
        //System.out.println("msg: civ main");
        new Data();
        new Civilization();

        gameSetup();
        boardGUI();
        roundLoop();
    }

    private static void boardGUI() {
        new View();
    }


    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Civilization(){
        turnCount =0;
        totalUnitCount =0;
    }
    private static void gameSetup() {
        System.out.println("msg: setup");
        startingPlayerSetup();
        new Setup();

    }

    private static void startingPlayerSetup() {
        try {
            players = new RoundTable(3);
            assert players.listOfPlayers.isEmpty() == false;
            currentPlayer = players.listOfPlayers.getFirst();
        }catch(NullPointerException e){
            System.out.println("Here we are! too many players created or null");
        }


    }

    private static void roundLoop() {
        for(Player player: RoundTable.listOfPlayers) {
            gameTurn(player);
            checkAllEndings();
        }
    }

    private static void endCondition() {

    }

    private static void checkAllEndings() {
         new Endings();

    }

    private static void gameTurn(Player player) {
        //events
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
}

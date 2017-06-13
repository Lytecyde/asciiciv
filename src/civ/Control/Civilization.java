package civ.Control;

import civ.Model.Data;
import civ.Model.EndOfGame;
import civ.gui.Setup;
import civ.gui.View;

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
    public static void main(String[] args) {
        System.out.println("msg: civ main");
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
        players = new RoundTable(3);
        currentPlayer = players.listOfPlayers.getFirst();
    }

    private static void roundLoop() {

        for(Player player: RoundTable.listOfPlayers) gameTurn(player);

    }

    private static void endCondition() {

    }

    private static EndOfGame findCondition() {
        //TODO
        return new EndOfGame();
    }

    private static void gameTurn(Player player) {
        ministries(player);
        unitLeadership(player);
        cityManagement(player);
        endCondition();
        endTurn();
    }

    private static void ministries(Player player) {
        currentPlayer = player;
        //events

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

    private static void social(Player currentPlayer) {

    }

    private static void interior(Player currentPlayer) {

    }

    private static void science(Player currentPlayer) {

    }

    private static void cultural(Player currentPlayer) {

    }

    private static void financial(Player player) {
        adjustFunds(player.incomePerTurn);
    }

    private static void adjustFunds(int incomePerTurn) {
        Civilization.currentPlayer.funds += incomePerTurn;
    }
}

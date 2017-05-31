package civ.Control;

import civ.Model.Data;
import civ.Model.EndOfGame;
import civ.gui.Setup;

/**
 * Created by miku on 30/05/2017.
 */
public class Civilization {
    static RoundTable players = new RoundTable(3);
    private static Data gameData =  new Data();
    public static String type;
    public static Player currentPlayer;
    public static int gameMapSizeX;
    public static int gameMapSizeY;
    public static int worldGreenLevel;
    public static int worldPeaceScore;
    public static void main(String[] args) {
        gameSetup();
        turnLoop();
    }

    private static void gameSetup() {
        new Setup();
        currentPlayer = players.listOfPLayers.getFirst();
    }

    private static void turnLoop() {
        for(Player player: RoundTable.listOfPLayers)gameRound(player);
    }

    private static void endCondition() {

    }

    private static EndOfGame findCondition() {
        //TODO
        return new EndOfGame();
    }

    private static void gameRound(Player player) {
        //money
        //culture
        //science
        //happiness
        //migration
        //unit movement
        //citygrowth
        //citybuildings
        //orders
        currentPlayer = player;
        endCondition();
    }
}

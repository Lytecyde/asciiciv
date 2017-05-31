package civ.Control;

import civ.Model.Data;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class RoundTable {
    public static LinkedList<Player> listOfPLayers = new LinkedList<>();
    private int nationCode = 0;
    public RoundTable(int numberOfPlayers){

        generateListOfPlayers(numberOfPlayers);
    }

    private void generateListOfPlayers(int numberOfPlayers) {
        for(int i = 0 ; i < numberOfPlayers;++i) {
            nationCode =(int)(Math.random() * Data.nations.size());
            Player p = new Player(nationCode);
            int n = Data.nations.indexOf(Data.Nation.names[nationCode]);
            Data.nations.remove(n);
            listOfPLayers.add(p);
        }
    }

}

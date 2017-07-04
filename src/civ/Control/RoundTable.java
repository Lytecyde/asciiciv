package civ.Control;

import civ.Model.Data;
import civ.Model.Location;

import java.util.*;

/**
 * Created by miku on 30/05/2017.
 */
public class RoundTable {
    public LinkedList<Player> listOfPlayers = new LinkedList<Player>();
    private int currentNation = 0;
    public LinkedList<String> listOfNations = new LinkedList<String>();
    private int countPlayersMade = 0;

    public RoundTable(int numberOfPlayers){
        generateListOfAllNations();
        generateListOfPlayers(numberOfPlayers);

    }

    private void generateListOfAllNations() {
        listOfNations = new LinkedList<>();
        List list = new ArrayList<String>(Arrays.asList(Data.Nation.names));
        listOfNations.addAll(list);
    }

    private void generateListOfPlayers(int numberOfPlayers) {
        System.out.println("Generating list...");
        for (countPlayersMade = 0;
             countPlayersMade < numberOfPlayers;
             countPlayersMade++) {
            currentNation = !listOfNations.isEmpty()? pickRandomNationFrom
                    (listOfNations):countPlayersMade;
            Civilization.currentPlayer = new Player(
                    Data.Nation.names[currentNation]);
            takeFromNationsListAddToPlayers(currentNation);
        }
        Data.Turn.currentPlayer = listOfPlayers.getFirst();
        System.out.println("players made: " + countPlayersMade);
    }

    private int pickRandomNationFrom(LinkedList<String> listOfNations) {

        do {
            currentNation = (int)(
                    (listOfNations.size() -1) * Math.random());
            System.out.println("" +currentNation);
        }while(!isInBoundsNationCode());
        System.out.println("Nation created is :" + currentNation);
        return currentNation;
    }

    private void takeFromNationsListAddToPlayers(int currentNation) {

            addPlayerToListByCode(currentNation);
            removeNation(currentNation);

    }

    private void addPlayerToListByCode(int currentNation) {
        String nationName = listOfNations.get(currentNation);
        System.out.print("nation is named " + nationName);
        createAndStorePlayer(nationName);
    }

    private void createAndStorePlayer(String nationName) {
        Player p = new Player(nationName);
        listOfPlayers.add(p);
        Data.listOfPlayers.add(p);
    }

    private void removeNation(int currentNation) {
        String currentName = Data.Nation.names[currentNation];
        int n = listOfNations.indexOf(currentName);
        try {
            listOfNations.remove(n);
        }
        catch(NullPointerException e){
            System.out.println(e + " TRYING TO REMOVE FROM AN EMPTY list");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(e + " Trying to remove froma a false location" );
        }

    }

    private boolean isInBoundsNationCode() {
        return currentNation >= 0 &&  currentNation < listOfNations.size();
    }



}

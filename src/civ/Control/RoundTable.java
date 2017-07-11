package civ.Control;

import civ.Model.Data;
import civ.Model.ID;

import java.util.*;

/**
 * Created by miku on 30/05/2017.
 */
public class RoundTable {
    public LinkedList<Player> listOfPlayers = new LinkedList<Player>();
    private int currentNationIndex = 0;
    public LinkedList<String> listOfNations = new LinkedList<String>();
    private int countPlayersMade = 0;

    public RoundTable(int numberOfPlayers){
        generateListOfAllNations();
        generateListOfPlayers(numberOfPlayers);
        saveToData(listOfPlayers);
    }

    private void saveToData(LinkedList<Player> listOfPlayers) {
        Data.listOfPlayers.addAll(listOfPlayers);
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
            currentNationIndex = !listOfNations.isEmpty()?
                    pickRandomNationFrom(listOfNations):
                    countPlayersMade;
            takeFromNationsListAddToPlayers(currentNationIndex);
        }
        Data.Turn.currentPlayer = listOfPlayers.getFirst();
    }

    private int pickRandomNationFrom(LinkedList<String> listOfNations) {

        do {
            Random random =  new Random();
            currentNationIndex = random.nextInt(listOfNations.size());
            System.out.println("" + currentNationIndex);
        }while(isUnique(currentNationIndex));
        System.out.println("Nation created is :" + currentNationIndex);
        return currentNationIndex;
    }

    private boolean isUnique(int currentNation) {
        boolean c = false;
        for(Player p : listOfPlayers){
            boolean equality = p.identification.id == currentNation;
            c = c || equality;
        }
        return c;
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
        ID idforplayer = new ID();
        idforplayer.setID(nationName, currentNationIndex,"peaceful");
        Player p = new Player(idforplayer);
        listOfPlayers.add(p);

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
}

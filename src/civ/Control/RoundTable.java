package civ.Control;

import civ.Model.Data;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class RoundTable {
    public static LinkedList<Player> listOfPlayers = new LinkedList<>();
    private int currentNation = 0;
    public static int numberOfPlayers;
    public LinkedList<String> listOfNations = new LinkedList<>();
    public RoundTable(int numberOfPlayers){

        generateListOfPlayers(numberOfPlayers);

    }

    private void generateListOfPlayers(int numberOfPlayers) {
        new Data();
        generateListOfAllNations();
        currentNation = pickRandomNation(listOfNations);
        movePlayerNames(currentNation);
    }

    private void movePlayerNames(int currentNation) {

            addPlayerToListFrom(currentNation);
            listOfNations = removeNationFrom(listOfNations);

    }

    private void generateListOfAllNations() {
        listOfNations = new LinkedList<>();
        for (int i = 0; i < Data.Nation.names.length ; i++) {
            listOfNations.add(Data.Nation.names[++i]);
        }
    }

    private void addPlayerToListFrom(int currentNation) {
        Player p = new Player(listOfNations.get(currentNation));
        listOfPlayers.add(p);
    }

    private int pickRandomNation(LinkedList<String> listOfNations) {
        do {
            currentNation = (int) (Math.random() * listOfNations.size());
            System.out.println();
        }while(!isInBoundsNationCode());
        return currentNation;
    }

    private boolean isInBoundsNationCode() {
        return currentNation >= 0 &&  currentNation < listOfNations.size();
    }

    private LinkedList removeNationFrom(LinkedList<String> listOfNations) {
        String currentName = Data.Nation.names[currentNation];
        int n = listOfNations.indexOf(currentName);
        try {
            listOfNations.remove(n);
        }
        catch(NullPointerException e){
            System.out.println(e + " TRYING TO REMOVE FROM EMPTY list");
            }
        catch(IndexOutOfBoundsException e){
            System.out.println(e + " Trying to remove froma a false location" );
        }
        return listOfNations;
    }

}

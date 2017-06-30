package civ.Control;

import civ.Model.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class RoundTable {
    public static LinkedList<Player> listOfPlayers = new LinkedList<>();
    private int currentNation = 0;
    //public static int numberOfPlayers;
    public LinkedList<String> listOfNations;
    private int countPlayersMade = 0;

    public RoundTable(int numberOfPlayers){
        //this.numberOfPlayers = numberOfPlayers;
        generateListOfAllNations();
        generateListOfPlayers(numberOfPlayers);

    }

    private void generateListOfPlayers(int numberOfPlayers) {
        System.out.println("Generating list...");
        for (countPlayersMade = 0;
             countPlayersMade < numberOfPlayers;
             countPlayersMade++) {
            currentNation = pickRandomNationFrom(listOfNations);
            takeFromNationsListAddToPlayers(currentNation);
        }

        System.out.println("playersmade: " + countPlayersMade);
    }

    private void takeFromNationsListAddToPlayers(int currentNation) {

            addPlayerToListByCode(currentNation);
            removeNation(currentNation);

    }

    private void generateListOfAllNations() {
        listOfNations = new LinkedList<>();
        Collection list = new Collection() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] a) {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Collections.addAll(list, Data.Nation.names);
        listOfNations.addAll(list);

    }

    private void addPlayerToListByCode(int currentNation) {
        Player p = new Player(listOfNations.get(currentNation));
        listOfPlayers.add(p);
    }

    private int pickRandomNationFrom(LinkedList<String> listOfNations) {
        do {
            currentNation = (int)(
                    (listOfNations.size() -1) * Math.random());
            System.out.println(currentNation);
        }while(!isInBoundsNationCode());
        System.out.println("Nation created is :" + currentNation);
        return currentNation;
    }

    private boolean isInBoundsNationCode() {
        return currentNation >= 0 &&  currentNation < listOfNations.size();
    }

    private void removeNation(int currentNation) {
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

    }

}

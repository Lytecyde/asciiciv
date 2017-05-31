package civ.Model;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Units {
    public static LinkedList<Unit> list = new LinkedList<>();
    Unit activeUnit;
    public Units(){
        populateStartList();
        activeUnit = list.getFirst();
    }

    private void populateStartList() {
        Unit unit = new Unit(UnitType.SETTLER);
        list.add(unit);
        unit = new Unit(UnitType.WARRIOR);
        list.add(unit);
    }

}

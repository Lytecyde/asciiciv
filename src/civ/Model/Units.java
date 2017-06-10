package civ.Model;

import civ.Control.Civilization;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Units {
    public static LinkedList<Unit> list = new LinkedList<>();
    Unit activeUnit = new Unit(UnitType.SETTLER);
    public Units(){
        populateStartList();
        activeUnit = list.getFirst();
    }

    public static int sumOfUnit(UnitType unitType) {

        int count = 0;
        for(Unit u:list) {
            String type = u.identification.type;
            count += type.equals(unitType.name())? 1 : 0;
        }
        return count;
    }

    private void populateStartList() {
        Unit unit = new Unit(UnitType.SETTLER);
        list.add(unit);
        unit = new Unit(UnitType.WARRIOR);
        list.add(unit);

    }

}

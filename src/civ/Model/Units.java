package civ.Model;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Units {
    public  LinkedList<Unit> list = new LinkedList<>();
    Unit activeUnit;

    public Units(){
        populateUnitsListAtStart();
        activeUnit = list.getFirst();
    }

    public int countUnitsOfType(UnitType unitType) {

        int count = 0;
        for(Unit u:list) {
            String type = u.identification.type;
            count += type.equals(unitType.name())? 1 : 0;
        }
        return count;
    }

    private void populateUnitsListAtStart() {
        Unit unit = new Unit(UnitType.SETTLER);
        list.add(unit);
        unit = new Unit(UnitType.WARRIOR);
        list.add(unit);

    }

}

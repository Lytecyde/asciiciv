package civ.Model;

import civ.Control.Player;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Units {
    public  LinkedList<Unit> list = new LinkedList<>();


    public Units(Player player){
        populateUnitsListAtStart(player);

    }
    public Units(){
        populateUnitsListAtStart();

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

    private void populateUnitsListAtStart(Player player) {
        Unit unit = new Unit(UnitType.SETTLER, player);
        list.add(unit);
        unit = new Unit(UnitType.WARRIOR, player);
        list.add(unit);

    }

    public LinkedList<Unit> getList(){
        return list;
    }

}

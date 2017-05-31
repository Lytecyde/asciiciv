package civ.Model;

/**
 * Created by miku on 30/05/2017.
 */
public enum UnitType {
    SETTLER (0),
    BUILDER (1),
    WARRIOR(2),
    FARMER(3),
    MONK(4),
    SCHOLAR(5),
    TRADER(6),
    EXPLORER(7),
    ARTIST(8),
    DIPLOMAT(9),
    DOCTOR(10),
    JUDGE(11);

    private final int typeCode;

    UnitType(int typeCode){
        this.typeCode = typeCode;
    }

    public int getTypeCode(){
        return typeCode;

    }
}

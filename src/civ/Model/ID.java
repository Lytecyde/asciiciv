package civ.Model;

/**
 * Created by miku on 30/05/2017.
 */
public class ID {
    public String fullName = "";
    public int id = 0;
    public String type = "";
    public int unitCode;

    public void setID(String fullname, int id, String type) {
        this.id = id;
        this.fullName = fullname;
        this.type = type;
    }
}

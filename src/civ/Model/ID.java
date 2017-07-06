package civ.Model;

/**
 * Created by miku on 30/05/2017.
 */
public class ID {
    public String fullName = "";
    public static int id = 0;
    public String type = "";
    public static int getNextID(){
        return id++;
    }
}

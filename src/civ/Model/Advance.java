package civ.Model;


import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by miku on 31/05/2017.
 */
public class Advance {
    ID identification = new ID();
    public String story = new String();
    String iconFilename = "";
    public ImageIcon advanceIcon = new ImageIcon(iconFilename);
    int cost = 0;
    LinkedList<String> prerequirements = new LinkedList<>();
    LinkedList<String> makesAvailable = new LinkedList<>();
    LinkedList<String> buildables = new LinkedList<>();
    LinkedList<String> units = new LinkedList<>();


    public ID getIdentification() {
        return identification;
    }


}

package civ.Model;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Advances {
    public LinkedList<Advance> list = new LinkedList<>();
    public Advances(){
        firstAdvances();
    }

    private void firstAdvances() {
        list.add(Advance.Agriculture);
    }
}

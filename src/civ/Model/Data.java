package civ.Model;

import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Data {

    public static LinkedList<String> nations = new LinkedList<>();

    public Data() {
        generateNationsList();
    }

    private void generateNationsList() {
        for(String s: Nation.names) nations.add(s);
    }

    public static final int scapegoatingEffect = 1;


    public class Map {
        int normalXmax = 90;
        int normalYmax = 52;
    }
    public static class Nation{

        public static String[] names = {
                "Egypt",
                "Mesopotamia",
                "Greece",
                "Rome",
                "Germany",
                "Russia",
                "Great Britain",
                "USA"

        };
        public static String[] leadersNames = {
                "Ramses",
                "Hammurabi",
                "Alexander",
                "Cesar",
                "",
                "",
                "",
                ""
        };
    }
}

package civ.Model;

import civ.Control.Player;
import civ.Control.RoundTable;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Data {

    public final static Location CENTRE = new Location(11, 7);
    public static final int rows = 16;
    public static final int cols = 24;
    public static LinkedList<String> nations = new LinkedList<>();
    public static int numberOfArrowKeyPresses = 0;
    public static LinkedList<Player> listOfPlayers = new LinkedList<Player>();
    public static int numberOfPlayers = 0;
    public static Color[] colors = {
            Color.black,
            Color.orange,
            Color.pink,
            Color.magenta,
            Color.gray,
            Color.red,
            new Color(0,0,128),
            new Color(128,0,253)
    };

    public Data() {
        generateNationsList();
    }

    public final static char landChit = '■';

    public final static char seaChit = '±';

    public final static char airChit = 16;

    private void generateNationsList() {
         Collections.addAll(nations, Nation.names);
    }

    public static final int scapegoatingEffect = 1;

    

    public static class Map {

        final static int normalXmax = 90;
        final static int  normalYmax = 52;
    }

    public static class Turn {
        public static Player currentPlayer;
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
                "Narmer",
                "Sargon",
                "Alexander",
                "Cesar",
                "Bismarck",
                "Rörich",
                "Arthur",
                "Washington"
        };
    }
    public static class Advancements {
        public static final String[] names = {
                "Mining",
            "Engineering",
            "Waterworks",
            "Roads",
            "Bridges",
            "Tunnels",

                "Irrigation",
            "Fertilisers",
            "Fishfarming",
            "Almanach",
            "Husbandry",
            "Hydroponics",

                "Riverboat",
            "Fishing",
            "Cartography",
            "Sailing",
            "Navigation",
            "Seadrones",

                "Writing",
            "Philosophy",
            "Academy",
            "University",
            "MOOC",
            "Augmented Intelligence",

                "Mechanical calculators",
            "Vacuum tubes",
            "Silicon",
            "Mobiles",
            "Nano",
            "Quantum",

                "Software",
            "DataBases",
            "Networks",
            "Social Media",
            "Deep Learning",
            "Artificial Intelligence",

            "Myths",
            "Rituals",
            "Ceremonies",
            "Priesthood",
            "Theocracy",
            "Transpersonality",

                "Bartering",
            "Trade",
            "Currency",
            "Banking",
            "Taxation",
            "Blockchain",

                "Paintings",
            "Sculptures",
            "Architecture",
            "Theatre",
            "Film",
            "Games",

                "Mathematics",
            "Physics",
            "Biology",
            "Chemistry",
            "Economics",
            "Informatics",

            "Fermentation",
            "Canning",
            "Sanitation",
            "Medicine",
            "Surgery",
            "Antibiotics",
            "Reversed Aging",

                "Pottery",
            "Metal working",
            "Plastics",
            "Fuels",
            "Nanotech",
            "4D materials",

                "Tactics",
            "Strategy",
            "Siegecraft",
            "Artillery",
            "Blitz",
            "Electronic",

                "Riding",
                "Seaships",
                "Trains",
                "Automobiles",
                "Airplanes",
                "Rockets",
            "Peer Review",
            "Falsification",
            "Paradigms",
            "Experimentation",
            "Observation",
            "Theorisation"
        };
        int costs[] = {
                0,
                60,
                80,
                100,
                100,
                100,
                0,
                60,
                80,
                100,
                100,
                100,

                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,


        };

        public String[][] prerequirements = {
                {"",""},
                {"",""}};
    }
}

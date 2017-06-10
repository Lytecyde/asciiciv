package civ.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by miku on 30/05/2017.
 */
public class Data {

    public static LinkedList<String> nations = new LinkedList<>();

    public static int numberOfArrowKeyPresses = 0;
    public Data() {
        generateNationsList();
    }

    private void generateNationsList() {
         Collections.addAll(nations, Nation.names);
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

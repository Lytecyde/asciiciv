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

    public final static Location CENTRE = new Location(8, 12 );
    public static final int rows = 16;
    public static final int cols = 24;
    public static LinkedList<String> nations = new LinkedList<>();
    public static int numberOfArrowKeyPresses = 0;
    public static LinkedList<Player> listOfPlayers = new LinkedList<Player>();
    public static int numberOfPlayers = 0;
    public static Unit NULLUNIT;

    public static Color[] colors = {
            Color.black,
            Color.orange,
            new Color(201,133,63),
            new Color(200,0,253),
            Color.gray,
            Color.red,
            new Color(0,0,200),
            new Color(128,0,253)
    };
    public static String competency;
    public static String firstPlayerName;

    public Data() {
        generateNationsList();
    }

    public final static char relationshipLove = '♥';

    public final static char relationshipHate = '♠';

    public final static char relationshipPeace = '☮'; //9774

    public final static char relationshipFist = 'F';//✊ forment

    public final static char relationshipRage = '☠';

    public final static char flag = '⚑';//🌸

    public final static char culture = '♫';
    public final static char food = '⌠';//🍞
    public final static char science = 'ƒ';//💡
    public final static char book = 'm';//📖
    public final static char law = '§';
    public final static char information = 'B';
    public final static char gold = '$';
    public final static char building = '■';
    public final static char drugs = '+';//💊
    public final static char skills = '*';
    public final static char motors = '⚙';//🚗 🚂 ♞
    public final static char weapons = '↑';//🔫
    public final static char victory = 'V';//✌

    public final static char population = '☺';//🚹

    public final static char happyFace = '☺';

    public final static char unhappyFace = '☹';

    public final static char capitalCity = '★';

    public final static char nukeWinter = '❄';

    public final static char globalBurn = '☼';

    //lands

    public final static char cityscape = '#';//🏙

    public final static char roads = '×';

    public final static char canals = '╬';

    public final static char rails = '╪';

    //units

    public final static char landChit = '▓';

    public final static char seaChit = '►';//🚢

    public final static char airChit = '^';//✈ or ∞

    public final static char rocketChit = '▲';//🚀

    public final static char satellite = 'Φ'; //🛰

    //buildings

    public final static char propagandaTower = 'A';//🗼
    String teletower = "🗼";//makes people happy or angry others happy or angry
    String bank = "🏦";//adds gold and risk of bust



    String school = "🏫";//adds skills
    String artCommune = "🖌";//creates culture
    String temple = "⛪";//makes preachers
    String court = "§";//makes things more lawful
    String factory = "🏭";//makes goods
    String house = "🏠";//houses people contributes to happiness
    String hospital = "🏥";//heals people and increases life expectancy
    String garage = "⛽";//fuels and repairs? motorised units
    String lab = "🔬";//adds science
    String server = "💻";// adds information bytes
    String farm = "🏡";//adds food
    String market = "🏬";//makes merchants

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
        public static int currentPlayerIndex;
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

package civ.Control;

import civ.Model.*;

import java.util.LinkedList;

/**
 * Created by miku on 31/05/2017.
 * my first polymorphic 'switch'
 */
public class Endings {

    public class Conditional {
        public boolean isReversedAging(){
            return Civilization.currentPlayer.advances.acquired.contains(
                    (Object) "Reversed Aging");
        }
        public boolean isEnvironmentalist(){
            return Civilization.worldGreenLevel > 0 ;
        }
        public boolean isSpaceRace() { return Civilization.currentPlayer.rocketBuilt; }
        public boolean isTradeSupremacy() {
            LinkedList<Player> otherPlayers = groupOtherPlayersList();
            boolean[] isForeignCorporation = findForeignCorporations(otherPlayers);
            return isMajorityOfCorporations() &&
                    !isOneAmong(isForeignCorporation) ;
        }
        private boolean isMajorityOfCorporations() {
            int halfOfPotentialCorporations = ResourceTypes.values().length/2;
            return Civilization.currentPlayer.corporations.list.size() >= halfOfPotentialCorporations;
        }

        private LinkedList<Player> groupOtherPlayersList() {
            LinkedList<Player> otherPlayers = Civilization.players.listOfPlayers;
            otherPlayers.remove(Civilization.currentPlayer);
            return otherPlayers;
        }

        public boolean isProsperity(){
            int loadsOfMoneyFactor = 100;
            return Civilization.currentPlayer.funds >
                (Civilization.currentPlayer.population * loadsOfMoneyFactor);
        }

        public boolean isArtilectBuilt(){
            return Civilization.currentPlayer.advances.acquired.contains(
                    (Object) "Artificial Intelligence");
        }
        public boolean isHappy(){
            return Civilization.currentPlayer.happiness == Civilization.currentPlayer.population &&
                Civilization.currentPlayer.population  > sumOfOtherPopulations();
        }

        private int sumOfOtherPopulations() {
            LinkedList<Player> otherPlayers = groupOtherPlayersList();
            int sum = 0;
            for(Player p:otherPlayers) sum = sum + p.population;
            return sum;
        }

        public boolean isEducation(){
            return Civilization.currentPlayer.education > sumOfOtherEducated();
        }

        private int sumOfOtherEducated() {
            LinkedList<Player> otherPlayers = groupOtherPlayersList();
            int sum = 0;
            for(Player p:otherPlayers) sum = sum + p.education;
            return sum;
        }


        private boolean[] findForeignCorporations(LinkedList<Player> otherPlayers) {
            int otherCorporationsCount = 0;
            boolean isForeignCorporation[] = new boolean[otherPlayers.size()];

            for(Player p : otherPlayers) isForeignCorporation[++otherCorporationsCount] = isOtherCorporationsExist(p);
            return isForeignCorporation;
        }

        private boolean isOneAmong(boolean[] array)
        {
            boolean isOneInArray = false;
            for(boolean b : array) isOneInArray |= b;
            return isOneInArray;

        }


        private boolean isOtherCorporationsExist(Player p) {
            return p.corporations.list.size() >= 0;
        }

        public Conditional(){
            boolean[] listOfVictoryConditions = {
                    isReversedAging(),
                    isArtilectBuilt(),
                    isEnvironmentalist(),
                    isHappy(),
                    isTradeSupremacy(),
                    isProsperity(),
                    isSpaceRace(),
                    isEducation(),
                    isEnlightenment()

            };
        }

        public boolean isVictory(boolean[] listOfVictoryConditions){
            return isOneAmong(listOfVictoryConditions);
        }

        public boolean isEnlightenment() {
            return Civilization.currentPlayer.population < sumOfPreachers();

        }

        private int sumOfPreachers() {
            return Units.sumOfUnit(UnitType.MONK);
        }




    }


    public class ReversedAging extends End {
        public String message() {
            String message = "The age of arrested aging has dawned! The Elixir of Youth perfected!";
            System.out.println(message);
            return message;
        }
    }

    public class Environment extends End {
        public String message() {
            String message = "Biodiversity prospers, many species return.";
            System.out.println();
            return message;
        }
    }

    public class SpaceRace extends End {
        public String message() {
            String message = "Space vessel to the nearest star arrives on Alpha Centauri.";
            System.out.println(message);
            return message;
        }
    }

    public class TradeSupremacy extends End {
        public String message() {
            String message = "All world corporations are run by your nation.";
            System.out.println();
            return message;
        }
    }

    public class Artilect extends End {
        public String message() {
            String message = "An artilect achieves, then surpasses human level intelligence.";
            System.out.println();
            return message;
        }
    }

    public class Prosperity extends End {
        public String message() {
            String message = "All benefit from the great wealth of your population.";
            System.out.println(message);
            return message;
        }
    }

    public class Happiness extends End {
        public String message() {
            String message = "Everyone gets happy.";
            System.out.println(message);
            return message;
        }
    }

    public class Education extends End {
        public String message() {
            String message = "Everyone is highly educated.";
            System.out.println(message);
            return message;
        }
    }

    public class Enlightenment extends End {
        public String message() {
            String message = "Everyone is enlightened!";
            System.out.println(message);
            return message;
        }
    }

    /**
     * Created by miku on 30/05/2017.
     */

    public class SocialDiversity extends End {
        public String message() {
            String message = "Countless nations have been vanquished, lack of diversity has formed a monoculture.";
            System.out.println(message);
            return message;
        }
    }

    public class LossOfFreedom extends End{
        public String message() {
            String message = "A tyrant nation has overtaken your nation, condemning you to slavery or extinction";
            System.out.println(message);
            return message;
        }
    }

    public class LossOfEarth extends End{
        public String message() {
            String message = "The planet's soil is desertified, jungles and forests burnt, air polluted, " +
                    "global warming cooks us all!";
            System.out.println(message);
            return message;
        }
    }

    public class NuclearWinter extends End{
        public String message() {
            String message = "An automated nuclear escalation causes a dustcloud to cover the earth for years, " +
                    "putting out all of sunlight for decades...";
            System.out.println(message);
            return message;
        }

    }

}


package civ.gui;

import civ.Control.Civilization;
import civ.Model.Data;

import javax.swing.*;

import static javax.swing.JOptionPane.*;
import javax.swing.JDialog;

/**
 * Created by miku on 31/05/2017.
 */
public class Setup {
    public Setup() {
        sizeDialogue();
        playersDialogue();
        nameDialogue();
    }


    private void sizeDialogue() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = {"SMALL", "NORMAL", "LARGE", "HUGE"};
        String initialSelection = "NORMAL";
        String question = Questions.message[0];
        Object selection =
                JOptionPane.showInputDialog(
                null,
                 question ,
                "Setup 1", QUESTION_MESSAGE,
                null,
                selectionValues,
                initialSelection);
        defineMapSize((String) selection);

    }
    private void playersDialogue() {
        JDialog.setDefaultLookAndFeelDecorated(true);

        Object[] selectionValues = {"3", "4", "5", "6", "7"};
        String initialSelection = "3";
        String question = Questions.message[1];
        Object selection =
                JOptionPane.showInputDialog(
                null,
                 question ,
                "Setup 2", QUESTION_MESSAGE,
                null,
                selectionValues,
                initialSelection);
        defineNumberOfPlayers((String) selection);

    }

    private void competencyDialogue(){
        JDialog.setDefaultLookAndFeelDecorated(true);

        Object[] selectionValues = {"Chieftain", "Leader", "King", "Emperor",
                "Deity"};
        String initialSelection = "Leader";
        String question = Questions.message[2];
        Object selection =
                JOptionPane.showInputDialog(
                        null,
                        question ,
                        "Setup 3", QUESTION_MESSAGE,
                        null,
                        selectionValues,
                        initialSelection);
        defineCompetency((String) selection);
    }

    private void defineCompetency(String selection) {
        Data.competency = selection;
    }

    private void nameDialogue(){
        JDialog.setDefaultLookAndFeelDecorated(true);

        String question = Questions.message[3];
        String name = JOptionPane.showInputDialog(
                        null,
                        question );
        defineName(name);
    }

    private void defineName(String name) {
        Data.firstPlayerName = name;
    }

    private void defineMapSize(String selection) {
        switch(selection){
            /*
            Small:     66 x 42  (6 players, 12 city-states, 3 natural wonders)
            Standard:  80 x 52  (8 players, 16 city-states, 4 natural wonders)
            Large:    104 x 64  (10 players, 20 city-states, 6 natural wonders)
            Huge:     128 x 80  (12 players, 24 city-states, 7 natural wonders)
             */
            case "SMALL":
                Civilization.gameMapSizeX = 66;
                Civilization.gameMapSizeY = 42;
                break;
            case "NORMAL":
                Civilization.gameMapSizeX = 80;
                Civilization.gameMapSizeY = 52;
                break;
            case "LARGE":
                Civilization.gameMapSizeX = 104;
                Civilization.gameMapSizeY = 64;
                break;
            case "HUGE":
                Civilization.gameMapSizeX = 128;
                Civilization.gameMapSizeY = 80;
            default:
                Civilization.gameMapSizeX = 80;
                Civilization.gameMapSizeY = 52;
        }
        System.out.println("MAPSIZE:"+ Civilization.gameMapSizeX + "  " +
        Civilization.gameMapSizeY);
    }

    private void defineNumberOfPlayers(String numberOfPlayers){
         Data.numberOfPlayers = Integer.parseInt(numberOfPlayers);
    }

    public static class Questions {
        static String[] message = {
                "What size of a world would you like?",
                "How many civilizations are there?",
                "How competent are you?",
                "What is your name?"
        };
    }
}

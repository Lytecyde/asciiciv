package civ.gui;

import civ.Control.Civilization;

import javax.swing.*;

/**
 * Created by miku on 31/05/2017.
 */
public class Setup {
    public Setup() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        sizeDialogue();

    }

    private void sizeDialogue() {
        Object[] selectionValues = {"SMALL", "NORMAL", "LARGE", "HUGE"};
        String initialSelection = "Normal Map";
        String question = Questions.message[0];
        Object selection = JOptionPane.showInputDialog(
                null,
                 question ,
                "Setup", JOptionPane.QUESTION_MESSAGE,
                null,
                selectionValues,
                initialSelection);
        defineMapSize((String) selection);
    }

    private void defineMapSize(String selection) {
        switch(selection){/*
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

        }
    }

    public static class Questions {
        static String[] message = {
                "What size of a world would you like?"
        };
    }
}

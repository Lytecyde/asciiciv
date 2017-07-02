package civ.gui;

import civ.Model.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miku on 29/06/2017.
 */
public class EndListener implements ActionListener{
    int i =0;
    @Override
    public void actionPerformed(ActionEvent e) {
        calculateTime();
        selectNextPlayer();
    }

    private void selectNextPlayer() {
        i = (i++) % Data.listOfPlayers.size();
        Data.Turn.currentPlayer = Data.listOfPlayers.get(i);
    }

    private void calculateTime() {
        int timeNow = civ.Control.Civilization.year;
        int timeDifference =
                (timeNow < 3000) ? 20 :
                    ((timeNow < 5000) ? 10 :
                            ((timeNow < 5500) ? 5 :
                                    ((timeNow < 5750) ? 2 :
                                            ((timeNow < 5850) ? 1 : 1))));

        timeNow += timeDifference;
        civ.Control.Civilization.year = timeNow;
    }
}


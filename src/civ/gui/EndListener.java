package civ.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miku on 29/06/2017.
 */
public class EndListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
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


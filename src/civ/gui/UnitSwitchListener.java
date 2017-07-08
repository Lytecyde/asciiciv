package civ.gui;


import civ.Control.Civilization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miku on 29/06/2017.
 */
public class UnitSwitchListener implements ActionListener{
    View v;
    public UnitSwitchListener(View view){
        v= view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View.currentUnitIndex = View.currentUnitIndex <
                Civilization.currentPlayer.unitCount -1 ?
                ++View.currentUnitIndex:0;
        v.updateUnitBoard();
        v.switchPlayer.setFocusPainted(false);
        v.requestFocus();
    }

}

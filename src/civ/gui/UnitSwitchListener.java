package civ.gui;



import civ.Control.Player;
import civ.Model.Data;
import civ.Model.Location;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static civ.Control.Civilization.currentPlayer;

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
        Player currentPlayer = Data.Turn.currentPlayer;
        View.currentUnitIndex = ++View.currentUnitIndex%
                (currentPlayer.unitCount -1);
        v.updateUnitBoard();
        v.switchPlayer.setFocusPainted(false);
        v.requestFocus();
        while(currentPlayer != null &&
                currentPlayer.units != null &&
                currentPlayer.units.list != null
                ){
            System.out.println("unitswitch" +
                    currentPlayer.units.list.get(0).getType());
            System.out.println("nation:" +
                    currentPlayer.units.list.getFirst().ownerNation);
            break;
        }
        while(currentPlayer.units.list.getFirst().location != null ){
            System.out.println("may be fine");
            Location firstUnit = currentPlayer
                    .units.list.getFirst().location;
            v.placeOldLabelBackTo(v.cursorLocation);
            v.placeCursorOnPanelAt(firstUnit);
            v.replaceWorldMap();
            break;
        }
    }
}

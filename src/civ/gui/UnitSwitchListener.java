package civ.gui;


import civ.Control.Player;
import civ.Model.Data;
import civ.Model.Location;

import javax.swing.JLabel;
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
                (currentPlayer.unitCount );
        View.UpdateUnitBoard uub = v.new UpdateUnitBoard(v.board);
        uub.updateUnitBoardWithCurrentPlayerUnit();
        v.switchPlayer.setFocusPainted(false);
        v.requestFocus();
        while(currentPlayer != null &&
                currentPlayer.units != null &&
                currentPlayer.units.list != null
                ){
            System.out.println("unitswitch" +
                    currentPlayer.units.list.get(
                            View.currentUnitIndex).getType());
            System.out.println("nation:" +
                    currentPlayer.identification.fullName);
            break;
        }
        while(currentPlayer.units.list.getFirst().location != null ){
            System.out.println("is fine");
            Location firstUnit = currentPlayer
                    .units.list.get(View.currentUnitIndex).location;
            v.new WorldMapFunctionality().replaceWorldMap();
            v.new CellGrid().fillCellGrid();
            //v.placeOldLabelBackTo(v.cursorLocation);
            String cursor =String.valueOf(Data.cursor);
            JLabel jLabel= v.new Placement().placementOnPanelAt(
                    cursor,
                    firstUnit);
            v.cursorLocation = firstUnit;
            v.new Replacement().showMap();
            break;
        }
    }
}

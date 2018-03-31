package civ.gui;

import civ.Control.Civilization;
import civ.Control.Player;
import civ.Model.Data;
import civ.Model.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by miku on 06/07/2017.
 */
public class PlayerSwitchListener implements ActionListener {

    private View v;

    public PlayerSwitchListener(View view){
        v = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Player nextPlayer = Civilization.getNext(Data.Turn.currentPlayer);
        String playerNation = nextPlayer.identification.fullName;
        System.out.println("switched to player:" + playerNation);
        Civilization.currentPlayer = nextPlayer;
        Data.Turn.currentPlayer = nextPlayer;
        Data.Turn.currentPlayerIndex =
                Civilization.listOfPlayers.indexOf(nextPlayer);
        updateView(playerNation);
    }

    private void updateView(String playerNation) {
        v.setTitle(playerNation);
        v.emptyUnitBoard(v.board);
        LinkedList<Unit> list = Civilization.currentPlayer.units.list;
        View.UpdateUnitBoard updateUnitBoard = v.new UpdateUnitBoard(v.board);
        updateUnitBoard.update(list);
        v.switchPlayer.setFocusPainted(false);
        v.nextUnit.doClick();
        v.requestFocus();
    }
}

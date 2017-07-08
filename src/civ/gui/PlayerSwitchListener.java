package civ.gui;

import civ.Control.Civilization;
import civ.Control.Player;
import civ.Model.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by miku on 06/07/2017.
 */
public class PlayerSwitchListener implements ActionListener {

    private View v;
    private LinkedList<Player> players;

    public PlayerSwitchListener(View view){
        v = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Player nextPlayer = Civilization.getNext(Civilization.currentPlayer);
        String playerNation = nextPlayer.nationName;
        v.setTitle(playerNation);
        System.out.println(playerNation);
        Civilization.currentPlayer = nextPlayer;
        Data.Turn.currentPlayer = nextPlayer;
        Data.Turn.currentPlayerIndex =
                Civilization.listOfPlayers.indexOf(nextPlayer);
        v.emptyUnitBoard();
        v.updateUnitBoardWith(Civilization.currentPlayer.units.list);
        v.switchPlayer.setFocusPainted(false);
        v.requestFocus();
    }
}

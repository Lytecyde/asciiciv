package civ.gui;

import civ.Model.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miku on 29/06/2017.
 */
public class EndListener implements ActionListener{
    int i =0;
    View v;
    @Override
    public void actionPerformed(ActionEvent e) {
        selectNextPlayer();
        v.endTurn.setFocusPainted(false);
        v.requestFocus();
    }
    public EndListener(View view){
        v = view;
    }
    private void selectNextPlayer() {
        i = isLastPlayer(i)?i++: returnToFirstPLayer();
        i = i % Data.listOfPlayers.size();
        Data.Turn.currentPlayer = Data.listOfPlayers.get(i);
    }

    private int returnToFirstPLayer(){
        calculateTime();
        int firstPlayerIndex =0;
        return firstPlayerIndex;
    }

    private boolean isLastPlayer(int i ) {
        return i == Data.listOfPlayers.size() - 1;
    }

    private void calculateTime() {
        int timeNow = civ.Control.Civilization.year;
        int timeDifference =
                isDuringPrimeAge(timeNow) ? 20 :
                    (isDuringAntiquity(timeNow) ? 10 :
                            (isDuringMiddleAges(timeNow) ? 5 :
                                    (isDuringRenaissance(timeNow) ? 2 :
                                            (isDuringEnlightenment(timeNow) ?
                                                    1 : 1))));
        timeNow += timeDifference;
        civ.Control.Civilization.year = timeNow;
        updateView(timeNow);
    }

    private void updateView(int timeNow) {
        v.setTitle(Data.Turn.currentPlayer.nationName);
        v.year.setText("Year: " + timeNow);
        v.updateUnitBoard();
        v.showControl();
        v.showMap();

    }

    private boolean isDuringEnlightenment(int timeNow) {
        return timeNow < 5850;
    }

    private boolean isDuringRenaissance(int timeNow) {
        return timeNow < 5750;
    }

    private boolean isDuringMiddleAges(int timeNow) {
        return timeNow < 5500;
    }

    private boolean isDuringAntiquity(int timeNow) {
        return timeNow < 4500;
    }

    private boolean isDuringPrimeAge(int timeNow) {
        return timeNow < 3000;
    }


}


package civ.gui;

import civ.Model.Data;

import javax.swing.*;
import java.io.Serializable;

public class Init implements Serializable {
    private final View view;

    public Init(View view) {
        this.view = view;
    }

    void initCellGridPanels() {
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                view.getCellPanelGrid()[x][y] = new JPanel();
            }
        }
    }

    void initGridLabels() {
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                view.getGridLabels()[x][y] = new JLabel();
            }
        }
    }

    void initVisibleGridLabels() {
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                view.getVisibleGrid()[x][y] = new JLabel();
            }
        }
    }
}
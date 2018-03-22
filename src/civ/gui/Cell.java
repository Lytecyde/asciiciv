package civ.gui;

import civ.Model.Data;
import civ.Model.Location;
import civ.Model.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Cell implements Serializable {
    private final View view;

    public Cell(View view) {
        this.view = view;
    }//CELLWORK


    protected void placeVisibleLabels() {
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                JLabel cellLabel = view.getVisibleGrid()[x][y];
                placeLabelOnMap(cellLabel, new Location(x, y));
            }
        }
    }

    void placeLabelOnMap(JLabel c, Location location) {
        c.setSize(view.getCellDimension());
        JPanel cell = view.getCellPanelGrid()[location.x][location.y];
        cell.setLayout(new GridLayout(1, 1));
        cell.setSize(view.getCellDimension());
        cell.add(c);
        cell.setVisible(true);
        replaceLabelOnCell(cell, c);
        (view.getWorldMapPanel()).add(cell);
        view.getWorldMapPanel().setVisible(true);
        view.new Replacement().showMap();;
    }

    void replaceLabelOnCell(JPanel jPanel, JLabel c) {
        jPanel.remove(0);
        jPanel.add(c);
    }

    JLabel[][] getCellLabelsFromWorldMapOriginal() {
        JLabel[][] localMapCells = new JLabel[Data.rows][Data.cols];
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                view.getGridLabels()[x][y] = WorldMap.mapCells[x][y];
                localMapCells[x][y] = view.getGridLabels()[x][y];
            }
        }
        return localMapCells;
    }

    protected void createCells() {
        JPanel p;
        Location l;
        for (int x = 0; x < Data.rows; x++) {
            for (int y = 0; y < Data.cols; y++) {
                p = getCell(x, y);
                l = new Location(x, y);
                addCellToWorldMap(p, l);
            }
        }
    }

    JPanel getCell(int x, int y) {
        JPanel p;
        p = view.getCellPanelGrid()[x][y];
        p.setLayout(new GridLayout(1, 1));
        p.add(view.getVisibleGrid()[x][y]);
        return p;
    }

    protected void addCellToWorldMap(JPanel p, Location loc) {
        (view.getWorldMapPanel()).add(p, loc.x, loc.y);
        view.new Replacement().showMap();
    }
}
package civ.gui;

import civ.Model.LandType;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class LandLabel implements Serializable {

    private final Dimension cellDimension = new Dimension(
            View.cellSize,
            View.cellSize);
    public LandLabel() {

    }

    public JLabel getLabelBy(LandType type) {
        JLabel c;
        switch (type) {
            case SEA:
                return seaLabel();

            case LAND:
               return landLabel();

            case MOUNTAIN:
                return mountainLabel();

            case DESERT:
                return desertLabel();

            default:
                return seaLabel();
        }
        //return c;
    }
    private JLabel landLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.green);
        label.setForeground(new Color(34,139,34));
        label.setText("♠");
        label.setPreferredSize(cellDimension);
        return label;

    }private JLabel riverLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(new Color(34,139,34));
        label.setForeground(Color.cyan);
        label.setText("~");
        label.setPreferredSize(cellDimension);
        return label;

    }
    private JLabel mountainLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.green);
        label.setForeground(Color.white);
        label.setText("M");
        label.setPreferredSize(cellDimension);
        return label;

    }

    private JLabel desertLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setForeground(Color.orange);
        label.setText("~");
        label.setPreferredSize(cellDimension);
        return label;
    }

    private JLabel seaLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setForeground(Color.black);
        label.setBackground(Color.cyan);
        label.setText("~");
        label.setPreferredSize(cellDimension);
        return label;
    }
}
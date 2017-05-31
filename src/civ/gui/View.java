package civ.gui;

import civ.Control.Civilization;

import javax.swing.*;
import java.awt.*;

/**
 * Created by miku on 30/05/2017.
 */
public class View extends JFrame {
    JPanel text = new JPanel();
    JPanel worldMap = new JPanel();
    
    JLabel[][] tileset = new JLabel[Civilization.gameMapSizeX][Civilization.gameMapSizeY];
    int x = 0;
    int y = 0;
    JPanel globeAndData = new JPanel();
    JPanel globeMap = new JPanel();
    JPanel dataBoard = new JPanel();
    char[][] map = new char[Civilization.gameMapSizeX][Civilization.gameMapSizeY];

    public View(){
        createMap();
        createTileSet();
        
        globeAndData.setLayout(new GridLayout(1,2));
        globeAndData.add(globeMap);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(text, BorderLayout.SOUTH);
        getContentPane().add(globeMap);
        pack();
        setVisible(true);
    }

    private void createMap() {

    }

    private void createTileSet() {

        dimensionXtilesetGenerator();
        //createMap and place on mapsigns and features

    }

    private void dimensionXtilesetGenerator() {
        int x,y = 0;
        for(x = 0; x <  90;x++)
            dimensionYtilesetGenerator(x);
    }

    private void dimensionYtilesetGenerator(int x) {
        int y;
        for(y =0; y < 52; y++) defineTileSet(x, y);
    }

    private void defineTileSet(int x, int y) {
        tileset[x][y] = new JLabel();
        tileset[x][y].setOpaque(true);

    }


}

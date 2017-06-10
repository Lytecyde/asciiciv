package civ.Model;

import civ.Control.Civilization;

import javax.swing.*;
import java.awt.*;


/**
 * Created by miku on 07/06/2017.
 */
public class WorldMap {
    private final Color sea = Color.CYAN;
    private final Color grassland = Color.green;
    private final Color desert = Color.yellow;
    private final Color mountain = Color.white;
    private static final int cols = 24;
    private static final int rows = 16;


    static int mapSizeX = Civilization.gameMapSizeX;
    static int mapSizeY = Civilization.gameMapSizeY;
    int labelSizeX, labelSizeY = 15;
    public static JPanel map = new JPanel();
    public JLabel[][] visibleMap = new JLabel[cols][rows];
    public static JPanel[][] panelHolderGrid = new JPanel[cols][rows];

    public static JLabel[][] mapCells = new JLabel[cols][rows];

    public WorldMap(){
        map.setLayout(new GridLayout(cols,rows));
        generateMap();
        map.setVisible(true);
        map.setFocusTraversalKeysEnabled(false);
    }

    public WorldMap(MapType type){
        map.setLayout(new GridLayout(cols, rows));
        map.setPreferredSize(new Dimension(480, 320));
        generateVisibleMap(cols, rows);
        map.repaint();
        map.setVisible(true);
    }

    private void generateVisibleMap( int maxCols, int maxRows) {
        defineGrid(maxCols, maxRows);
        JPanel[][] a = createContentsForGrid(maxCols, maxRows);

        //setAttributesFor(maxCols, maxRows);
        addCellsToGrid(maxCols, maxRows, a);
    }

    private void defineGrid(int mapSizeX, int mapSizeY) {
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {

                this.panelHolderGrid[x][y] = new JPanel();
            }
        }
    }

    private void setAttributesFor(int x, int y) {

        this.mapCells[x][y].setPreferredSize(
                        new Dimension(labelSizeX, labelSizeY));
                this.mapCells[x][y].setOpaque(true);
                this.mapCells[x][y].setBackground(sea);

                this.mapCells[x][y].setText("~");
                this.mapCells[x][y].setVisible(true);

    }

    private JPanel[][] createContentsForGrid(int mapSizeX, int mapSizeY) {
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {

                this.mapCells[x][y] = new JLabel();
                //setAttributesFor(x,y);
                this.mapCells[x][y].setPreferredSize(
                        new Dimension(labelSizeX, labelSizeY));
                this.mapCells[x][y].setOpaque(true);
                this.mapCells[x][y].setBackground(sea);

                this.mapCells[x][y].setText("~");
                this.mapCells[x][y].setVisible(true);

                panelHolderGrid[x][y].add(this.mapCells[x][y]);
                panelHolderGrid[x][y].setVisible(true);
            }
        }


        return panelHolderGrid;
    }

    public static void addCellsToGrid(int mapSizeX, int mapSizeY, JPanel[][] a){
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {

                map.add(a[x][y]);
                map.revalidate();
                map.repaint();
            }
        }

    }

    private void generateMap(){
        System.out.println("Generating seaworld");
        JPanel [] [] a = createContentsForGrid(mapSizeX, mapSizeY);
        setAttributesFor(mapSizeX, mapSizeY);
        addCellsToGrid(mapSizeX, mapSizeY,a);
    }

    public JPanel getMap() {
        return map;
    }


}


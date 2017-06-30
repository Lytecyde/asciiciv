package civ.Model;

import civ.Control.Civilization;
import civ.gui.LandLabel;

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
    private static final int rows = 16;
    private static final int columns = 24;


    static int mapSizeX = Civilization.gameMapSizeX;
    static int mapSizeY = Civilization.gameMapSizeY;
    int labelSizeX, labelSizeY = 15;
    public static JPanel map = new JPanel();
    public JLabel[][] visibleMap = new JLabel[rows][columns];
    public static JPanel[][] panelHolderGrid;

    public static JLabel[][] mapCells;
    private int landLabelMap[][] = new int[rows][columns];

    public WorldMap(){



    }

    public WorldMap(MapType type){
        generateLands();
        switch(type){
            case VISIBLE:
                mapCells = new JLabel[rows][columns];
                panelHolderGrid = new JPanel[rows][columns];
                map.setLayout(new GridLayout(rows, columns));
                map.setPreferredSize(new Dimension(360, 240));
                map.setVisible(true);
                map.setFocusTraversalKeysEnabled(false);
                defineLabels();
                defineGrid();
                setAttributesForAllLabels();
                generateMap();
                map.repaint();

                break;
            default:

        }

    }

    public void createGlobeMap() {
        //TODO:minimap
        //create tectonic plates
        //set directions for each plate
        //simulate plate movement
        //generate continents
        //map mountains
        //add glaciers
        // add deserts
    }

    private void generateLands(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {

                landLabelMap[x][y] =
                        (int)(Math.random()*LandType.values().length);
                System.out.println("lands of the map" + landLabelMap[x][y]);

            }
        }
    }

    private void setAttributesForAllLabels() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                setAttributesFor(x,y);
            }
        }
    }

    private void defineLabels() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                try {
                    this.mapCells[x][y] = new JLabel();
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Wrong mapCells at location");
                }
            }
        }
    }

    private void defineGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                this.panelHolderGrid[x][y] = new JPanel();
                panelHolderGrid[x][y].setPreferredSize(
                        new Dimension(15,15));
            }
        }
    }

    private void setAttributesFor(int x, int y) {
        try {
            this.mapCells[x][y].setPreferredSize(
                    new Dimension(labelSizeX, labelSizeY));
            this.mapCells[x][y].setOpaque(true);
            this.mapCells[x][y].setBackground(sea);
            this.mapCells[x][y].setText("~");
            this.mapCells[x][y].setVisible(true);
        }catch(NullPointerException e){
            System.out.println(e +""+ x +", "+ y);
        }
    }

    private void createMap() {
        //code for the asciimap goes here

    }

    private JPanel[][] getContentsPanelsForGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                setPanelWithCell(x, y);
            }
        }
        return panelHolderGrid;
    }

    private void setPanelWithCell(int x, int y) {
        JLabel mc = this.mapCells[x][y];
        int map = landLabelMap[x][y];
        LandType landtype =  LandType.values()[landLabelMap[x][y]];
        JLabel mapland = (new LandLabel()).getLabelBy(landtype);
        try {

            mc.setBackground(mapland.getBackground());
            mc.setForeground(mapland.getForeground());
            mc.setText(mapland.getText());
            this.panelHolderGrid[x][y].add(mc);
            this.panelHolderGrid[x][y].setVisible(true);
            this.panelHolderGrid[x][y].repaint();
        }
        catch(NullPointerException e){
                System.out.println(e);
            }
    }

    public static void addToGrid( JPanel[][] a){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                map.add(a[x][y]);
                map.revalidate();
                map.repaint();
            }
        }

    }

    private void generateMap(){



        JPanel[][] a = getContentsPanelsForGrid();

        addToGrid(a);
    }

    public JPanel getMap() {
        return map;
    }


}


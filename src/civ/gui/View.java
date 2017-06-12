package civ.gui;

import civ.Control.Civilization;
import civ.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import static javax.swing.BorderFactory.createBevelBorder;

/**
 * Created by miku on 30/05/2017.
 */
public class View extends JFrame implements ActionListener {
    Location location = new Location(7, 11);

    JPanel text = new JPanel();

    JTextArea textArea = new JTextArea(5, 80);

    int x = 0;
    int y = 0;
    public JPanel control = new JPanel();
    public JPanel globeMap = new JPanel();
    public JPanel dataBoard = new JPanel();
    public JPanel worldMapPanel = new JPanel();//TODO:should a controller supply the
    // data
    // actually
    char[][] map =
            new char[Civilization.gameMapSizeX][Civilization.gameMapSizeY];
    public static final int cols = 24;
    public static final int rows = 16;
    private final int cellSize = 15;
    private static WorldMap worldMapPanelContents;
    private static JLabel cursor;
    private static JLabel label;
    private JLabel transparentLabel;

    public View() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 640));
        this.setLayout(new BorderLayout());
        this.worldMapPanel = createContent();
        placeContentTo(this.worldMapPanel);
        MyKeyListener mkl = new MyKeyListener();
        addKeyListener(mkl);
        placeMenuBar();
        //
        addCellsToPanel(getCells());
        placeCursor(location);
        repaint();
        pack();

        setVisible(true);
    }

    private void placeMenuBar() {
        JMenuBar jmb = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        JMenuItem jmiSave = new JMenuItem("Save");
        JMenuItem jmiExit = new JMenuItem("Exit");

        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        //orders, ministrydata, ministry orders?, advances
        JMenu jmOrders = new JMenu("Orders");
        JMenuItem jmiOperate = new JMenuItem("Operate");
        JMenuItem jmiHeal = new JMenuItem("Heal");
        JMenuItem jmiVigil = new JMenuItem("Vigil");
        JMenuItem jmiFortify = new JMenuItem("Fortify");

        jmOrders.add(jmiOperate);
        jmOrders.add(jmiHeal);
        jmOrders.add(jmiVigil);
        jmOrders.add(jmiFortify);
        jmb.add(jmOrders);

        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);

        jmiOperate.addActionListener(this);
        jmiHeal.addActionListener(this);
        jmiVigil.addActionListener(this);
        jmiFortify.addActionListener(this);


        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);

        this.setJMenuBar(jmb);
    }

    private void placeContentTo(JPanel worldMap) {
        textArea.setEnabled(false);
        this.add(text, BorderLayout.SOUTH);
        this.add(control, BorderLayout.EAST);
        this.add(worldMap, BorderLayout.CENTER);
    }


    private JPanel createContent() {

        createGlobeMap();
        createControlPanel();
        createTextPanel();
        createMapPanel();
        worldMapPanel = createWorldMap();

        return worldMapPanel;
    }

    private void createGlobeMap() {
        //TODO:minimap
    }

    private JPanel createWorldMap() {
        worldMapPanelContents = new WorldMap(MapType.VISIBLE);
        worldMapPanel.add(worldMapPanelContents.getMap());
        placeMapLabels();
        worldMapPanel.setVisible(true);
        worldMapPanel.repaint();
        return worldMapPanel;
    }

    private void createTextPanel() {

        text.add(textArea);
    }

    private void createControlPanel() {
        control.setLayout(new GridLayout(2, 1));
        control.add(globeMap);

        createDataBoard();
        control.add(dataBoard);
        control.repaint();
    }

    private void createDataBoard() {
        dataBoard.setLayout(new GridLayout(2,1));
        JLabel funds = new JLabel("Funds: " +
                Civilization.currentPlayer.funds);
        JLabel pollution = new JLabel("Pollution: " +
                Civilization.currentPlayer.pollution);
        dataBoard.add(funds);
        dataBoard.add(pollution);
    }

    private void createMapPanel() {
        worldMapPanel.setPreferredSize(
                new Dimension(
                        cols * cellSize + 10,
                        rows * cellSize + 10));
        worldMapPanel.setVisible(true);
    }

    private void createMap() {
        //code for the asciimap goes here
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        textArea.setText(command);
        switch (command) {
            case "About":
                String about = "Game variant of the famed Civilization game," +
                        "in a minimalist setting. \n Civilization " +
                        "tends towards peace. \nTentatively " +
                        "GPL3";
                textArea.setText(about);
                break;
        }
    }


    private void placeCursor(Location location) {
        textArea.setText(location.getX() + ":"
                + location.getY() + "::"
                + Data.numberOfArrowKeyPresses);
        Data.numberOfArrowKeyPresses++;
        //remove a label from location
        JPanel p = WorldMap.panelHolderGrid[location.getX()][location.getY()];
        JLabel c = defineCursor((JLabel) p.getComponent(0));

        removeAndAdd(
                WorldMap.panelHolderGrid[location.getX()][location.getY()], c);

        WorldMap.addToGrid(WorldMap.panelHolderGrid);
        WorldMap.map.revalidate();
        WorldMap.map.repaint();
        //repaint
    }

    private void placeOldLabelBackTo(Location location) {
        JLabel c = seaLabel();
        removeAndAdd(
                WorldMap.panelHolderGrid[location.getX()][location.getY()], c);

        WorldMap.addToGrid(WorldMap.panelHolderGrid);
        WorldMap.map.revalidate();
        WorldMap.map.repaint();

    }
    private void placeLabel(Location location, LandType type) {
        JLabel c = getLabelBy(type);

        removeAndAdd(
                WorldMap.panelHolderGrid[location.getX()][location.getY()], c);

        WorldMap.addToGrid(WorldMap.panelHolderGrid);
        WorldMap.map.revalidate();
        WorldMap.map.repaint();
        //repaint
    }

    private JLabel getLabelBy(LandType type) {
        JLabel c;
        switch(type){
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
        label.setPreferredSize(new Dimension(cellSize, cellSize));
        return label;

    }
    private JLabel mountainLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.green);
        label.setForeground(Color.white);
        label.setText("M");
        label.setPreferredSize(new Dimension(cellSize, cellSize));
        return label;

    }
    private JLabel desertLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.yellow);
        label.setForeground(Color.white);
        label.setText("~");
        label.setPreferredSize(new Dimension(cellSize, cellSize));
        return label;

    }
    private JLabel seaLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.cyan);
        label.setText("~");
        label.setPreferredSize(new Dimension(cellSize, cellSize));
        return label;
    }
    private void placeMapLabels(){
        Location l;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                l = new Location(x,y);
                placeLabel(l,LandType.SEA);
            }
        }
    }

    private void removeAndAdd(JPanel jPanel, JLabel c) {
        jPanel.removeAll();
        jPanel.add(c);
    }

    private JLabel[][] getCells() {
        JLabel[][] localMapCells = new JLabel[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                localMapCells[x][y] = WorldMap.mapCells[x][y];

            }
        }
        return localMapCells;
    }

    private void addCellsToPanel(JLabel[][] localMapCells) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                this.worldMapPanel.add(localMapCells[x][y]);
                this.worldMapPanel.revalidate();
                this.worldMapPanel.repaint();
            }
        }
    }

    private JLabel defineCursor(JLabel cursor) {
        cursor = getCells()[location.x][location.y];
        Border raised = createBevelBorder(BevelBorder.RAISED,
                Color.PINK, Color.RED);
        //this.cursor.setBackground(Color.red);
        cursor.setBorder(raised);
        cursor.setPreferredSize(new Dimension(cellSize, cellSize));
        return cursor;
    }




    public class MyKeyListener implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            Location aftermove;
            System.out.println("" +
                    Data.numberOfArrowKeyPresses + " : " + e.getKeyCode());
            try {
                switch (e.getKeyCode()) {

                    case 37:
                        placeOldLabelBackTo(location);
                        aftermove = location.movement(DirectionType.NORTH);
                        placeCursor(aftermove);

                        break;
                    case 39:
                        placeOldLabelBackTo(location);
                        aftermove = location.movement(DirectionType.SOUTH);
                        placeCursor(aftermove);

                        break;
                    case 38:
                        placeOldLabelBackTo(location);
                        aftermove = location.movement(DirectionType.WEST);
                        placeCursor(aftermove);

                        break;
                    case 40:
                        placeOldLabelBackTo(location);
                        aftermove = location.movement(DirectionType.EAST);
                        placeCursor(aftermove);

                        break;

                }
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println(aiobe +"movement went out");
            }
        }
    }
}


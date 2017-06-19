package civ.gui;

import civ.Control.Civilization;
import civ.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.BorderFactory.createBevelBorder;

/**
 * Created by miku on 30/05/2017.
 */
public class View extends JFrame implements ActionListener {
    final Location CENTRE = new Location(11, 7);
    Location location = CENTRE;

    JTextArea textArea = new JTextArea(5, 60);
    int x;
    int y;
    JPanel text = new JPanel();
    public JPanel control = new JPanel();
    public JPanel globeMap = new JPanel();
    public JPanel dataBoard = new JPanel();
    //TODO:should a controller supply the data actually
    public JPanel worldMap = new JPanel();

    char[][] map =
            new char[Civilization.gameMapSizeX][Civilization.gameMapSizeY];
    public static final int cols = 24;
    public static final int rows = 16;
    private final int cellSize = 15;
    private static WorldMap worldMapPanelContents = new WorldMap(
            MapType.VISIBLE);
    private JLabel cursor;
    private static JLabel label;
    private final JLabel[][] gridLabels = new JLabel[rows][cols];
    private JLabel[][] visibleGrid = new JLabel[rows][cols];
    public JPanel[][] cellGrid = new JPanel[rows][cols];
    private JLabel temporaryContents;
    private Dimension cellDimension;

    public View() {
        setupView();
        placeMenuBar();
        initCellGrid();
        initLabels();
        testLabelsAll("0th");
        makeGridLabels();

        //
        createContent();
        getVisibleGridFromGridLabels();
        testLabelsAll("1st");
        makeCellGrid();
        placeMapLabels();
        addCellsToPanel();

        testLabelsAll("2nd");
        //
        placeCursorOnPanelAt(CENTRE);
        placeContentToView();
        makeCellGrid();

        testLabelsAll("3rd");
        setVisible(true);
        pack();
        repaint();
    }

    private void testLabelsAll(String s) {
        System.out.println(s+"test");
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                testLabel(x, y);
            }
        }
    }

    private void testLabel(int x, int y) {
        System.out.println(x + "," + y + visibleGrid[x][y].getText()
                + visibleGrid[x][y].getSize().height
        );

        System.out.println(x + "," + y + gridLabels[x][y].getText()
        );
    }


    private void setupView() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 640));
        setLayout(new BorderLayout());
        MyKeyListener mkl = new MyKeyListener();
        addKeyListener(mkl);
    }

    private void makeGridLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                gridLabels[x][y] = getCellLabelsFromWorldMap()[x][y];
            }
        }
    }

    private void getVisibleGridFromGridLabels() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                visibleGrid[x][y] = gridLabels[x][y];
                visibleGrid[x][y].setVisible(true);
                this.visibleGrid[x][y].setPreferredSize(
                        new Dimension(
                                cellSize,
                                cellSize));
            }
        }
    }

    private void makeCellGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                cellGrid[x][y].add(visibleGrid[x][y]);
                cellGrid[x][y].setVisible(true);
            }
        }
        showMap();
    }

    private void initCellGrid(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                cellGrid[x][y] = new JPanel();
            }
        }
    }

    private void initLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                visibleGrid[x][y] = new JLabel();
                gridLabels[x][y] = new JLabel();

            }
        }
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

        JMenu jmMinistries = new JMenu("Ministries");
        JMenuItem jmiFinancial = new JMenuItem("Financial");
        JMenuItem jmiSocial = new JMenuItem("Social");
        JMenuItem jmiInterior = new JMenuItem("Interior");
        JMenuItem jmiForeignAffairs = new JMenuItem("Foreign Affairs");
        JMenuItem jmiDefence = new JMenuItem("Defence");

        jmMinistries.add(jmiFinancial);
        jmMinistries.add(jmiSocial);
        jmMinistries.add(jmiInterior);
        jmMinistries.add(jmiForeignAffairs);
        jmMinistries.add(jmiDefence);
        jmb.add(jmMinistries);

        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);

        jmiFinancial.addActionListener(this);
        jmiSocial.addActionListener(this);
        jmiInterior.addActionListener(this);
        jmiForeignAffairs.addActionListener(this);


        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);

        this.setJMenuBar(jmb);
    }

    private void placeContentToView() {
        textArea.setEnabled(false);
        this.add(text, BorderLayout.SOUTH);
        this.add(control, BorderLayout.EAST);
        this.add(worldMap, BorderLayout.CENTER);
    }


    private void createContent() {

        createGlobeMap();
        createControlPanel();
        createTextPanel();
        createMapPanel();
        createWorldMap();
    }

    private void createGlobeMap() {
        //TODO:minimap
    }

    private JPanel createWorldMap() {
        worldMapPanelContents = new WorldMap(MapType.VISIBLE);
        worldMap.add(worldMapPanelContents.getMap());
        worldMap.setLayout(new GridLayout(rows, cols));
        placeMapLabels();
        worldMap.setVisible(true);
        worldMap.repaint();
        return worldMap;
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
        worldMap.setPreferredSize(
                new Dimension(
                        cols * cellSize + 10,
                        rows * cellSize + 10));
        worldMap.setVisible(true);
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


    private void placeCursorOnPanelAt(Location location) {
        JPanel p = cellGrid[location.x][location.y];
        placeCursorTo(location);
        replaceVisible(location, cursor);
        placeVisibleGridToCellGrid();
        showMap();

    }
    private void placeCursorTo(Location l) {
        JLabel c = new JLabel();
        temporaryContents = visibleGrid[l.x][l.y];
        c.setOpaque(true);
        c.setText(temporaryContents.getText());
        c.setForeground(Color.red);
        c.setBackground(Color.white);
        c.setPreferredSize(temporaryContents.getPreferredSize());
        c.setVisible(true);
        cursor = c;
        visibleGrid[l.x][l.y] = c;
    }
    private void placeOldLabelBackTo(Location previous) {
        JLabel c = new JLabel();
        //JLabel contents = gridLabels[previous.x][previous.y];
        c.setOpaque(true);
        c.setText(temporaryContents.getText());
        c.setForeground(temporaryContents.getForeground());
        c.setBackground(temporaryContents.getBackground());
        c.setPreferredSize(temporaryContents.getPreferredSize());
        c.setVisible(true);
        JPanel p = cellGrid[previous.x][previous.y];
        replaceVisible( previous , c);
        placeVisibleGridToCellGrid();
        showMap();

    }

    private void placeVisibleGridToCellGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                selectPanelFillings(x, y);
            }
        }

    }

    private void selectPanelFillings(int x, int y) {
        switch(panelComponentCount(cellGrid[x][y])){
            case 0:
                cellGrid[x][y].add(visibleGrid[x][y]);
                break;
            default:

                cellGrid[x][y] = new JPanel();
                cellGrid[x][y].add(visibleGrid[x][y]);
        }
        setVisible(cellGrid[x][y]);
    }

    private void setVisible(JPanel jPanel) {
        jPanel.setVisible(true);
        showMap();
    }

    private int panelComponentCount(JPanel p) {
        return p.getComponentCount();
    }

    private void replaceVisible(Location previous, JLabel c) {

        visibleGrid[previous.x][previous.y] = c;
        visibleGrid[previous.x][previous.y].setSize(
                cellDimension);

    }

    private void placeLabel(Location location) {
        JLabel c = visibleGrid[location.x][location.y];
        cellDimension = new Dimension(cellSize, cellSize);
        c.setSize(cellDimension);
        JPanel p = cellGrid[location.x][location.y];
        p.setSize(cellDimension);
        p.setVisible(true);
        replace(p, c);
        worldMap.add(p,location.x,location.y);
        showMap();
    }

    private void showMap() {

        worldMap.revalidate();
        worldMap.repaint();
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
    private void placeMapLabels(){
        Location l;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                l = new Location(x,y);
                placeLabel(l);
            }
        }
    }

    private void replace(JPanel jPanel, JLabel c) {
        jPanel.removeAll();
        jPanel.add(c);
    }

    private JLabel[][] getCellLabelsFromWorldMap() {
        JLabel[][] localMapCells = new JLabel[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                gridLabels[x][y] = WorldMap.mapCells[x][y];
                //debug lable:
                // 1 sinine rida
                localMapCells[x][y] = gridLabels[x][y];

            }
        }
        return localMapCells;
    }

    private boolean isLabel(JLabel jLabel) {
        return (jLabel instanceof JLabel);
    }

    private void addCellsToPanel() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

                worldMap.add(visibleGrid[x][y]);
                showMap();
            }
        }
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
            Location previous = new Location(location.getX(), location.getY());
            System.out.println("" +
                    Data.numberOfArrowKeyPresses + " : " + e.getKeyCode());
            JLabel[][] g;
            try {
                switch (e.getKeyCode()) {

                    case 37:
                        aftermove = previous.movement(DirectionType.NORTH);
                        System.out.println("location:"+previous.x +", "+
                                previous.y +"::"+aftermove.x + ", " +
                                aftermove.y);
                        placeOldLabelBackTo(previous);
                        placeCursorOnPanelAt(aftermove);
                        location = aftermove;
                        break;
                    case 39:
                        aftermove = previous.movement(DirectionType.SOUTH);
                        System.out.println("location:"+previous.x +", "+
                                previous.y +"::"+aftermove.x + ", " +
                                aftermove.y);
                        placeOldLabelBackTo( previous);
                        placeCursorOnPanelAt(aftermove);
                        location = aftermove;
                        break;
                    case 38:
                        aftermove = previous.movement(DirectionType.WEST);
                        System.out.println("location:"+previous.x +", "+
                                previous.y +"::"+aftermove.x + ", " +
                                aftermove.y);
                        placeOldLabelBackTo( previous);
                        placeCursorOnPanelAt(aftermove);
                        location = aftermove;

                        break;
                    case 40:
                        aftermove = previous.movement(DirectionType.EAST);
                        System.out.println("location:"+previous.x +", "+
                                previous.y +"::"+aftermove.x + ", " +
                                aftermove.y);
                        placeOldLabelBackTo(previous);
                        placeCursorOnPanelAt(aftermove);
                        location = aftermove;

                        break;

                }
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println(aiobe +"movement went out");
            }
        }
    }

    private boolean isbothEqual(Location location, Location aftermove) {
        return (location.x == aftermove.x) && (location.y == aftermove.y);
    }


}


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
    public JPanel worldMap = new JPanel();

    char[][] map =
            new char[Civilization.gameMapSizeX][Civilization.gameMapSizeY];
    public static final int cols = 24;
    public static final int rows = 16;

    public static final int cellSize =15;
    private static WorldMap worldMapPanelContents = new WorldMap(
            MapType.VISIBLE);
    private JLabel cursor;
    private static JLabel label;
    private final JLabel[][] gridLabels = new JLabel[rows][cols];
    private JLabel[][] visibleGrid = new JLabel[rows][cols];
    public JPanel[][] cellGrid = new JPanel[rows][cols];
    private JLabel temporaryContents;
    private Dimension cellDimension = new Dimension(cellSize, cellSize);

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
        fillCellGrid();
        placeLabels();
        createCells();

        testLabelsAll("2nd");
        //
        System.out.println(location.x +" "+location.y);
        placeCursorOnPanelAt(CENTRE);
        placeContentToView();
        fillCellGrid();

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

        System.out.println(x +
                "," +
                y +
                gridLabels[x][y].getText()

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
                this.visibleGrid[x][y].setPreferredSize(cellDimension);
            }
        }
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

        jmiOperate.addActionListener(this);
        jmiHeal.addActionListener(this);
        jmiVigil.addActionListener(this);
        jmiFortify.addActionListener(this);

        jmiFinancial.addActionListener(this);
        jmiSocial.addActionListener(this);
        jmiInterior.addActionListener(this);
        jmiForeignAffairs.addActionListener(this);
        jmiDefence.addActionListener(this);

        jmiFinancial.addActionListener(this);
        jmiSocial.addActionListener(this);
        jmiInterior.addActionListener(this);
        jmiForeignAffairs.addActionListener(this);
        jmiDefence.addActionListener(this);

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

    protected JPanel createWorldMap() {

        worldMap.setLayout(new GridLayout(rows, cols));
        worldMap.setSize(320,240);
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
        dataBoard.setLayout(new GridLayout(4,1));
        JLabel funds = new JLabel("Funds: " +
                Civilization.currentPlayer.funds);
        JLabel pollution = new JLabel("Pollution: " +
                Civilization.currentPlayer.pollution);
        JLabel tax = new JLabel("Taxrate: " +
                Civilization.currentPlayer.tax);
        JLabel year = new JLabel("Year: " +
                Civilization.year);
        dataBoard.add(year);
        dataBoard.add(tax);
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
            default:
                textArea.setText(command);
        }
    }


    protected void placeCursorOnPanelAt(Location location) {
        placeCursorTo(location);
        replaceVisible(location, cursor);
        fillCellGrid();
        showMap();

    }
    private void placeCursorTo(Location l) {
        JLabel c = new JLabel();
        temporaryContents = gridLabels[l.x][l.y];
        c.setOpaque(true);
        c.setText(temporaryContents.getText());
        c.setForeground(Color.red);
        c.setBackground(Color.white);
        c.setPreferredSize(temporaryContents.getPreferredSize());
        c.setVisible(true);
        cursor = c;
        visibleGrid[l.x][l.y] = c;
    }
    public void placeOldLabelBackTo(Location previous) {
        JLabel c = getPreviousLabel();

        replaceVisible( previous , c);
        fillCellGrid();
        showMap();
        //TODO:destruct temporaryContents as null
    }

    private JLabel getPreviousLabel() {
        JLabel c = new JLabel();

        c.setOpaque(true);
        c.setText(temporaryContents.getText());
        c.setForeground(temporaryContents.getForeground());
        c.setBackground(temporaryContents.getBackground());
        c.setPreferredSize(temporaryContents.getPreferredSize());
        c.setVisible(true);
        return c;
    }

    private void fillCellGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                fillCell(x, y);
                showMap();
            }
        }
    }

    private void fillCell(int x, int y) {

        defineCellGrid(x, y);

        cellGrid[x][y].add(visibleGrid[x][y]);
        setVisible(cellGrid[x][y]);

    }

    private void defineCellGrid(int x, int y) {
        cellGrid[x][y] = new JPanel();
        cellGrid[x][y].setLayout(new GridLayout(1,1));
        cellGrid[x][y].setSize(cellDimension);
    }

    private void setVisible(JPanel jPanel) {
        jPanel.setVisible(true);
        showMap();
    }



    private void replaceVisible(Location previous, JLabel c) {

        visibleGrid[previous.x][previous.y] = c;
        visibleGrid[previous.x][previous.y].setText(c.getText());
        visibleGrid[previous.x][previous.y].setForeground(c.getForeground());
        visibleGrid[previous.x][previous.y].setBackground(c.getBackground());
        visibleGrid[previous.x][previous.y].setSize(cellDimension);

    }

    private void placeLabel(JLabel c, Location location) {


        c.setSize(cellDimension);
        JPanel cell = cellGrid[location.x][location.y];
        cell.setLayout(new GridLayout(1,1));
        cell.setSize(cellDimension);
        cell.add(c);
        cell.setVisible(true);
        replaceLabelOnCell(cell, c);

        worldMap.add(cell,location.y,location.x);
        worldMap.setVisible(true);
        showMap();
    }

    private void showMap() {
        worldMap.revalidate();
        worldMap.repaint();
    }


    protected void placeLabels(){
        Location l;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                l = new Location(x,y);
                JLabel cellLabel = visibleGrid[l.x][l.y];//TODO:was location

                placeLabel(cellLabel, l);
            }
        }
    }

    private void replaceLabelOnCell(JPanel jPanel, JLabel c) {
        jPanel.remove(0);//
        jPanel.add(c);

    }

    private JLabel[][] getCellLabelsFromWorldMap() {
        JLabel[][] localMapCells = new JLabel[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                gridLabels[x][y] = WorldMap.mapCells[x][y];
                localMapCells[x][y] = gridLabels[x][y];
            }
        }
        return localMapCells;
    }



    protected void createCells() {
        JPanel p;
        Location l;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                testCountComponentsOfCell(x, y);
                p = getCell(x, y);
                l= new Location(x,y);
                addCellToWorldMap(p, l);
            }
        }

    }

    private void testCountComponentsOfCell(int x, int y) {
        System.out.println("componentlength at xy "+
                x+
                ","+
                "y"+
                y +
                cellGrid[x][y].getComponents().length);
        final int SINGLE_COMPONENT = 1;
        assert cellGrid[x][y].getComponents().length <= SINGLE_COMPONENT;
        String exceptionMessage;
        exceptionMessage = "Exception!!!!!!!!!";
        exceptionMessage =
                cellGrid[x][y].getComponents().length <= SINGLE_COMPONENT ?
                        exceptionMessage :
                        "";
        System.out.print(exceptionMessage);
    }

    private JPanel getCell(int x, int y) {
        JPanel p;
        p = cellGrid[x][y];
        p.setLayout(new GridLayout(1,1));
        p.add(visibleGrid[x][y]);
        return p;
    }

    protected void addCellToWorldMap(JPanel p, Location loc) {

                worldMap.add(p,loc.x,loc.y);
                showMap();


    }
    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            Location aftermove;
            Location previous = new Location(
                    location.getX(),
                    location.getY()
            );
            System.out.println("" +
                    Data.numberOfArrowKeyPresses +
                    " : " +
                    e.getKeyCode());

            try {
                switch (e.getKeyCode()) {

                    case 37:
                        aftermove = previous.movement(DirectionType.SOUTH);
                        testLocationPrint(aftermove, previous);
                        replaceLabelThenMap(aftermove, previous);
                        setLoc(aftermove);
                        break;
                    case 39:
                        aftermove = previous.movement(DirectionType.NORTH);
                        testLocationPrint(aftermove, previous);
                        replaceLabelThenMap(aftermove, previous);
                        setLoc(aftermove);
                        break;
                    case 38:
                        aftermove = previous.movement(DirectionType.EAST);
                        testLocationPrint(aftermove, previous);
                        replaceLabelThenMap(aftermove, previous);
                        setLoc(aftermove);
                        break;
                    case 40:
                        aftermove = previous.movement(DirectionType.WEST);
                        testLocationPrint(aftermove, previous);
                        replaceLabelThenMap(aftermove, previous);
                        setLoc(aftermove);
                        break;
                    default:

                }
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println(aiobe + "movement went out");
            }
        }

        private void replaceLabelThenMap(Location aftermove, Location previous) {
            placeOldLabelBackTo(previous);
            placeCursorOnPanelAt(aftermove);
            replaceWorldMap();
        }
    }


    void setLoc(Location aftermove) {
        this.location = aftermove;
    }

    private void replaceWorldMap() {
        worldMap.removeAll();
        //TODO: define cellGrid
        initCellGrid();
        fillCellGrid();
        placeLabels();
        worldMap = createWorldMap();
        createCells();
        worldMap.setVisible(true);

    }

    private void testLocationPrint(Location aftermove, Location previous) {
        System.out.println("location:"+previous.x +", "+
                previous.y +"::"+aftermove.x + ", " +
                aftermove.y);
    }




}


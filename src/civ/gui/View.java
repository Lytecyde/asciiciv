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
    Location location = new Location(7,11);

    JPanel text = new JPanel();

    JTextArea textArea = new JTextArea(5, 80);

    int x = 0;
    int y = 0;
    public JPanel control = new JPanel();
    public JPanel globeMap = new JPanel();
    public JPanel dataBoard = new JPanel();
    public  JPanel worldMapPanel = new JPanel();//TODO:should a controller supply the
    // data
    // actually
    char[][] map =
            new char[Civilization.gameMapSizeX][Civilization.gameMapSizeY];
    public static final int cols = 24;
    public static final int rows = 16;
    private final int cellSize = 15;
    private static WorldMap worldMapPanelContents;
    private static JLabel cursor;
    public View(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.worldMapPanel = createContent();
        placeContent(this.worldMapPanel);
        MyKeyListener mkl = new MyKeyListener();
        addKeyListener(mkl);
        placeMenuBar();
        //
        defineCursor();
        placeCursor(location);
        repaint();
        pack();
        setPreferredSize(new Dimension(640,400));
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


        jmiExit.addActionListener( this);
        jmiAbout.addActionListener(this);

        this.setJMenuBar(jmb);
    }

    private void placeContent( JPanel worldMap) {
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
        System.out.println("Generating the map");
        worldMapPanelContents = new WorldMap(MapType.VISIBLE);

        worldMapPanel.add(worldMapPanelContents.getMap());
        worldMapPanel.repaint();
        return worldMapPanel;
    }

    private void createTextPanel() {

        text.add(textArea);
    }

    private void createControlPanel() {
        control.setLayout(new GridLayout(1,2));
        control.add(globeMap);

        JLabel funds = new JLabel("Funds: " +
                Civilization.currentPlayer.funds);
        dataBoard.add(funds);
        control.add(dataBoard);
        control.repaint();
    }

    private void createMapPanel() {


        worldMapPanel.setPreferredSize(
                new Dimension(
                    cols*cellSize+10, rows*cellSize+10));

        worldMapPanel.setVisible(true);

    }

    private void createMap() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        textArea.setText(command);
        switch(command){
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
        textArea.setText(location.getX() +":"
                +location.getY()+"::"
                +Data.numberOfArrowKeyPresses);
        Data.numberOfArrowKeyPresses++;
        //remove a label from location
        WorldMap.panelHolderGrid[location.getX()][location.getY()]
         .removeAll();
        WorldMap.panelHolderGrid[location.getX()][location.getY()].add
                (this.cursor);

        WorldMap.addCellsToGrid(cols,rows, WorldMap.panelHolderGrid);
        WorldMap.map.revalidate();
        WorldMap.map.repaint();
        //repaint
    }

    private JLabel[][] getCells() {
        JLabel[][] localMapCells = new JLabel[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                localMapCells[x][y] = WorldMap.mapCells[x][y];
            }
        }
        return localMapCells;
    }

    private void addCellsToPanel(JLabel[][] localMapCells) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                this.worldMapPanel.add(localMapCells[x][y]);
                this.worldMapPanel.revalidate();
                this.worldMapPanel.repaint();
            }
        }

    }

    private void defineCursor() {
        this.cursor = new JLabel();
        Border raised = createBevelBorder(BevelBorder.RAISED,
                Color.PINK, Color.RED);
        this.cursor.setBackground(Color.red);
        this.cursor.setBorder(raised);
        this.cursor.setPreferredSize(new Dimension(cellSize,cellSize));
    }

    public class MyKeyListener implements KeyListener{
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
                    Data.numberOfArrowKeyPresses +" : "+ e.getKeyCode());
            try {
                switch (e.getKeyCode()) {

                    case 37:

                        location.movement(DirectionType.NORTH);
                        placeCursor(location);

                        break;
                    case 39:
                        location.movement(DirectionType.SOUTH);
                        placeCursor(location);

                        break;
                    case 38:
                        location.movement(DirectionType.WEST);
                         placeCursor(location);

                        break;
                    case 40:
                        location.movement(DirectionType.EAST);
                        placeCursor(location);

                        break;

                }
            }catch(ArrayIndexOutOfBoundsException aiobe){
                System.out.println( aiobe );
            }
        }
    }

}

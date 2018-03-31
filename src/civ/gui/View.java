package civ.gui;

import civ.Control.Civilization;
import civ.Control.Player;
import civ.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import static civ.Model.Data.cols;
import static civ.Model.Data.rows;
import static javax.swing.BorderFactory.createBevelBorder;

/**
 * Created by miku on 30/05/2017.
 */
public class View extends JFrame implements ActionListener {

    private final Init init = new Init(this);
    private final Cell cell = new Cell(this);
    Location cursorLocation = Data.CENTRE;

    JTextArea textArea = new JTextArea(5, 52);

    int y;
    JPanel text = new JPanel();
    public JPanel control = new JPanel();
    public JPanel globeMapPanel = new JPanel();
    public static JLabel funds, pollution, tax, year;//for databoard
    public static JLabel unitType, veteran; //for unit
    public static JButton nextUnit;
    public static JLabel artifacts;
    public static JLabel terrainType;
    public static JLabel structure;


    public JPanel worldMapPanel = new JPanel();
    public CellGrid cellGrid = new CellGrid();


    public static final int cellSize = 15;
    public static WorldMap worldMapPanelContents;
    public JLabel cursor;
    private static JLabel label;
    public final JLabel[][] gridLabels = new JLabel[rows][cols];
    public JLabel[][] visibleGrid = new JLabel[rows][cols];
    public JPanel[][] cellPanelGrid = new JPanel[rows][cols];

    private Dimension cellDimension = new Dimension(cellSize, cellSize);
    public static int currentUnitIndex = 0;
    public JButton endTurn = new JButton("End this Turn.");
    public JButton switchPlayer = new JButton("Next player");
    public Board board;

    public Unit active;
    public Unit underCursor;
    private JLabel activeLabel;
    private JLabel temporaryContentsUnit;
    private JLabel temporaryContentsCursor = new JLabel();


    public View() {
    }

    public View(WorldMap wm) {
        board = new Board(this);
        worldMapPanelContents = wm;

        preparations();
        makeContents();
        placeAllContentToView();
        placeUnits();

        setFirstActiveUnit();
        new WorldMapFunctionality().replaceWorldMap();
        worldMapPanel.requestFocus();

        setVisible(true);
        pack();
        repaint();
    }

    private void setFirstActiveUnit() {
        active = Data.listOfPlayers.getFirst().units.list.getFirst();
    }

    private void placeAllContentToView() {
        ViewContents c = new ViewContents();
        c.new Placer().invoke();
    }

    private void makeContents() {
        ViewContents c = new ViewContents();
        c.new Maker().invoke();
    }

    private void preparations() {
        new Preparations().invoke();
    }

    public void setAllUnitLocationsAtStart() {

    }

    public void placeUnits() {
        new UnitPlacer(this).invoke();
        //TODO:placeSatellites();
    }


    //SKETCH
    public void placeCities() {
        String citySign = "#";
        MapCoordinates cityLocation;
        for (Player p : Data.listOfPlayers) {
            for (City c : p.cities.list) {
                //TODO roughly
                cityLocation = c.getLocation();

                //Location loc = new Location(
                //        cityLocation.getX(),
                //        cityLocation.getY()
                //);
                //addCellToWorldMap(cell, loc);
            }
        }
    }

    private void createContentDefinitions() {
        board = new Board(this);
        ViewContents c = new ViewContents();

        ViewContents.Definitions d = c.new Definitions(this, board);
    }

    public JPanel[][] getCellPanelGrid() {
        return cellPanelGrid;
    }

    public JLabel[][] getGridLabels() {
        return gridLabels;
    }

    public JLabel[][] getVisibleGrid() {
        return visibleGrid;
    }

    public Dimension getCellDimension() {
        return cellDimension;
    }

    public JPanel getWorldMapPanel() {
        return worldMapPanel;
    }

    public class WorldMapFunctionality {
        private JPanel createWorldMap() {
            ViewContents c = new ViewContents();
            c.new Definitions().createWorldMap();
            return worldMapPanel;
        }

        public void replaceWorldMap() {
            worldMapPanel.removeAll();
            init.initCellGridPanels();
            new CellGrid().fillCellGrid();
            //clearMapToNature();//either or prolly two functions needed
            cell.placeVisibleLabels();
            worldMapPanel = createWorldMap();
            cell.createCells();
            worldMapPanel.setVisible(true);
        }

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
            case "Exit":
                //save game dialog are you sure?
                System.exit(0);
                break;
            case "Operation":
                //TODO:NEXT !!!
                new civ.Model.Operation(Data.Turn.currentPlayer);
                break;
            default:
                textArea.setText(command);
        }
    }

    private void operate(Unit unit) {
        //unit.works();
    }

    //placement of units and cursors on map
    public class Placement {
        protected JLabel placementOnPanelAt(String letter,
                                            Location location) {
            cursorLocation = location;
            JLabel receptacle = placement(letter, location);
            new Replacement().replaceVisible(location, receptacle);
            cellGrid.fillCellGrid();
            new Replacement().showMap();
            return receptacle;
        }


        private JLabel placement(String letter, Location l) {
            JLabel c = new JLabel();
            temporaryContentsUnit = gridLabels[l.x][l.y];
            c.setOpaque(true);
            c.setText(letter); //@
            c.setForeground(Color.red);
            c.setBackground(Color.white);
            c.setPreferredSize(temporaryContentsUnit.getPreferredSize());
            c.setVisible(true);
            c.setFocusable(true);
            c.requestFocus();
            assignment(c, l);
            return c;
        }

        private void assignment(JLabel c, Location l) {
            visibleGrid[l.x][l.y] = c;
        }

    }


    public class Replacement {
        public void returnToBefore(JLabel temporary, Location previous) {
            JLabel c = getPrevious(temporary);
            replaceVisible(previous, c);
            cellGrid.fillCellGrid();
            showMap();
        }

        private JLabel getPrevious(JLabel temporary) {
            JLabel c = new JLabel();
            c.setOpaque(true);
            c.setText(temporary.getText());
            c.setForeground(temporary.getForeground());
            c.setBackground(temporary.getBackground());
            c.setPreferredSize(temporary.getPreferredSize());
            c.setVisible(true);
            return c;
        }

        public void replaceVisible(Location previous, JLabel c) {
            visibleGrid[previous.x][previous.y] = c;
            visibleGrid[previous.x][previous.y].setText(c.getText());
            visibleGrid[previous.x][previous.y].setForeground(c.getForeground());
            visibleGrid[previous.x][previous.y].setBackground(c.getBackground());
            visibleGrid[previous.x][previous.y].setSize(cellDimension);
        }


        public void showMap() {
            worldMapPanel.revalidate();
            worldMapPanel.repaint();
        }
    }


    public class CellGrid {
        public void fillCellGrid() {
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    fillCell(x, y);
                    new Replacement().showMap();
                }
            }
        }

        private void fillCell(int x, int y) {
            defineCellGrid(x, y);
            cellPanelGrid[x][y].add(visibleGrid[x][y]);
            setVisible(cellPanelGrid[x][y]);
        }

        private void defineCellGrid(int x, int y) {
            cellPanelGrid[x][y] = new JPanel();
            cellPanelGrid[x][y].setLayout(new GridLayout(1, 1));
            cellPanelGrid[x][y].setSize(cellDimension);
        }

        private void setVisible(JPanel jPanel) {
            jPanel.setVisible(true);
            new Replacement().showMap();
        }
    }

    public void showControl(Board board) {
        board.data.revalidate();
        board.data.repaint();
        control.revalidate();
        control.repaint();
    }

    public boolean emptyUnitBoard(Board board) {
        board.unit.removeAll();
        return false;
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
            Location previous;

            System.out.println("Keypressed" +
                    Data.numberOfArrowKeyPresses++ +
                    " : " +
                    e.getKeyCode());
            try {
                previous = cursorLocation;
                cursorMovements(e, previous, board);
                previous = cursorLocation;
                unitMovements(e, previous);
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println(aiobe + "movement went out");
            }
        }

        private void unitMovements(KeyEvent e, Location previous) {
            MoveUnit mu =
                    active.chit == Data.landChit ? new MoveLandUnit() :
                    active.chit == Data.seaChit ? new MoveSeaUnit() :
                    active.chit == Data.airChit ? new MoveAirUnit() :
                    active.chit == Data.rocketChit ? new MoveToOrbit() :
                                                    new StayPut();
            char keyPressed = e.getKeyChar();
            MoveInDirection mid =
                    keyPressed == 'd' ? new MoveSouth(mu) :
                            keyPressed == 'a' ? new MoveNorth(mu) :
                                    keyPressed == 's' ? new MoveWest(mu) :
                                            keyPressed == 'w' ? new MoveEast(mu) :
                                                    new StayAtSpot();
            mid.move(previous);
        }

        abstract class MoveUnit {
            abstract void move(Location previous, DirectionType direction);
        }

        class StayPut extends MoveUnit {
            void move(Location previous, DirectionType direction) {
            }
        }

        class MoveLandUnit extends MoveUnit {

            void move(Location previous, DirectionType direction) {
                Location aftermove = getLocationAfterMove(previous, direction);
                while (!(visibleGrid[aftermove.x][aftermove.y].getBackground()
                        .equals(Color.cyan))) {
                    moveUnit(previous, direction);
                    break;
                }
            }
        }

        class MoveSeaUnit extends MoveUnit {
            void move(Location previous, DirectionType direction) {
                Location aftermove = getLocationAfterMove(previous, direction);
                while (visibleGrid[aftermove.x][aftermove.y].getBackground()
                        .equals(Color.cyan)) {
                    moveUnit(previous, direction);
                    break;
                }
            }
        }

        class MoveAirUnit extends MoveUnit {
            void move(Location previous, DirectionType direction) {
                moveUnit(previous, direction);
            }
        }

        class MoveToOrbit extends MoveUnit {
            void move(Location previous, DirectionType direction) {
                //countdown...
                // orbit achieved!
            }
        }

        private void moveUnit(
                Location previous,
                DirectionType direction) {
            Location aftermove = getLocationAfterMove(previous, direction);
            temporaryContentsUnit = visibleGrid[previous.x][previous.y];
            replaceUnitThenMap(aftermove, previous);
            checkForUnit(aftermove);
            moveCursor(previous, direction, board);
        }

        private Location getLocationAfterMove(
                Location previous,
                DirectionType direction) {
            Location aftermove;
            aftermove = previous.movement(direction);
            Player currentPlayer = Data.Turn.currentPlayer;
            LinkedList<Unit> ul = currentPlayer.units.list;
            ul.get(currentUnitIndex).location = aftermove;
            return aftermove;
        }

        abstract class MoveInDirection {
            abstract void move(Location previous);
        }

        class MoveSouth extends MoveInDirection {
            MoveUnit mu;

            MoveSouth(MoveUnit mu) {
                this.mu = mu;
            }

            @Override
            void move(Location previous) {
                mu.move(previous, DirectionType.SOUTH);
            }

        }

        class MoveWest extends MoveInDirection {
            MoveUnit mu;

            MoveWest(MoveUnit mu) {
                this.mu = mu;
            }

            @Override
            void move(Location previous) {
                mu.move(previous, DirectionType.WEST);
            }
        }

        class MoveNorth extends MoveInDirection {
            MoveUnit mu;

            MoveNorth(MoveUnit mu) {
                this.mu = mu;
            }

            @Override
            void move(Location previous) {
                mu.move(previous, DirectionType.NORTH);
            }
        }

        class MoveEast extends MoveInDirection {
            MoveUnit mu;

            MoveEast(MoveUnit mu) {
                this.mu = mu;
            }

            @Override
            void move(Location previous) {
                mu.move(previous, DirectionType.EAST);
            }
        }

        private void placeRemainingUnitsBack(Location previous) {
            LinkedList<Unit> ulAtPrevious;
            ulAtPrevious = getUnitsAt(previous);
            while (ulAtPrevious.size() > 0) {
                new Replacement().returnToBefore(
                        temporaryContentsUnit,
                        previous);
                for (Unit u : ulAtPrevious) placeUnit(u);
                break;
            }
        }


        //use for first turn first player
        private void placeActiveUnitOnPanelAt(Location location) {
            setActiveUnitsLocation(location);
            while (active != null) {
                placeUnit(active);
            }
            JLabel activeunit = makeUnitLabel(location);
            new Replacement().replaceVisible(location, activeunit);
            cellGrid.fillCellGrid();
            new Replacement().showMap();
        }

        private JLabel makeUnitLabel(Location location) {
            JLabel c = new JLabel();
            temporaryContentsUnit = gridLabels[location.x][location.y];
            activeLabel = makeUnitJLabel(c);
            visibleGrid[location.x][location.y] = c;
            return c;
        }

        private JLabel makeUnitJLabel(JLabel c) {
            c.setOpaque(true);
            c.setText(temporaryContentsUnit.getText());
            c.setForeground(active.colors);
            c.setPreferredSize(temporaryContentsUnit.getPreferredSize());
            c.setVisible(true);
            c.setFocusable(true);
            c.requestFocus();
            return c;
        }


        public LinkedList<Location> createAllUnitsLocations() {
            LinkedList<Location> ull = new LinkedList<Location>();
            for (Player p : Data.listOfPlayers) {
                for (Unit unit : p.units.list) {
                    ull.add(unit.location);
                }
            }
            return ull;
        }

        public int unitCountAt(Location x) {
            LinkedList<Location> ull = createAllUnitsLocations();
            int countUnits = 0;
            for (int i = 0; i < ull.size(); i++) {
                while ((ull.get(i).x == x.x) && (ull.get(i).y == x.y)) {
                    countUnits++;
                    break;
                }
            }
            return countUnits;
        }

        private void cursorMovements(KeyEvent e,
                                     Location previous,
                                     Board board) {
            switch (e.getKeyCode()) {
                case 39:
                    moveCursor(previous, DirectionType.SOUTH, board);
                    break;
                case 37:
                    moveCursor(previous, DirectionType.NORTH, board);
                    break;
                case 40:
                    moveCursor(previous, DirectionType.WEST, board);
                    break;
                case 38:
                    moveCursor(previous, DirectionType.EAST, board);
                    break;
                case KeyEvent.VK_SPACE:
                    //TODO:activate unit on cursorLocation for movement
                default:

            }
        }

        private void moveCursor(Location previous,
                                DirectionType direction,
                                Board board) {
            UpdateUnitBoard uub = new UpdateUnitBoard(board);
            Location aftermove = previous.movement(direction);
            temporaryContentsUnit = visibleGrid[previous.x][previous.y];
            replaceLabelThenMap(aftermove, previous);
            setCursorLocation(aftermove);
            checkForUnit(aftermove);
            while (unitCountAt(aftermove) > 0) {
                LinkedList<Unit> lu = getUnitsAt(aftermove);
                active = lu.peekFirst();
                uub.update(lu);
                break;
            }
            while (unitCountAt(aftermove) == 0) {
                uub.updateUnitBoardWithEmpty();
                break;
            }
            placeUnits();
        }

        private void checkForUnit(Location l) {
            boolean presentUnit = isLandUnitDisplayed(visibleGrid[l.x][l.y]);
            while (presentUnit) {
                getResponse(l, board);
                break;
            }
        }

        private boolean isLandUnitDisplayed(JLabel jLabel) {
            boolean unitPresent = jLabel.getText().equals(
                    String.valueOf(Data.landChit));
            while (!unitPresent) {
                emptyUnitBoard(board);
                break;
            }
            return unitPresent;
        }

        private boolean getResponse(Location l, Board board) {
            UpdateUnitBoard uub = new UpdateUnitBoard(board);
            LinkedList<Unit> unitsAtLocation = getUnitsAt(l);
            boolean updated = isLandUnitDisplayed(visibleGrid[l.x][l.y]) ?
                    uub.update(unitsAtLocation) :
                    emptyUnitBoard(board);
            active = unitsAtLocation.peekFirst();
            uub.updateUnitBoardWithActiveUnit();
            return updated;

        }

        public boolean emptyUnitBoard(Board board) {
            board.unit.removeAll();
            return false;
        }

        private void replaceLabelThenMap(Location aftermove,
                                         Location previous) {
            new Replacement().returnToBefore(
                    temporaryContentsCursor,
                    previous);
            String letter = temporaryContentsCursor.getText();
            cursor = new Placement().placementOnPanelAt(letter, aftermove);
            new WorldMapFunctionality().replaceWorldMap();
        }

        private void replaceUnitThenMap(Location aftermove,
                                        Location previous) {
            new Replacement().returnToBefore(
                    temporaryContentsCursor,
                    previous);
            String letter = Character.toString(Data.landChit);
            activeLabel = new Placement().placementOnPanelAt(letter,
                    aftermove);
            new WorldMapFunctionality().replaceWorldMap();
        }

        private class StayAtSpot extends MoveInDirection {
            @Override
            void move(Location previous) {

            }
        }
    }


    private LinkedList<Unit> getUnitsAt(Location l) {
        LinkedList<Unit> unitsAtLocation = new LinkedList<Unit>();

        for (Player p : Data.listOfPlayers) {
            while (!p.units.list.isEmpty()) {
                for (Unit u : p.units.list) {
                    try {
                        while (u.location != null) {
                            while (u.location.x == l.x &&
                                    u.location.y == l.y) {
                                unitsAtLocation.add(u);
                                break;
                            }
                            break;
                        }
                    } catch (NullPointerException npe) {
                        System.err.print(npe);
                    }
                }
                break;
            }
        }
        return unitsAtLocation;
    }

    private void setActiveUnitsLocation(Location aftermove) {
        while (active != null) {
            active.location = aftermove;
            break;
        }
    }

    private void placeUnit(Unit unit) {
        Location location = unit.location;
        switch (unit.chit) {
            case Data.landChit:
                visibleGrid[location.x][location.y].setText(
                        Character.toString(unit.chit));
                visibleGrid[location.x][location.y].setForeground(
                        active.colors);
                break;
            case Data.airChit:
                visibleGrid[location.x][location.y].setText(
                        Character.toString(unit.chit));
                visibleGrid[location.x][location.y].setForeground(
                        active.colors);
                break;
            case Data.seaChit:
                visibleGrid[location.x][location.y].setText(
                        Character.toString(unit.chit));
                visibleGrid[location.x][location.y].setForeground(
                        active.colors);
                break;
            default:

        }
    }

    void setCursorLocation(Location aftermove) {
        this.cursorLocation = aftermove;
    }


    public class UpdateUnitBoard {
        Board board;
        public UpdateUnitBoard(Board board){
            this.board = board;
        }
        public boolean update(LinkedList<Unit> unitsAtLocation) {
            board.unit.removeAll();
            board.createUnitBoard(View.this);
            try {
                while (!unitsAtLocation.isEmpty()) {
                    Unit current = unitsAtLocation.getFirst();
                    unitType.setText(current.getType() +
                            current.identification.fullName +
                            current.identification.id
                    );
                    board.unit.add(unitType);
                    String veteranText = current.isVeteran() ?
                            "Veteran" :
                            "Rookie";
                    veteran.setText(veteranText);
                    break;
                }
            } catch (NullPointerException npe) {
                System.err.println("hey no units at this location" + npe);
            }
            board.unit.add(veteran);
            return !unitsAtLocation.isEmpty();
        }

        public void updateUnitBoardWithActiveUnit() {
            board.unit.removeAll();
            board.createUnitBoard(View.this);
            while (active != null) {
                unitType.setText(active.getType() +
                        active.identification.fullName +
                        currentUnitIndex
                );
                board.unit.add(unitType);
                String veteranText = active.isVeteran() ? "Veteran" : "Rookie";
                veteran.setText(veteranText);
                board.unit.add(veteran);
                break;
            }
        }

        public void updateUnitBoardWithCurrentPlayerUnit() {
            board.unit.removeAll();
            board.createDataBoard();
            Player player = Data.Turn.currentPlayer;
            LinkedList<Unit> playerForces = new LinkedList<Unit>();
            playerForces.addAll(player.units.getList());
            Unit current = playerForces.get(currentUnitIndex);
            unitType.setText(current.getType() +
                    player.identification.fullName +
                    currentUnitIndex
            );
            board.unit.add(unitType);
            String veteranText = current.isVeteran() ? "Veteran" : "Rookie";
            veteran.setText(veteranText);
            board.unit.add(veteran);

        }

        public void updateUnitBoardWithEmpty() {
            board.unit.removeAll();
            board.createUnitBoard(View.this);
        }


    }
    private class Preparations {
        public void invoke() {
            setupView();
            placeMenuBar();
            init.initCellGridPanels();
            initLabels();
            makeGridLabels();
        }

        private void setupView() {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(820, 640));
            setLayout(new BorderLayout());
            setFocusable(true);
            MyKeyListener mkl = new MyKeyListener();
            addKeyListener(mkl);

        }

        private void placeMenuBar() {
            JMenuBar menuBar = new JMenuBar();

            JMenu file = new JMenu("File");
            JMenuItem open = new JMenuItem("Open");
            JMenuItem close = new JMenuItem("Close");
            JMenuItem save = new JMenuItem("Save");
            JMenuItem exit = new JMenuItem("Exit");

            file.add(open);
            file.add(close);
            file.add(save);
            file.addSeparator();
            file.add(exit);
            menuBar.add(file);

            //orders, ministrydata, ministry orders?, advances
            JMenu orders = new JMenu("Orders");
            JMenuItem operate = new JMenuItem("Operation");
            JMenuItem heal = new JMenuItem("Heal");
            JMenuItem vigil = new JMenuItem("Vigil");
            JMenuItem fortify = new JMenuItem("Fortify");

            orders.add(operate);
            orders.add(heal);
            orders.add(vigil);
            orders.add(fortify);
            menuBar.add(orders);

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
            menuBar.add(jmMinistries);

            JMenu data = new JMenu("Data");
            JMenuItem wonders = new JMenuItem("Wonders of the World");
            JMenuItem topCities = new JMenuItem("Top Cities");
            JMenuItem demographics = new JMenuItem("Demographics");
            JMenuItem progress = new JMenuItem("Progress chart");
            JMenuItem international = new JMenuItem("Affairs");

            data.add(wonders);
            data.add(topCities);
            data.add(demographics);
            data.add(progress);
            data.add(international);
            menuBar.add(data);

            JMenu jmHelp = new JMenu("Help");
            JMenuItem jmiAbout = new JMenuItem("About");
            jmHelp.add(jmiAbout);
            menuBar.add(jmHelp);

            open.addActionListener(View.this);
            close.addActionListener(View.this);
            save.addActionListener(View.this);

            operate.addActionListener(View.this);
            heal.addActionListener(View.this);
            vigil.addActionListener(View.this);
            fortify.addActionListener(View.this);

            jmiFinancial.addActionListener(View.this);
            jmiSocial.addActionListener(View.this);
            jmiInterior.addActionListener(View.this);
            jmiForeignAffairs.addActionListener(View.this);
            jmiDefence.addActionListener(View.this);

            wonders.addActionListener(View.this);
            topCities.addActionListener(View.this);
            demographics.addActionListener(View.this);
            progress.addActionListener(View.this);
            international.addActionListener(View.this);

            exit.addActionListener(View.this);
            jmiAbout.addActionListener(View.this);
            View.this.setJMenuBar(menuBar);
        }

        private void initLabels() {
            init.initGridLabels();
            init.initVisibleGridLabels();
        }

        private void makeGridLabels() {
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    gridLabels[x][y] = cell.getCellLabelsFromWorldMapOriginal()[x][y];
                }
            }
        }
    }

    public class ViewContents {
        public class Definitions {
            Definitions(View view, Board board) {
                worldMapPanelContents.createWorld();
                createControlPanel(view, board);
                createTextPanel();
                createMapPanel();
                createWorldMap();
                createGlobeMap();
            }

            Definitions() {
            }

            private void createControlPanel(View view, Board board) {
                control.setLayout(new GridLayout(5, 1));
                createGlobeMap();
                control.add(globeMapPanel);
                board.createDataBoard();
                control.add(board.data);
                board.createUnitBoard(view);
                control.add(board.unit);
                PlayerSwitchListener psl = new PlayerSwitchListener(view);
                switchPlayer.addActionListener(psl);
                EndListener endListener = new EndListener(view);
                endTurn.addActionListener(endListener);
                control.add(endTurn);
                control.add(switchPlayer);
                control.repaint();
            }

            private void createTextPanel() {
                text.add(textArea);
            }

            private void createMapPanel() {
                worldMapPanel.setPreferredSize(
                        new Dimension(
                                cols * cellSize + 10,
                                rows * cellSize + 10));
                worldMapPanel.setVisible(true);
            }

            private void createWorldMap() {
                worldMapPanel.setLayout(new GridLayout(rows, cols));
                worldMapPanel.setSize(cols * 15, rows * 15);
                worldMapPanel.setVisible(true);
                worldMapPanel.repaint();
            }

            private JPanel createGlobeMap() {
                globeMapPanel.setBackground(Color.black);
                globeMapPanel.setLayout(new GridLayout(rows, cols));
                globeMapPanel.setSize(cols * 4, rows * 4);
                globeMapPanel.setVisible(true);
                globeMapPanel.repaint();
                return globeMapPanel;
            }
        }

        public class Maker {
            public void invoke() {
                createContentDefinitions();
                setVisibleGridFromGridLabels();
                new CellGrid().fillCellGrid();
                cell.placeVisibleLabels();
                cell.createCells();
                setAllUnitLocationsAtStart();
            }

            public void setVisibleGridFromGridLabels() {
                for (int x = 0; x < rows; x++) {
                    for (int y = 0; y < cols; y++) {
                        visibleGrid[x][y] = gridLabels[x][y];
                        visibleGrid[x][y].setVisible(true);
                        visibleGrid[x][y].setPreferredSize(cellDimension);
                    }
                }
            }
        }

        public class Placer {
            public void invoke() {
                cursor = new Placement().placementOnPanelAt(
                        temporaryContentsCursor.getText(),
                        Data.CENTRE);
                placeContentToView();
                new CellGrid().fillCellGrid();
            }




            private void placeContentToView() {
                textArea.setEnabled(false);
                View.this.add(text, BorderLayout.SOUTH);
                View.this.add(control, BorderLayout.EAST);
                View.this.add(worldMapPanel, BorderLayout.CENTER);
            }
        }
    }

    public static class Board {
        public View view;

        public Board(View view) {
            this.view = view;
        }

        public Board getBoard(){
            return this;
        }
        public  JPanel data = new JPanel();
        public JPanel unit = new JPanel();
        public  JPanel terrain = new JPanel();

        private void createDataBoard() {
            data.setLayout(new GridLayout(4, 1));
            view.funds = new JLabel("Funds: " +
                    Data.Turn.currentPlayer.funds);
            view.pollution = new JLabel("Pollution: " +
                    Data.Turn.currentPlayer.pollution);
            view.tax = new JLabel("Taxrate: " +
                    Data.Turn.currentPlayer.tax);
            view.year = new JLabel("Year: " +
                    Civilization.year);
            data.add(view.year);
            data.add(view.tax);
            data.add(view.funds);
            data.add(view.pollution);
        }

        private void createUnitBoard(View view) {
            unit.setLayout(new GridLayout(4, 1));
            view.nextUnit = new JButton("Next Unit");
            UnitSwitchListener unitSwitchListener =
                    new UnitSwitchListener(view);
            view.nextUnit.addActionListener(unitSwitchListener);
            view.unitType = new JLabel("Unit ID : ");
            view.veteran = new JLabel("Experience:");
            view.nextUnit.setFocusPainted(false);
            unit.add(view.nextUnit);
            unit.add(view.unitType);
            unit.add(view.veteran);


        }

        private void createTerrainBoard() {
            terrain.setLayout(new GridLayout(4, 1));
            view.terrainType = new JLabel("Terrain:");
            view.structure = new JLabel("Infrastructure:");//city road, canal
            // mine
            // bridge
            view.artifacts = new JLabel("Artifacts:");//pollution, resource,
            // village
            terrain.add(view.terrainType);
            terrain.add(view.structure);
            terrain.add(view.artifacts);
        }
    }

    class UnitPlacer {
        public View view;

        public UnitPlacer(View view) {
            this.view = view;
        }

        public void invoke() {
            placeLandUnits();
            //placeSeaUnits();
            //placeAirUnits();
        }

        private void placeLandUnits() {
            char sign = Data.landChit;
            paintUnitSign(view, sign);
        }

        private void placeSeaUnits() {
            char sign = Data.seaChit;
            paintUnitSign(view, sign);
        }

        private void placeAirUnits() {
            char sign = Data.airChit;
            paintUnitSign(view, sign);
        }

        private void paintUnitSign(View view, char sign) {
            for (Player p : Data.listOfPlayers) {
                for (Unit u : p.units.list) {
                    Location l = u.location;
                    String signStr = Character.toString(sign);
                    view.visibleGrid[l.x][l.y]
                            .setText(signStr);
                    view.visibleGrid[l.x][l.y].setForeground(p.colors);
                }
            }
        }
    }

}

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

    Location cursorLocation = Data.CENTRE;

    JTextArea textArea = new JTextArea(5, 52);
    int x;
    int y;
    JPanel text = new JPanel();
    public JPanel control = new JPanel();
    public JPanel globeMapPanel = new JPanel();
    public JPanel dataBoard = new JPanel();
    public JPanel unitBoard = new JPanel();
    public JPanel worldMapPanel = new JPanel();



    public static final int cellSize = 15;
    public static WorldMap worldMapPanelContents;
    public JLabel cursor;
    private static JLabel label;
    private final JLabel[][] gridLabels = new JLabel[rows][cols];
    private JLabel[][] visibleGrid = new JLabel[rows][cols];
    public JPanel[][] cellPanelGrid = new JPanel[rows][cols];
    private JLabel temporaryContentsCursor;
    private Dimension cellDimension = new Dimension(cellSize, cellSize);
    public static int currentUnitIndex = 0;
    public JButton endTurn = new JButton("End this Turn.");
    public JButton switchPlayer = new JButton("Next player");
    public JButton nextUnit;


    public JLabel funds, pollution, tax, year;//for databoard
    private JLabel unitType, veteran; //for unit

    public Unit active;
    private JLabel activeLabel;
    private JLabel temporaryContentsUnit;

    public View(){}

    public View(WorldMap wm) {
        worldMapPanelContents = wm;
        //preparations
        setupView();
        placeMenuBar();
        initCellGridPanels();
        initLabels();
        makeGridLabels();

        //makeContents
        createContentDefinitions();
        getVisibleGridFromGridLabels();
        fillCellGrid();
        placeVisibleLabels();
        createCells();
        setAllUnitLocationsAtStart();

        //placeAllContent
        placeCursorOnPanelAt(Data.CENTRE);
        placeContentToView();
        fillCellGrid();

        placeUnits();
        active = Data.listOfPlayers.getFirst().units.list.getFirst();
        replaceWorldMap();
        worldMapPanel.requestFocus();

        setVisible(true);
        pack();
        repaint();
    }

    public void setAllUnitLocationsAtStart() {

    }

    public void placeUnits() {
        placeLandUnits();
        //placeSeaUnits();
        //placeAirUnits();
        //TODO:placeSatellites();
    }

    private void placeAirUnits() {
        char sign = Data.airChit;
        paintUnitSign(sign);
    }

    private void placeSeaUnits() {
        char sign = Data.seaChit;
        paintUnitSign(sign);
    }

    private void placeLandUnits() {
        char sign = Data.landChit;
        paintUnitSign(sign);
    }

    private void paintUnitSign(char sign) {
        for(Player p : Data.listOfPlayers){
            for(Unit u: p.units.list) {
                Location l = u.location;
                String signStr = Character.toString(sign);
                visibleGrid[l.x][l.y].setText(signStr);
                visibleGrid[l.x][l.y].setForeground(p.colors);
            }
        }
    }

    public void updateUnitsOnMap(){
        //TODO going to moveCursor some units around!!!
    }

    private void setupView() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(820, 640));
        setLayout(new BorderLayout());
        setFocusable(true);
        MyKeyListener mkl = new MyKeyListener();
        addKeyListener(mkl);

    }

    private void makeGridLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                gridLabels[x][y] = getCellLabelsFromWorldMapOriginal()[x][y];
            }
        }
    }

    public void getVisibleGridFromGridLabels() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                visibleGrid[x][y] = gridLabels[x][y];
                visibleGrid[x][y].setVisible(true);
                visibleGrid[x][y].setPreferredSize(cellDimension);
            }
        }
    }

    private void initCellGridPanels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                cellPanelGrid[x][y] = new JPanel();
            }
        }
    }

    private void initLabels(){
        initGridLabels();
        initVisibleGridLabels();
    }

    private void initGridLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                gridLabels[x][y] = new JLabel();
            }
        }
    }
    private void initVisibleGridLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                visibleGrid[x][y] = new JLabel();
            }
        }
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
        JMenuItem operate = new JMenuItem("Operate");
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

        open.addActionListener(this);
        close.addActionListener(this);
        save.addActionListener(this);

        operate.addActionListener(this);
        heal.addActionListener(this);
        vigil.addActionListener(this);
        fortify.addActionListener(this);

        jmiFinancial.addActionListener(this);
        jmiSocial.addActionListener(this);
        jmiInterior.addActionListener(this);
        jmiForeignAffairs.addActionListener(this);
        jmiDefence.addActionListener(this);

        wonders.addActionListener(this);
        topCities.addActionListener(this);
        demographics.addActionListener(this);
        progress.addActionListener(this);
        international.addActionListener(this);

        exit.addActionListener(this);
        jmiAbout.addActionListener(this);
        this.setJMenuBar(menuBar);
    }

    private void placeContentToView() {
        textArea.setEnabled(false);
        this.add(text, BorderLayout.SOUTH);
        this.add(control, BorderLayout.EAST);
        this.add(worldMapPanel, BorderLayout.CENTER);
    }

    private void createContentDefinitions() {
        worldMapPanelContents.createWorld();
        createControlPanel();
        createTextPanel();
        createMapPanel();
        createWorldMap();
        createGlobeMap();
    }

    protected JPanel createWorldMap() {
        worldMapPanel.setLayout(new GridLayout(rows, cols));
        worldMapPanel.setSize(cols *15, rows *15);
        worldMapPanel.setVisible(true);
        worldMapPanel.repaint();
        return worldMapPanel;
    }

    private JPanel createGlobeMap() {
        globeMapPanel.setBackground(Color.black);
        globeMapPanel.setLayout(new GridLayout(rows, cols));
        globeMapPanel.setSize(cols*4, rows*4);
        globeMapPanel.setVisible(true);
        globeMapPanel.repaint();
        return globeMapPanel;
    }

    private void createTextPanel() {
        text.add(textArea);
    }

    private void createControlPanel() {
        control.setLayout(new GridLayout(5, 1));
        createGlobeMap();
        control.add(globeMapPanel);
        createDataBoard();
        control.add(dataBoard);
        createUnitBoard();
        control.add(unitBoard);
        PlayerSwitchListener psl = new PlayerSwitchListener(this);
        switchPlayer.addActionListener(psl);
        EndListener endListener = new EndListener(this);
        endTurn.addActionListener(endListener);
        control.add(endTurn);
        control.add(switchPlayer);
        control.repaint();
    }

    private void createUnitBoard() {
        unitBoard.setLayout(new GridLayout(4,1));
        nextUnit = new JButton("Next Unit");
        UnitSwitchListener unitSwitchListener =
                new UnitSwitchListener(this);
        nextUnit.addActionListener(unitSwitchListener);
        unitType = new JLabel("Unit ID : ");
        veteran = new JLabel("Experience:");
        nextUnit.setFocusPainted(false);
        unitBoard.add(nextUnit);
        unitBoard.add(unitType);
        unitBoard.add(veteran);


    }

    public void updateUnitBoardWithEmpty(){
        unitBoard.removeAll();
        createUnitBoard();
    }

    public void updateUnitBoardWithCurrentPlayerUnit(){
        unitBoard.removeAll();
        createUnitBoard();
        Player player = Data.Turn.currentPlayer;
        LinkedList<Unit> playerForces = new LinkedList<Unit>();
        playerForces.addAll(player.units.getList());
        Unit current = playerForces.get(currentUnitIndex);
        unitType.setText( current.getType() +
                player.identification.fullName +
                currentUnitIndex
        );
        unitBoard.add(unitType);
        String veteranText = current.isVeteran() ? "Veteran":"Rookie";
        veteran.setText(veteranText);
        unitBoard.add(veteran);

    }
    public void  updateUnitBoardWithActiveUnit(){
        unitBoard.removeAll();
        createUnitBoard();
        while(active!=null) {
            unitType.setText(active.getType() +
                    active.identification.fullName +
                    currentUnitIndex
            );
            unitBoard.add(unitType);
            String veteranText = active.isVeteran() ? "Veteran" : "Rookie";
            veteran.setText(veteranText);
            unitBoard.add(veteran);
            break;
        }
    }

    private void createDataBoard() {
        dataBoard.setLayout(new GridLayout(4,1));
        funds = new JLabel("Funds: " +
                Data.Turn.currentPlayer.funds);
        pollution = new JLabel("Pollution: " +
                Data.Turn.currentPlayer.pollution);
        tax = new JLabel("Taxrate: " +
                Data.Turn.currentPlayer.tax);
        year = new JLabel("Year: " +
                Civilization.year);
        dataBoard.add(year);
        dataBoard.add(tax);
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
                //save game
                System.exit(0);
            default:
                textArea.setText(command);
        }
    }


    protected void placeCursorOnPanelAt(Location location) {
        cursorLocation = location;
        placeCursorTo(location);
        replaceVisible(location, cursor);
        fillCellGrid();
        showMap();
    }
    protected void placeUnitOnPanelAt(Location location) {
        cursorLocation = location;
        placeUnitTo(location);
        replaceVisible(location, activeLabel);
        fillCellGrid();
        showMap();
    }

    private void placeCursorTo(Location l) {
        JLabel c = new JLabel();
        temporaryContentsCursor = gridLabels[l.x][l.y];
        c.setOpaque(true);
        c.setText(temporaryContentsCursor.getText());
        c.setForeground(Color.red);
        c.setBackground(Color.white);
        c.setPreferredSize(temporaryContentsCursor.getPreferredSize());
        c.setVisible(true);
        c.setFocusable(true);
        c.requestFocus();
        cursor = c;
        visibleGrid[l.x][l.y] = c;
    }
    private void placeUnitTo(Location l) {
        JLabel c = new JLabel();
        temporaryContentsUnit = gridLabels[l.x][l.y];
        c.setOpaque(true);
        c.setText(Character.toString(Data.landChit));
        c.setForeground(Color.red);
        c.setBackground(Color.white);
        c.setPreferredSize(temporaryContentsUnit.getPreferredSize());
        c.setVisible(true);
        c.setFocusable(true);
        c.requestFocus();
        activeLabel = c;
        visibleGrid[l.x][l.y] = c;
    }

    public void placeOldLabelBackTo(Location previous) {
        JLabel c = getPreviousLabel(temporaryContentsCursor);
        replaceVisible(previous, c);
        fillCellGrid();
        showMap();
    }

    public void placeUnitOldLabelBackTo(Location previous) {
        JLabel c = getPreviousLabel(temporaryContentsUnit);
        replaceVisible(previous, c);
        fillCellGrid();
        showMap();
    }

    private JLabel getPreviousLabel(JLabel temporary) {
        JLabel c = new JLabel();
        c.setOpaque(true);
        c.setText(temporary.getText());
        c.setForeground(temporary.getForeground());
        c.setBackground(temporary.getBackground());
        c.setPreferredSize(temporary.getPreferredSize());
        c.setVisible(true);
        return c;
    }

    public void fillCellGrid() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                fillCell(x, y);
                showMap();
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
        cellPanelGrid[x][y].setLayout(new GridLayout(1,1));
        cellPanelGrid[x][y].setSize(cellDimension);
    }

    private void setVisible(JPanel jPanel) {
        jPanel.setVisible(true);
        showMap();
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

    public void emptyMap(){
        initVisibleGridLabels();
        initCellGridPanels();
        replaceWorldMap();
    }

    public void newMapWithUnits(){
        getVisibleGridFromGridLabels();
        clearMapToNature(); //restart gridlabels to visiblelabels
        placeUnits();
        placeVisibleLabels();
    }

    public void showControl(){
        dataBoard.revalidate();
        dataBoard.repaint();
        control.revalidate();
        control.repaint();
    }

    protected void placeVisibleLabels(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                JLabel cellLabel = visibleGrid[x][y];
                placeLabelOnMap(cellLabel, new Location(x,y));
            }
        }
    }


    protected void clearMapToNature(){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                JLabel cellLabel = gridLabels[x][y];
                placeLabelOnMap(cellLabel, new Location(x,y));
            }
        }
    }

    private void placeLabelOnMap(JLabel c, Location location) {
        c.setSize(cellDimension);
        JPanel cell = cellPanelGrid[location.x][location.y];
        cell.setLayout(new GridLayout(1,1));
        cell.setSize(cellDimension);
        cell.add(c);
        cell.setVisible(true);
        replaceLabelOnCell(cell, c);
        worldMapPanel.add(cell,location.y,location.x);
        worldMapPanel.setVisible(true);
        showMap();
    }

    private void replaceLabelOnCell(JPanel jPanel, JLabel c) {
        jPanel.remove(0);
        jPanel.add(c);
    }

    private JLabel[][] getCellLabelsFromWorldMapOriginal() {
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
                cellPanelGrid[x][y].getComponents().length);
        final int SINGLE_COMPONENT = 1;
        assert cellPanelGrid[x][y].getComponents().length <= SINGLE_COMPONENT;
        String exceptionMessage;
        exceptionMessage = "Exception!";
        exceptionMessage =
                cellPanelGrid[x][y].getComponents().length <= SINGLE_COMPONENT ?
                        "":
                        exceptionMessage;
        System.out.print(exceptionMessage);
    }

    private JPanel getCell(int x, int y) {
        JPanel p;
        p = cellPanelGrid[x][y];
        p.setLayout(new GridLayout(1,1));
        p.add(visibleGrid[x][y]);
        return p;
    }

    protected void addCellToWorldMap(JPanel p, Location loc) {
        worldMapPanel.add(p,loc.x,loc.y);
        showMap();
    }

    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
        final int UPDATE = 1;
        final int NONE = 0;
        @Override
        public void keyPressed(KeyEvent e) {
            Location previous;
            System.out.println("Keypressed" +
                    Data.numberOfArrowKeyPresses++ +
                    " : " +
                    e.getKeyCode());
            try {
                previous = cursorLocation;
                cursorMovements(e, previous);
                previous = cursorLocation;
                unitMovements(e,previous);
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println(aiobe + "movement went out");
            }
        }

        private void unitMovements(KeyEvent e, Location previous) {
            MoveUnit mu = active.chit==Data.landChit ? new MoveLandUnit() :
                    active.chit==Data.seaChit ? new MoveSeaUnit() :
                    active.chit == Data.airChit? new MoveAirUnit():
                    active.chit == Data.rocketChit? new MoveToOrbit():
                            new StayPut();
            switch (e.getKeyChar()) {
                case 'd':
                    mu.move(previous, DirectionType.SOUTH);
                    break;
                case 'a':
                    mu.move(previous, DirectionType.NORTH);
                    break;
                case 's':
                    mu.move(previous, DirectionType.WEST);
                    break;
                case 'w':
                    mu.move(previous, DirectionType.EAST);
                    break;
                case 'n':
                    nextUnit.doClick();
                    break;
                default:

            }
        }

        abstract class MoveUnit{
            abstract void move(Location previous, DirectionType direction);
        }
        class StayPut extends MoveUnit{
            void move(Location previous, DirectionType direction){}
        }
        class MoveLandUnit extends MoveUnit
        {

            void move(Location previous, DirectionType direction){
                Location aftermove = getLocationAfterMove(previous, direction);
                while(!visibleGrid[aftermove.x][aftermove.y].getBackground()
                        .equals(Color.cyan) ){
                    moveUnit(previous, direction);
                    break;
                }
            }
        }

        class MoveSeaUnit extends MoveUnit
        {
            void move(Location previous, DirectionType direction){
                Location aftermove = getLocationAfterMove(previous, direction);
                while(visibleGrid[aftermove.x][aftermove.y].getBackground()
                        .equals(Color.cyan)){
                    moveUnit(previous, direction);
                    break;
                }
            }
        }

        class MoveAirUnit extends MoveUnit
        {
            void move(Location previous, DirectionType direction){
                moveUnit(previous,direction);
            }
        }
        class MoveToOrbit extends MoveUnit{
            void move(Location previous, DirectionType direction){
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
            moveCursor(previous,direction);
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

        private void placeRemainingUnitsBack(Location previous) {
            LinkedList<Unit> ulAtPrevious = new LinkedList<Unit>();
            ulAtPrevious =getUnitsAt(previous);
            while(ulAtPrevious.size() > 0){
                placeUnitOldLabelBackTo(previous);
                for(Unit u: ulAtPrevious) placeUnit(u);
                break;
            }
        }

        private void placeActiveUnitOnPanelAt(Location location) {
            setActiveUnitsLocation(location);
            while(active != null) {
                placeUnit(active);
            }
            JLabel activeunit = makeUnitLabel(location);
            replaceVisible(location, activeunit);
            fillCellGrid();
            showMap();
        }

        private JLabel makeUnitLabel(Location location) {
            JLabel c = new JLabel();
            temporaryContentsUnit = gridLabels[location.x][location.y];
            c.setOpaque(true);
            c.setText(temporaryContentsUnit.getText());
            c.setForeground(active.colors);
            c.setPreferredSize(temporaryContentsUnit.getPreferredSize());
            c.setVisible(true);
            c.setFocusable(true);
            c.requestFocus();
            activeLabel = c;
            visibleGrid[location.x][location.y] = c;
            return c;
        }

        

        public LinkedList<Location> createAllUnitsLocations(){
            LinkedList<Location> ull = new LinkedList<Location>();
            for(Player p : Data.listOfPlayers){
                for(Unit unit: p.units.list){
                    ull.add(unit.location);
                }
            }
            return ull;
        }

        public int unitCountAt(Location x){
            LinkedList<Location> ull = createAllUnitsLocations();
            int countUnits = 0;
            for (int i = 0; i < ull.size(); i++) {
                while((ull.get(i).x == x.x) && (ull.get(i).y == x.y)) {
                    countUnits++;
                    break;
                }
            }
            return countUnits;
        }

        private void cursorMovements(KeyEvent e, Location previous) {
            switch (e.getKeyCode()) {
                case 39:
                    moveCursor(previous, DirectionType.SOUTH);
                    break;
                case 37:
                    moveCursor(previous, DirectionType.NORTH);
                    break;
                case 40:
                    moveCursor(previous, DirectionType.WEST);
                    break;
                case 38:
                    moveCursor(previous, DirectionType.EAST);
                    break;
                case KeyEvent.VK_SPACE:
                    //activate unit on cursorLocation for movement
                default:

            }
        }

        private void moveCursor(Location previous, DirectionType direction) {
            Location aftermove = previous.movement(direction);
            temporaryContentsUnit = visibleGrid[previous.x][previous.y];
            replaceLabelThenMap(aftermove, previous);
            setCursorLocation(aftermove);
            checkForUnit(aftermove);
            while(unitCountAt(aftermove)>0){
                LinkedList<Unit> lu = getUnitsAt(aftermove);
                active = lu.peekFirst();
                updateUnitBoardWith(lu);
                break;
            }
            while(unitCountAt(aftermove)==0){
                updateUnitBoardWithEmpty();
                break;
            }
            placeUnits();
        }

        private void checkForUnit(Location l) {
            boolean presentUnit = isLandUnitDisplayed(visibleGrid[l.x][l.y]);
            while(presentUnit) {
                getResponse(l);
                break;
            }
        }

        private boolean isLandUnitDisplayed(JLabel jLabel) {
            boolean unitPresent = jLabel.getText().equals(
                    String.valueOf(Data.landChit));
            while(!unitPresent) {
                emptyUnitBoard();
                break;
            }
            return unitPresent;
        }

        private boolean getResponse(Location l) {
            LinkedList<Unit> unitsAtLocation = getUnitsAt(l);
            boolean updated = isLandUnitDisplayed(visibleGrid[l.x][l.y]) ?
                    updateUnitBoardWith(unitsAtLocation):
                    emptyUnitBoard();
            active = unitsAtLocation.peekFirst();
            updateUnitBoardWithActiveUnit();
            return updated;

        }

        private void replaceLabelThenMap(Location aftermove,
                                         Location previous) {
            placeOldLabelBackTo(previous);
            placeCursorOnPanelAt(aftermove);
            replaceWorldMap();
        }

        private void replaceUnitThenMap(Location aftermove,
                                         Location previous) {
            placeOldLabelBackTo(previous);
            placeUnitOnPanelAt(aftermove);
            replaceWorldMap();
        }
    }

    public void removeOldUnitAt(Location previous) {
        JLabel[][] allLabels = getCellLabelsFromWorldMapOriginal();
        JLabel old = allLabels[previous.x][previous.y];
        replaceVisible(previous, old);
        placeLabelOnMap(old, previous);
        showMap();
    }

    private LinkedList<Unit> getUnitsAt(Location l) {
        LinkedList<Unit> unitsAtLocation = new LinkedList<Unit>();

        for(Player p: Data.listOfPlayers){
            while(!p.units.list.isEmpty()){
                for(Unit u:p.units.list){
                    try {
                        while(u.location != null) {
                            while (u.location.x == l.x &&
                                    u.location.y == l.y) {
                                unitsAtLocation.add(u);
                                break;
                            }
                            break;
                        }
                    }catch(NullPointerException npe){
                        System.err.print(npe);
                    }
                }
                break;
            }
        }
        return unitsAtLocation;
    }

    private void setActiveUnitsLocation(Location aftermove) {
        while(active!=null){
            active.location = aftermove;
            break;
        }
    }

    private void placeUnit(Unit unit) {
        Location location = unit.location;
        switch(unit.chit){
            case Data.landChit:
                visibleGrid[location.x][location.y].setText(
                        Character.toString(unit.chit));
                visibleGrid[location.x][location.y].setForeground(
                        active.colors);
                break;
            default:

        }
    }

    void setCursorLocation(Location aftermove){ this.cursorLocation = aftermove; }

    public boolean updateUnitBoardWith(LinkedList<Unit> unitsAtLocation) {
        unitBoard.removeAll();
        createUnitBoard();
        try {
            while(!unitsAtLocation.isEmpty()) {
                Unit current = unitsAtLocation.getFirst();
                unitType.setText(current.getType() +
                        current.identification.fullName +
                        current.identification.id
                );
                unitBoard.add(unitType);
                String veteranText = current.isVeteran() ?
                        "Veteran" :
                        "Rookie";
                veteran.setText(veteranText);
                break;
            }
        }catch(NullPointerException npe){
            System.err.println("hey" + npe);
        }
        unitBoard.add(veteran);
        return !unitsAtLocation.isEmpty();
    }

    public boolean emptyUnitBoard() {
        unitBoard.removeAll();
        return false;
    }

    public void replaceWorldMap() {
        worldMapPanel.removeAll();
        initCellGridPanels();
        fillCellGrid();
        //clearMapToNature();//either or prolly two functions needed
        placeVisibleLabels();
        worldMapPanel = createWorldMap();
        createCells();
        worldMapPanel.setVisible(true);
    }
    public void clearWorldMap() {
        worldMapPanel.removeAll();
        initCellGridPanels();
        fillCellGrid();
        clearMapToNature();
        worldMapPanel = createWorldMap();
        createCells();
        worldMapPanel.setVisible(true);
    }

    //tests
    private void testLocationPrint(Location aftermove, Location previous) {
        System.out.println("cursorLocation:"+previous.x +", "+
                previous.y +"::"+aftermove.x + ", " +
                aftermove.y);
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

}


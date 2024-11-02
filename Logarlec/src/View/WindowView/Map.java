package View.WindowView;

import Characters.Student;
import Controller.Notifiable;
import Game.Labyrinth;
import Rooms.IRoom;
import TestLogic.TestLogic;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The view of the labyrinth, where rooms are shown as nodes and their connections as arrows.
 */
public class Map extends JPanel implements IView {
    /**
     * The labyrinth instance associated with this map.
     */
    private Labyrinth labyrinth;
    /**
     * Flag to determine if baked-in data should be used.
     */

    private boolean useBakedInData = false; //------------------------------- ezt kell átállítani true-ra, hogy a beégetett pályát használja, false-ra, ha az egy sorral lejjebb lévő file-t töltse be ---------------
    /**
     * Filename of the map to load.
     */
    private String mapFilename = "demo_test";  //------------------------------- a betöltendő map file neve -------------------------------------------------------------------------------------------------------------
    /**
     * List of room node views.
     */
    List<RoomNodeView> nodes;
    /**
     * Border spacing to the left.
     */
    int borderToLeft = 150;
    /**
     * Border spacing to the right.
     */
    int borderToRight = 60;
    /**
     * Top border spacing.
     */
    int topBorder = 150;
    /**
     * Bottom border spacing.
     */
    int bottomBorder = 60;

    /**
     * Constructor for the Map class.
     *
     * @param controller The controller of the game.
     */
    public Map(Notifiable controller) {
        if(useBakedInData) {
            List<Student> students = new ArrayList<>();
            Student st = new Student();
            Student st2 = new Student();
            Student st3 = new Student();
            Student st4 = new Student();
            students.add(st);
            students.add(st2);
            students.add(st3);
            students.add(st4);
            labyrinth = new Labyrinth(students, this, controller);
        }
        else{
            loadMap(mapFilename, controller);
        }
        labyrinth.setControllerToItems();

        setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(topBorder, borderToLeft, bottomBorder, borderToRight));
        setLayout(null);

        //start labyrinth on thread
        Thread labyrinthThread = new Thread(() -> labyrinth.startGame());
        labyrinthThread.start();

    }

    /**
     * Paints the components of the map, including rooms and connections.
     *
     * @param g The graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nodes = new ArrayList<>();
        List<RoomNodeView> viewsToAdd = new ArrayList<>();
        for (IRoom room : labyrinth.getRooms()) {
            RoomNodeView roomNodeView = (RoomNodeView) MainWindow.viewsByObjects.get(room);
            if(roomNodeView == null){
                roomNodeView = (RoomNodeView) MainWindow.viewsByObjects.get(room.getDecoratedRoom());
            }

            if(roomNodeView != null){
                viewsToAdd.add(roomNodeView);
            }

        }
        // Add the views after the loop
        nodes.addAll(viewsToAdd);
        for(RoomNodeView roomNodeView : nodes){
            if(roomNodeView != null) {
                this.add(roomNodeView);
            }
        }


        // Add neighbors to nodes
        for (int i = 0; i < nodes.size(); i++) {
            RoomNodeView currentNode = nodes.get(i);
            if(currentNode != null){


            List<IRoom> neighbors = currentNode.getRoom().getNeighbours();
            for (IRoom neighbor : neighbors) {
                for(RoomNodeView neighborNode : nodes){
                    if(neighborNode != null && neighborNode.getRoom() == neighbor){
                        currentNode.getNeighbourRooms().add(neighborNode);
                        if(currentNode != neighborNode) {
                            drawArrowBetweenRooms(currentNode, neighborNode, g);
                        }
                    }
                }

            }
                // Draw line and arrow from currentNode to neighborNode

            }

        }
    }

    // Helper method to draw arrow between rooms
    private void drawArrowBetweenRooms(RoomNodeView from, RoomNodeView to, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Calculate cell size
        int cellWidth = (getWidth() - borderToLeft - borderToRight + 20) / 5; // Considering the gaps
        int cellHeight = (getHeight() - topBorder - bottomBorder + 20) / 5;

        // Calculate positions of the centers of the cells
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);

        //draw a circle on 0,0 to debug


        int fromCol = fromIndex % 5;
        int fromRow = fromIndex / 5;
        int toCol = toIndex % 5;
        int toRow = toIndex / 5;

        // Calculate coordinates of the center of the cells
//        int x1 = (fromCol * cellWidth) + borderToLeft + from.getWidth() + 30; // Adjusted for border
//        int y1 = (fromRow * cellHeight) + topBorder + from.getHeight() + 30; // Adjusted for border
//        int x2 = (toCol * cellWidth) + borderToLeft + from.getWidth() + 30; // Adjusted for border
//        int y2 = (toRow * cellHeight) + topBorder + from.getHeight() + 30; // Adjusted for border

        int x1 = from.getX() + 35;
        int y1 = from.getY() + 35;
        int x2 = to.getX() + 35;
        int y2 = to.getY() + 35;

        // Draw line
        g2d.drawLine(x1, y1, x2, y2);

        // Draw arrow
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy) - 40;
        g2d.translate(x1, y1);
        g2d.rotate(angle);
        g2d.fillPolygon(new int[]{70, 70-20, 70-20, 70}, new int[]{0, -20, 20, 0}, 4);
        g2d.rotate(-angle);
        g2d.translate(-x1, -y1);

    }

    /**
     * Updates the view of the map.
     */
    @Override
    public void update() {
        for (RoomNodeView node : nodes) {
            if(node != null) {
                node.repaint();
            }
        }
    }
    /**
     * Gets the panel component of the map.
     *
     * @return The JPanel component.
     */
    @Override
    public JPanel getPanel() {
        return null;
    }
    /**
     * Gets the label component of the map.
     *
     * @return The JLabel component.
     */
    @Override
    public JLabel getLabel() {
        return null;
    }

    /**
     * Loads the map from a file.
     *
     * @param fileName   The name of the file to load.
     * @param controller The controller of the game.
     */
    public void loadMap(String fileName,  Notifiable controller){
        if(fileName == null){
            fileName = "default.map";
        }
        TestLogic.setController(controller);
        labyrinth = TestLogic.getAndLoadLabyrinth(fileName);
        labyrinth.setMap(this);
        labyrinth.setController(controller);
    }
}

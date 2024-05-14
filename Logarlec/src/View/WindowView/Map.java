package View.WindowView;

import Characters.Student;
import Game.Labyrinth;
import Rooms.IRoom;
import TestLogic.TestLogic;
import View.CharacterView.StudentView;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends JPanel implements IView {
    private Labyrinth labyrinth;

    List<RoomNodeView> nodes;
int borderToLeft = 150;
int borderToRight = 60;
int topBorder = 150;
int bottomBorder = 60;


    public Map() {
        List<Student> students = new ArrayList<>();
        Student st = new Student();
        Student st2 = new Student();
        Student st3 = new Student();
        Student st4 = new Student();
        students = List.of(st, st2, st3, st4);
        labyrinth = new Labyrinth(students, this);
//        if(Labyrinth.currentPlayer == null) {
//            Labyrinth.currentPlayer = st;
//        }


        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(topBorder, borderToLeft, bottomBorder, borderToRight));
        setLayout(new GridLayout(5, 5, 20, 20));

        //start labyrinth on thread
        Thread labyrinthThread = new Thread(() -> labyrinth.startGame());
        labyrinthThread.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        nodes = new ArrayList<>();
        System.out.println("nUMBER OF ROOMS" + labyrinth.getRooms().size());
        for (IRoom room : labyrinth.getRooms()) {
            RoomNodeView roomNodeView = (RoomNodeView) MainWindow.viewsByObjects.get(room);
            nodes.add(roomNodeView);
            this.add(roomNodeView);
        }

        // Add neighbors to nodes
        for (int i = 0; i < nodes.size(); i++) {
            RoomNodeView currentNode = nodes.get(i);
            List<IRoom> neighbors = currentNode.getRoom().getNeighbours();
            for (IRoom neighbor : neighbors) {
                int neighborIndex = labyrinth.getRooms().indexOf(neighbor); //safety i guess
                for (int j = 0; j < nodes.size(); j++) {
                    if(nodes.get(j).getRoom().equals(neighbor)) {
                        neighborIndex = j;
                        break;
                    }
                }
                if(neighborIndex != -1) {
                    RoomNodeView neighborNode = nodes.get(neighborIndex);
                    currentNode.addNeighbourRoom(neighborNode);
                    drawArrowBetweenRooms(currentNode, neighborNode, g);
                }


                // Draw line and arrow from currentNode to neighborNode

            }
        }


        //drawArrowBetweenRooms(nodes.getFirst(), nodes.getFirst().getNeighbourRooms().get(1), g);
    }

    // Helper method to draw arrow between rooms
    private void drawArrowBetweenRooms(RoomNodeView from, RoomNodeView to, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Calculate cell size
        int cellWidth = (getWidth() - borderToLeft - borderToRight + 20) / 5; // Considering the gaps in the GridLayout
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
        int x1 = (fromCol * cellWidth) + borderToLeft + from.getWidth() + 30; // Adjusted for border
        int y1 = (fromRow * cellHeight) + topBorder + from.getHeight() + 30; // Adjusted for border
        int x2 = (toCol * cellWidth) + borderToLeft + from.getWidth() + 30; // Adjusted for border
        int y2 = (toRow * cellHeight) + topBorder + from.getHeight() + 30; // Adjusted for border

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


    @Override
    public void update() {
        for(IRoom room: labyrinth.getRooms()){
            MainWindow.viewsByObjects.get(room);
        }
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public JLabel getLabel() {
        return null;
    }

    public void loadMap(String fileName){
        if(fileName == null){
            fileName = "default.map";
        }
        labyrinth = TestLogic.getAndLoadLabyrinth(fileName);
    }
}

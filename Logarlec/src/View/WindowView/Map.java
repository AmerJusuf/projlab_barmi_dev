package View.WindowView;

import Characters.Student;
import Game.Labyrinth;
import Rooms.IRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Map extends JPanel {
    private Labyrinth labyrinth;

    List<RoomNodeView> nodes;
int borderToLeft = 150;
int borderToRight = 60;


    public Map() {
        List<Student> students = new ArrayList<>();
        Student st = new Student();
        Student st2 = new Student();
        Student st3 = new Student();
        Student st4 = new Student();
        students = List.of(st, st2, st3, st4);
        labyrinth = new Labyrinth(students);

        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(150, borderToLeft, 60, borderToRight));
        setLayout(new GridLayout(5, 5, 20, 20));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        nodes = new ArrayList<>();
        for (IRoom room : labyrinth.getRooms()) {
            RoomNodeView roomNodeView = new RoomNodeView(room);
            nodes.add(roomNodeView);
            this.add(roomNodeView);
        }

        // Add neighbors to nodes
        for (int i = 0; i < nodes.size(); i++) {
            RoomNodeView currentNode = nodes.get(i);
            List<IRoom> neighbors = labyrinth.getRooms().get(i).getNeighbours();
            for (IRoom neighbor : neighbors) {
                int neighborIndex = labyrinth.getRooms().indexOf(neighbor);
                RoomNodeView neighborNode = nodes.get(neighborIndex);
                currentNode.addNeighbourRoom(neighborNode);

                // Draw line and arrow from currentNode to neighborNode
               drawArrowBetweenRooms(currentNode, neighborNode, g);
            }
        }


        //drawArrowBetweenRooms(nodes.getFirst(), nodes.getFirst().getNeighbourRooms().get(1), g);
    }

    // Helper method to draw arrow between rooms
    private void drawArrowBetweenRooms(RoomNodeView from, RoomNodeView to, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Calculate cell size
        int cellWidth = (getWidth() - borderToLeft - borderToRight) / 5; // Considering the gaps in the GridLayout
        int cellHeight = (getHeight()) / 5;

        // Calculate positions of the centers of the cells
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);

        int fromCol = fromIndex % 5;
        int fromRow = fromIndex / 5;
        int toCol = toIndex % 5;
        int toRow = toIndex / 5;

        // Calculate coordinates of the center of the cells
        int x1 = (fromCol * cellWidth) + (from.getWidth() / 2) + borderToLeft + 20; // Adjusted for border
        int y1 = (fromRow * cellHeight) + (from.getHeight() / 2); // Adjusted for border
        int x2 = (toCol * cellWidth) + (to.getWidth() / 2) + borderToLeft + 20; // Adjusted for border
        int y2 = (toRow * cellHeight) + (to.getHeight() / 2); // Adjusted for border

        // Draw line
        g2d.drawLine(x1, y1, x2, y2);

        // Draw arrow
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        g2d.translate(x1, y1);
        g2d.rotate(angle);
        g2d.fillPolygon(new int[]{len, len-5, len-5, len}, new int[]{0, -5, 5, 0}, 4);
        g2d.rotate(-angle);
        g2d.translate(-x1, -y1);
    }



}

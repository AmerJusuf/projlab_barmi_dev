package View.WindowView;

import Rooms.IRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.AffineTransform;
public class RoomNodeView extends JPanel {

    private IRoom room;

    private List<RoomNodeView> neighbourRooms = new ArrayList<>();
    private Image circleImage;
    private boolean poisoned;
    private boolean cursed;
    private boolean sticky;

    public int width;
    public int height;

    public RoomNodeView(IRoom room) {
        this.room = room;
        this.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed
        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.poisoned = false;
        this.cursed = false;
        this.sticky = false;


        // Load circle image
        circleImage = new ImageIcon("Icons/basicroom.png").getImage();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle room click event
                // Example: show room information or perform some action
                System.out.println("Room clicked!");
            }
        });

        // Use BoxLayout to stack components vertically
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create labels for room types
        JLabel poisonedLabel = new JLabel(new ImageIcon("poisoned.jpg"));
        JLabel cursedLabel = new JLabel(new ImageIcon("cursed.jpg"));
        JLabel stickyLabel = new JLabel(new ImageIcon("sticky.jpg"));



        // Add room type labels to the panel
        add(Box.createVerticalGlue());
        add(poisonedLabel);
        add(cursedLabel);
        add(stickyLabel);
        add(Box.createVerticalGlue());
        this.setVisible(true);
    }
    
    public void addNeighbourRoom(RoomNodeView neighbourRoom) {
        neighbourRooms.add(neighbourRoom);
    }

    public List<RoomNodeView> getNeighbourRooms() {
        return neighbourRooms;
    }

    public void setRoomTypes(boolean poisoned, boolean cursed, boolean sticky) {
        this.poisoned = poisoned;
        this.cursed = cursed;
        this.sticky = sticky;
        repaint(); // Redraw panel with updated room types
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        width = getWidth()/3;
        height = getHeight()/3;
        // Draw circle image
        g.drawImage(circleImage, 0, 0, width,height, this);

        // Draw room types underneath the circle
        if (poisoned) {
            g.drawImage(new ImageIcon("poisoned.jpg").getImage(), 10, 80, this);
        }
        if (cursed) {
            g.drawImage(new ImageIcon("cursed.jpg").getImage(), 40, 80, this);
        }
        if (sticky) {
            g.drawImage(new ImageIcon("sticky.jpg").getImage(), 70, 80, this);
        }

    }


    public IRoom getRoom() {
        return room;
    }
}
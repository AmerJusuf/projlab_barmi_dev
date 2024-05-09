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
        this.poisoned = true;
        this.cursed = true;
        this.sticky = true;


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
        JLabel poisonedLabel = new JLabel(new ImageIcon("Icons/poisoned.jpg"));
        JLabel cursedLabel = new JLabel(new ImageIcon("Icons/cursed.jpg"));
        JLabel stickyLabel = new JLabel(new ImageIcon("Icons/sticky.jpg"));



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
        width = getWidth() / 3;
        height = getHeight() / 3;

        // Calculate maximum size
        int maxSize = 100;

        // Draw circle image
        int imageWidth = Math.min(maxSize, width);
        int imageHeight = Math.min(maxSize, height);
        g.drawImage(circleImage, 0, 0, imageWidth, imageHeight, this);

        // Draw room types underneath the circle
        int iconWidth = Math.min(maxSize, (width - 10) / 3) + 30; // Adjust position
        int iconHeight = Math.min(maxSize, (height - 80)) + 30; // Adjust position
        if (poisoned) {
            g.drawImage(new ImageIcon("Icons/poison.png").getImage(), -10, 30, iconWidth, iconHeight, this);
        }
        if (cursed) {
            g.drawImage(new ImageIcon("Icons/cursed.png").getImage(), 20, 30, iconWidth, iconHeight, this);
        }
        if (sticky) {
            g.drawImage(new ImageIcon("Icons/sticky.png").getImage(), 55, 30, iconWidth - 20, iconHeight, this);
        }
    }



    public IRoom getRoom() {
        return room;
    }
}
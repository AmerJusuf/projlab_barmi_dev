package View.WindowView;

import Controller.Notifiable;
import Game.Labyrinth;
import Rooms.IRoom;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.AffineTransform;
public class RoomNodeView extends JPanel implements IView {
    Notifiable controller;
    private IRoom room;

    private List<RoomNodeView> neighbourRooms = new ArrayList<>();
    private Image circleDefaultImage;
    private Image circleCurrentImage;
    private boolean poisoned;
    private boolean cursed;
    private boolean sticky;

    public int width;
    public int height;

    public static RoomNodeView lastClickedRoom = null;

    private int x;
    private int y;


    public RoomNodeView(IRoom room, boolean poisoned, boolean cursed, boolean sticky, Notifiable control) {
        this.room = room;
        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.poisoned = poisoned;
        this.cursed = cursed;
        this.sticky = sticky;
        this.controller = control;

        // Load circle image
        circleDefaultImage = new ImageIcon("Icons/basicroom.png").getImage();
        circleCurrentImage = new ImageIcon("Icons/poisonedroom.png").getImage();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked");
                lastClickedRoom = RoomNodeView.this;
                controller.notifyModelChanged();
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

    public void setPoisoned(){
        this.poisoned = true;
    }

    //importing from file uses this
    public RoomNodeView(IRoom room, boolean poisoned, boolean cursed, boolean sticky, int x, int y, Notifiable control) {
        this.room = room;
        this.x = x;
        this.y = y;
       // this.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed
        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.poisoned = poisoned;
        this.cursed = cursed;
        this.sticky = sticky;
        this.controller = control;

        // Load circle image
        circleDefaultImage = new ImageIcon("Icons/basicroom.png").getImage();
        circleCurrentImage = new ImageIcon("Icons/poisonedroom.png").getImage();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked");
                lastClickedRoom = RoomNodeView.this;
                controller.notifyModelChanged();
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
        setBounds(x, y);
        update();
    }

    public void setController(Notifiable controller) {
        this.controller = controller;
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

    public void handleRoomTypes(RoomNodeView roomNodeView){
        if(this.poisoned || roomNodeView.poisoned){
            this.poisoned = true;
        }
        if(this.cursed || roomNodeView.cursed){
            this.cursed = true;
        }
        if(this.sticky || roomNodeView.sticky){
            this.sticky = true;
        }
    }

    public void setBounds(int x, int y){
        this.x = x;
        this.y = y;
        super.setBounds(x, y, 220, 220);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBounds(x, y, 220, 220);

        width = getWidth() / 3;
        height = getHeight() / 3;

        // Calculate maximum size
        int maxSize = 100;

        // Draw circle image
        int imageWidth = Math.min(maxSize, width);
        int imageHeight = Math.min(maxSize, height);

        if (Labyrinth.currentPlayer.getRoom() == room) {
            g.drawImage(circleCurrentImage, 0, 0, imageWidth, imageHeight, this);
        }else {
            g.drawImage(circleDefaultImage, 0, 0, imageWidth, imageHeight, this);
        }

        int iconSize = Math.min(maxSize / 3, 30); // Adjust icon size as needed
        int iconSpacing = 10; // Adjust spacing between icons as needed
        int iconX = (imageWidth - iconSize) / 2; // Center horizontally
        int iconY = (imageHeight - iconSize) / 2; // Center vertically
        if (poisoned) {
            g.drawImage(new ImageIcon("Icons/poison.png").getImage(), iconX - 20, iconY, iconSize, iconSize, this);
            //iconX += iconSize + iconSpacing;
        }
        if (cursed) {
            g.drawImage(new ImageIcon("Icons/cursed.png").getImage(), iconX, iconY, iconSize, iconSize, this);
            iconX += iconSize + iconSpacing;
        }
        if (sticky) {
            g.drawImage(new ImageIcon("Icons/sticky.png").getImage(), iconX - 15, iconY, iconSize, iconSize, this);
        }

        String text = room.getCharacters().size() + "/" + room.getCapacity();
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();
        int textX = (getWidth() - textWidth) / 2; // Center horizontally
        int textY = (getHeight() - imageHeight) / 2 - textHeight / 2; // Above the circle image


        g.drawString(text, textX, textY);
    }

    public IRoom getRoom() {
        return room;
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public JLabel getLabel() {
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getPoisoned(){
        return poisoned;
    }

    public boolean getCursed(){
        return cursed;
    }

    public boolean getSticky(){
        return sticky;
    }

    public Notifiable getController(){
        return controller;
    }

}
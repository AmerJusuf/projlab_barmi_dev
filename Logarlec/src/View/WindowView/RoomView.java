package  View.WindowView;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;
import Rooms.IRoom;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view, which stores an IRoom object.
 * There are two of these views on the map, one of them shows the current students room
 * and the other, the last clicked room, where the student may try to move.
 */
public class RoomView implements ActionListener, IView {
    private IRoom room;

    private JPanel panel = new JPanel();
    private JLabel title;
    private JButton moveButton;
    private JButton stayButton;

    public RoomView(IRoom room)
    {


        if(Labyrinth.currentPlayer.getRoom() == room){
            title = new JLabel("Current Room");
            this.room = room;
        } else if (room != null) {
            title = new JLabel("DestinationRoom");
            this.room = room;
        } else {
            title = new JLabel("Click on a room to see its details");
        }


        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(4, 1, 0, 0));

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);

        handlePlayerPanel();
        handleItemPanel();
        initializeButtons();


        panel.setVisible(true);
    }

    private void initializeButtons(){
        if(room == null){
            return;
        }
        stayButton = new JButton("Stay");
        stayButton.setFocusable(false);
        stayButton.setPreferredSize(new Dimension(80, 30));
        stayButton.setVisible(false);
        stayButton.addActionListener(this);


        moveButton = new JButton("Move");
        moveButton.setFocusable(false);
        moveButton.setPreferredSize(new Dimension(80, 30));
        moveButton.setVisible(false);
        moveButton.addActionListener(this);



        if(Labyrinth.currentPlayer.getRoom() == room){
            stayButton.setVisible(true);
            moveButton.setVisible(false);
        } else {
            stayButton.setVisible(false);
            moveButton.setVisible(true);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(moveButton);
        buttonPanel.add(stayButton);
        panel.add(buttonPanel);
    }

    /**
     * The function, which updates the current characters part in a room.
     */
    private void handlePlayerPanel(){
        if(room == null){
            return;
        }
        JPanel playerListPanel = new JPanel();
        playerListPanel.setBackground(Color.LIGHT_GRAY);
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.X_AXIS));
        for (Character ch : room.getCharacters()) {
            IView player = MainWindow.viewsByObjects.get(ch);
            if (player != null) {
                playerListPanel.add(player.getLabel());
            }
        }
        JScrollPane scrollPane = new JScrollPane(playerListPanel);
        panel.add(scrollPane);
    }

    /**
     * The function, which updates the current items inside the room. (on the ground)
     */
    private void handleItemPanel(){
        if(room == null){
            return;
        }
        JPanel itemListPanel = new JPanel();
        itemListPanel.setBackground(Color.LIGHT_GRAY);
        itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.X_AXIS));
        for(Item item : room.getItems())
        {
            itemListPanel.add(MainWindow.viewsByObjects.get(item).getPanel());
        }
        JScrollPane scrollPane = new JScrollPane(itemListPanel);
        panel.add(scrollPane);
    }

    public void setRoom(IRoom room)
    {
        this.room = room;
    }

    /**
     * Handles the movement of characters.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moveButton) {
            System.out.println("Move");
         //   SwingUtilities.invokeLater(() -> {
                Labyrinth.currentPlayer.move(this.room);

//});
        }
        if (e.getSource() == stayButton) {
            System.out.println("Stay");
            Labyrinth.currentPlayer.setStayButtonClicked();

        }
    }

    @Override
    public void update() {
        panel.removeAll();
        if(room == null){
            title = new JLabel("Click on a room to see its details");
            title.setHorizontalAlignment(JLabel.CENTER);
            panel.add(title);
            return;
        }
        for(Item item : room.getItems())
        {
            MainWindow.viewsByObjects.get(item).update();
        }
        if(Labyrinth.currentPlayer.getRoom() == room){
            title = new JLabel("Current Room");
        } else {
            title = new JLabel("DestinationRoom");
        }
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(4, 1, 0, 0));

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);


        handlePlayerPanel();
        handleItemPanel();
        initializeButtons();
        if(Labyrinth.currentPlayer.getRoom() == room){
            stayButton.setVisible(true);
            moveButton.setVisible(false);
        } else {
            stayButton.setVisible(false);
            moveButton.setVisible(true);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public JLabel getLabel() {
        return title;
    }

    public IRoom getRoom() {
        return room;
    }

}

package  View.WindowView;

import Characters.Character;
import Items.Item;
import Rooms.*;
import Game.Labyrinth;
import View.CharacterView.CharacterView;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomView implements ActionListener, IView {
    private IRoom room;

    private JPanel panel = new JPanel();
    private JLabel title;
    private JButton moveButton;
    private JButton stayButton;

    //currentPLayer.getRoom() == room -> stayButton, amugymeg move

    public RoomView(IRoom room)
    {
        this.room = room;
        if(Labyrinth.currentPlayer.getRoom() == room){
            title = new JLabel("Current Room");
        } else {
            title = new JLabel("DestinationRoom");
        }
//        if(RoomNodeView.lastClickedRoom.getRoom() == room){
//            title = new JLabel("DestinationRoom");
//        }
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

    private void handlePlayerPanel(){
        JPanel playerListPanel = new JPanel();
        playerListPanel.setBackground(Color.LIGHT_GRAY);
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.X_AXIS));
        for (Character ch : room.getCharacters()) {
            IView player = MainWindow.viewsByObjects.get(ch);
            if (player != null) {
                playerListPanel.add(player.getLabel());
            } else {
                // Handle the case where the player view is not found for the character
            }
        }
        JScrollPane scrollPane = new JScrollPane(playerListPanel);
        panel.add(scrollPane);
    }

    private void handleItemPanel(){
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


    static public boolean moveButtonClicked = false;

    static public boolean isMoveButtonClicked() {
        return moveButtonClicked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moveButton) {
            System.out.println("Move");
            SwingUtilities.invokeLater(() -> {
                moveButtonClicked = true;
                Labyrinth.currentPlayer.move(this.room);
            });
        }
        if (e.getSource() == stayButton) {
            System.out.println("Stay");
            Labyrinth.currentPlayer.setMoveButtonClicked(true);
        }
    }

    @Override
    public void update() {
        panel.removeAll();
        if(Labyrinth.currentPlayer.getRoom() == room){
            stayButton.setVisible(true);
            moveButton.setVisible(false);
        } else {
            stayButton.setVisible(false);
            moveButton.setVisible(true);
        }
        handleItemPanel();
        handlePlayerPanel();
        initializeButtons();
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public JLabel getLabel() {
        return title;
    }

}

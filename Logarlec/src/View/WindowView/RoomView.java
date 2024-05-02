package  View.WindowView;

import Characters.Character;
import Items.*;
import Rooms.*;
import Game.Labyrinth;
import View.ItemView.AirFreshenerView;
import View.ItemView.TransistorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomView implements ActionListener {
    IRoom room;
    AirFreshener airFreshener = new AirFreshener();
    Transistor transistor = new Transistor();

    JPanel panel = new JPanel();
    JLabel title;
    JButton moveButton;
    JButton stayButton;

    //currentPLayer.getRoom() == room -> stayButton, amugymeg move

    public RoomView(IRoom room, int i)
    {
        this.room = room;
        if(i == 0){
            title = new JLabel("CurrentRoom");
            moveButton = new JButton("Stay");
        } else {
            title = new JLabel("DestinationRoom");
            moveButton = new JButton("Move");
        }
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(4, 1, 0, 0));
       // title = new JLabel("RoomView");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);

        handlePlayerPanel();
        handleItemPanel();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.LIGHT_GRAY);


        moveButton.setFocusable(false);
        moveButton.setPreferredSize(new Dimension(80, 30));
        moveButton.setVisible(true);
        buttonPanel.add(moveButton);
        panel.add(buttonPanel);

        panel.setVisible(true);
    }

    private void handlePlayerPanel(){
        JPanel playerListPanel = new JPanel();
        playerListPanel.setBackground(Color.LIGHT_GRAY);
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.X_AXIS));
        for( Character ch : room.getCharacters())
        {
            PlayerView player = new PlayerView(ch);
            playerListPanel.add(player.getIcon());
        }

        JScrollPane scrollPane = new JScrollPane(playerListPanel);
        panel.add(scrollPane);
    }

    private void handleItemPanel(){
        JPanel itemListPanel = new JPanel();
        itemListPanel.setBackground(Color.LIGHT_GRAY);
        itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.X_AXIS));
        for( Item item : room.getItems())
        {
            if( item instanceof AirFreshener)
            {
                AirFreshenerView airFreshenerView = new AirFreshenerView(airFreshener);
                itemListPanel.add(airFreshenerView.getPanel());
            } else if( item instanceof Transistor)
            {
                TransistorView transistorView = new TransistorView(transistor);
                itemListPanel.add(transistorView.getPickItemPanel());
            }
            itemListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        }
        JScrollPane scrollPane = new JScrollPane(itemListPanel);
        panel.add(scrollPane);
    }

    public void UpdateRoom(IRoom room)
    {
        this.room = room;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moveButton) {
            Labyrinth.currentPlayer.setMoveButtonClicked(true);
            Labyrinth.currentPlayer.move(new BasicRoom(0)); //TODO: must be the selected room
        }
        if (e.getSource() == stayButton) {
            System.out.println("Stay");
            Labyrinth.currentPlayer.setMoveButtonClicked(true);
        }
    }
}

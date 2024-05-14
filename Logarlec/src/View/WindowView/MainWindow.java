package  View.WindowView;

import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.Transistor;
import Rooms.BasicRoom;
import Rooms.IRoom;
import View.CharacterView.CharacterView;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MainWindow extends JFrame {
    List<IView> views;
    public static java.util.Map<Object, IView> viewsByObjects = new HashMap<>();

    public MainWindow() {
        this.setTitle("Best Game Ever");
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(10, 10, 10, 10);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height - 50);

        Map map = new Map();


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = insets;
        this.add(map, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(viewsByObjects.get(Labyrinth.currentPlayer).getPanel(), gbc);


        JPanel roomsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRooms = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;



        gbcRooms.gridx = 0;
        gbcRooms.gridy = 0;
        gbcRooms.gridwidth = 1;
        gbcRooms.gridheight = 1;
        gbcRooms.weightx = 0.5;
        gbcRooms.weighty = 1.0;
        gbcRooms.insets = new Insets(0, 0, 0, 10);
        gbcRooms.fill = GridBagConstraints.BOTH;
        RoomView roomSrc = new RoomView(Labyrinth.currentPlayer.getRoom());
        roomSrc.getPanel().setPreferredSize(new Dimension(350, 200));
        roomsPanel.add(roomSrc.getPanel(), gbcRooms);
        //roomV.panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));



        gbcRooms.gridx = 1;
        gbcRooms.gridy = 0;
        gbcRooms.gridwidth = 1;
        gbcRooms.gridheight = 1;
        gbcRooms.weightx = 1.0;
        gbcRooms.weighty = 1.0;
        gbcRooms.insets = new Insets(0, 10, 0, 0);
        gbcRooms.fill = GridBagConstraints.BOTH;
        RoomView roomDest = new RoomView(RoomNodeView.lastClickedRoom.getRoom());
        roomsPanel.add(roomDest.getPanel(), gbcRooms);

        this.add(roomsPanel, gbc);

        // Azért a legvégén kell mert csak így jelennek meg az elemek rajta
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.setSize(1000, 800);
        this.setVisible(true);


    }

    private void handleRoomViews(){

    }

    public void addView(IView view) {
        views.add(view);
    }

    public void removeView(IView view) {
        views.remove(view);
    }

    public void updateAllViews() {
        for (IView view : views) {
            view.update();
        }
    }


}

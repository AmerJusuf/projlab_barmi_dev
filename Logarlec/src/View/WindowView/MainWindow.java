package  View.WindowView;

import Controller.Controller;
import Controller.Notifiable;
import Game.GameState;
import Game.Labyrinth;
import View.CharacterView.StudentView;
import View.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

/**
 * The main frame, which can be reached from the menu.
 * It has four main parts, one of them representing the map, two for showing informations about rooms
 * and one for showing the current items of the player, whose round is on.
 */
public class MainWindow extends JFrame {
    List<IView> views;
    public static java.util.Map<Object, IView> viewsByObjects = new HashMap<>();
    Map map;
    RoomView roomSrc;
    RoomView roomDest;

    GridBagConstraints gbc;
    Notifiable controller;

    public MainWindow(Notifiable controller) {
        this.setTitle("Best Game Ever");
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        gbc = new GridBagConstraints();
        Insets insets = new Insets(10, 10, 10, 10);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height - 50);

        // tray icon
        ImageIcon image = new ImageIcon("Icons/cursor.jpg");
        this.setIconImage(image.getImage());

        // custom cursor
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(0, 5), "HandCursor");
        this.setCursor(customCursor);

        map = new Map(controller);
        this.controller = controller;
        try {
            while(!((Controller) controller).labyrinthReady)
                Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



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
        roomSrc = new RoomView(Labyrinth.currentPlayer.getRoom());
        roomSrc.getPanel().setPreferredSize(new Dimension(350, 200));
        roomsPanel.add(roomSrc.getPanel(), gbcRooms);



        gbcRooms.gridx = 1;
        gbcRooms.gridy = 0;
        gbcRooms.gridwidth = 1;
        gbcRooms.gridheight = 1;
        gbcRooms.weightx = 1.0;
        gbcRooms.weighty = 1.0;
        gbcRooms.insets = new Insets(0, 10, 0, 0);
        gbcRooms.fill = GridBagConstraints.BOTH;
        roomDest = new RoomView(null);
        roomDest.setRoom(null);

        roomsPanel.add(roomDest.getPanel(), gbcRooms);


        studentView = (StudentView) viewsByObjects.get(Labyrinth.currentPlayer);

        this.add(roomsPanel, gbc);

        // Azért a legvégén kell mert csak így jelennek meg az elemek rajta
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.setVisible(true);

        views = List.of(map,  roomSrc, roomDest, viewsByObjects.get(Labyrinth.currentPlayer));
        controller.notifyModelChanged();
    }


    public void addView(IView view) {
        views.add(view);
    }

    public void removeView(IView view) {
        views.remove(view);
    }

    StudentView studentView;

    public void updateAllViews() {
        if (Labyrinth.getGameState() == GameState.WIN) {
            JOptionPane.showMessageDialog(this, "You won!");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (Labyrinth.getGameState() == GameState.LOSE) {
            JOptionPane.showMessageDialog(this, "You lost!");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        roomSrc.setRoom(Labyrinth.currentPlayer.getRoom());

        if(RoomNodeView.lastClickedRoom == null){
            roomDest.setRoom(null);
        } else if(RoomNodeView.lastClickedRoom.getRoom() == Labyrinth.currentPlayer.getRoom()){
            roomDest.setRoom(null);
        }else {
            roomDest.setRoom(RoomNodeView.lastClickedRoom.getRoom());
        }
        roomSrc.update();
        roomDest.update();
        this.remove(studentView.getPanel());


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(viewsByObjects.get(Labyrinth.currentPlayer).getPanel(), gbc);
        studentView = (StudentView) viewsByObjects.get(Labyrinth.currentPlayer);

        views = List.of(map,  roomSrc, roomDest, studentView);


        for (IView view : views) {
            view.update();
        }
        map.removeAll();
        map.repaint();
        this.revalidate();
        this.repaint();
    }


}

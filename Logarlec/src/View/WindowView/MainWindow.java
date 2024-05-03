package  View.WindowView;

import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Items.AirFreshener;
import Items.FFP2;
import Items.Transistor;
import Rooms.BasicRoom;
import Rooms.IRoom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {


    public MainWindow() {
        this.setTitle("Best Game Ever");
        //this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        GridBagConstraints gbc = new GridBagConstraints();
        //this.setLayout(new FlowLayout());
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




        JPanel playerListPanel = new JPanel();
        playerListPanel.setBackground(Color.DARK_GRAY);


        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.X_AXIS));

        Student st = new Student();
        Student st2 = new Student();
        Instructor in = new Instructor();
        Cleaner cl = new Cleaner();

        PlayerView player1 = new PlayerView(st);
        playerListPanel.add(player1.panel);



        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(player1.panel, gbc);


        JPanel roomsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRooms = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;




       // AirFreshener af = new AirFreshener(false);
        Transistor tr = new Transistor();
        Transistor tr1 = new Transistor();
        Transistor tr2 = new Transistor();
        Transistor tr3 = new Transistor();
        Transistor tr4 = new Transistor();
        Transistor tr5 = new Transistor();
        Transistor tr6 = new Transistor();
        Transistor tr7 = new Transistor();
        Transistor tr8 = new Transistor();

        IRoom room = new BasicRoom(4);
        room.addCharacter(st2);
        room.addCharacter(st);
        room.addCharacter(in);
        room.addCharacter(cl);
        st.setRoom(room);
        st2.setRoom(room);
        in.setRoom(room);
        cl.setRoom(room);
        //room.addItem(af);
        room.addItem(tr);
        room.addItem(tr1);
        room.addItem(tr2);
        room.addItem(tr3);
        room.addItem(tr4);
        room.addItem(tr5);
        room.addItem(tr6);
        room.addItem(tr7);
        room.addItem(tr8);
        RoomView roomV = new RoomView(room, 0);
        roomV.panel.setPreferredSize(new Dimension(350, 200));

        //roomV.panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));


        gbcRooms.gridx = 0;
        gbcRooms.gridy = 0;
        gbcRooms.gridwidth = 1;
        gbcRooms.gridheight = 1;
        gbcRooms.weightx = 0.5;
        gbcRooms.weighty = 1.0;
        gbcRooms.insets = new Insets(0, 0, 0, 10);
        gbcRooms.fill = GridBagConstraints.BOTH;
        roomsPanel.add(roomV.panel, gbcRooms); //



        IRoom room2 = new BasicRoom(4);
        RoomView roomV2 = new RoomView(room2, 1);


        gbcRooms.gridx = 1;
        gbcRooms.gridy = 0;
        gbcRooms.gridwidth = 1;
        gbcRooms.gridheight = 1;
        gbcRooms.weightx = 1.0;
        gbcRooms.weighty = 1.0;
        gbcRooms.insets = new Insets(0, 10, 0, 0);
        gbcRooms.fill = GridBagConstraints.BOTH;
       roomsPanel.add(roomV2.panel, gbcRooms); //


        this.add(roomsPanel, gbc);

        // Azért a legvégén kell mert csak így jelennek meg az elemek rajta
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        //this.setSize(1000, 800);
        this.setVisible(true);
    }



}

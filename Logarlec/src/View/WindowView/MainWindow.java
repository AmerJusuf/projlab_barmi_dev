package  View.WindowView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {


    public MainWindow() {
        this.setTitle("Best Game Ever");
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);
        this.setResizable(false);
        //this.setLayout(new FlowLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Map map = new Map();
        map.panel.setBounds(0, 0, 1300, 600);
        this.add(map.panel);

        //playersView = new List<WindowView.PlayerView>();


        JPanel playerListPanel = new JPanel();
        playerListPanel.setBackground(Color.DARK_GRAY);

        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2); // Create a blue line border
        //playerListPanel.setBorder(border);


        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.X_AXIS));

        PlayerView player1 = new PlayerView("Player1");
        //player1.panel.setBorder(border);
        playerListPanel.add(player1.panel);

        PlayerView player2 = new PlayerView("Player2");
        playerListPanel.add(player2.panel);

        PlayerView player3 = new PlayerView("Player3");
        playerListPanel.add(player3.panel);

        PlayerView player4 = new PlayerView("Player4");
        playerListPanel.add(player4.panel);

        JScrollPane scrollPane = new JScrollPane(playerListPanel);
        scrollPane.setBounds(1310, 0, screenSize.width - 1330, 600);
        this.add(scrollPane);


        // Azért a legvégén kell mert csak így jelennek meg az elemek rajta
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.setSize(screenSize.width, screenSize.height);
        //this.setSize(1000, 800);
        this.setVisible(true);
    }



}

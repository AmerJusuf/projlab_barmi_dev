package  View.WindowView;

import javax.swing.*;
import java.awt.*;

public class RoomView {
    JPanel panel = new JPanel();

    JLabel title;


    RoomView(String curTitle)
    {
        //panel.setPreferredSize(new Dimension(200, 360));
        panel.setBackground(Color.GREEN);
        panel.setLayout(new GridLayout(2, 1, 5, 5));

        title = new JLabel(curTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);

        panel.setVisible(true);
    }
}

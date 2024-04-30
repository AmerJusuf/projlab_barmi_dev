package  View.WindowView;

import javax.swing.*;
import java.awt.*;

public class Map {
    JPanel panel = new JPanel();

    Map() {
        //panel.setBounds(0, 0, 500, 500);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
    }
}

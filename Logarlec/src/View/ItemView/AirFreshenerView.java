package View.ItemView;

import javax.swing.*;
import java.awt.*;

public class AirFreshenerView {
    JPanel panel = new JPanel();
    JLabel label;

    ImageIcon defIcon;

    public AirFreshenerView() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));
        defIcon = new ImageIcon("Icons/airfreshener.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);
    }

    public JPanel getPanel()
    {
        return panel;
    }
}

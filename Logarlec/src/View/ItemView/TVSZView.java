package View.ItemView;

import javax.swing.*;
import java.awt.*;

public class TVSZView {
    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon fakeItem;

    public TVSZView(boolean IsFake) {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/tvsz.png");
        fakeItem = new ImageIcon("Icons/tvszfake.png");

        label = new JLabel();
        if (IsFake)
            label.setIcon(fakeItem);
        else
            label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }
}

package View.ItemView;

import javax.swing.*;
import java.awt.*;

public class RagView {
    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;

    JButton pickButton = new JButton("Pick");
    JButton dropButton = new JButton("Drop");

    public RagView() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/rag.png");
        activeIcon = new ImageIcon("Icons/ragactive.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }
}

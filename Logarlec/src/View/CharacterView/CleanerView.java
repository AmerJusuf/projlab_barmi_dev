package View.CharacterView;

import javax.swing.*;

/**
 * A view, which is connected to a cleaner entity.
 */
public class CleanerView extends CharacterView{

    public CleanerView() {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));

        label = new JLabel();
        ImageIcon defIcon = new ImageIcon("Icons/cleaner.png");
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }

}

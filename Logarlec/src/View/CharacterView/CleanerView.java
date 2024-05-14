package View.CharacterView;

import Characters.Cleaner;

import javax.swing.*;

public class CleanerView extends CharacterView{
    private Cleaner cleaner;
    private ImageIcon defIcon = new ImageIcon("Icons/cleaner.png");

    public CleanerView(Cleaner cl) {
        cleaner = cl;
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }

    public JPanel getPanel() { return panel; }
    public JLabel getLabel() { return label; }
}

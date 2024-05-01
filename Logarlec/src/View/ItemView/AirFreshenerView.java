package View.ItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirFreshenerView implements ActionListener {
    JPanel panel = new JPanel();
    JLabel label;

    ImageIcon defIcon;

    JButton pickButton;
    JButton dropButton;

    public AirFreshenerView() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));
        defIcon = new ImageIcon("Icons/airfreshener.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickButton.setFocusable(false);
        pickButton.setVisible(false);
        panel.add(pickButton);

        dropButton = new JButton("Drop");
        dropButton.addActionListener(this);
        dropButton.setFocusable(false);
        dropButton.setVisible(false);
        panel.add(dropButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
        }
    }
}

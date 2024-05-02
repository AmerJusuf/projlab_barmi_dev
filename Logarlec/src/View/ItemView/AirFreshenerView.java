package View.ItemView;

import Items.AirFreshener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirFreshenerView implements ActionListener {
    AirFreshener airFreshener;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon placedIcon;

    JButton pickButton;
    JButton dropButton;
    JButton useButton;

    public AirFreshenerView(AirFreshener air) {
        airFreshener = air;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/airfreshener.png");
        placedIcon = new ImageIcon("Icons/airfreshenerplaced.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        label = new JLabel();
        label.setIcon(placedIcon);
        label.setVisible(false);
        panel.add(label);



        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickButton.setFocusable(false);
        pickButton.setVisible(false);
        panel.add(pickButton);

        useButton = new JButton("Place");
        useButton.addActionListener(this);
        useButton.setFocusable(false);
        useButton.setVisible(true);
        panel.add(useButton);

        dropButton = new JButton("Drop");
        dropButton.addActionListener(this);
        dropButton.setFocusable(false);
        dropButton.setVisible(true);
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
        if (e.getSource() == useButton) {
            System.out.println("Place");
            label.setIcon(placedIcon);
        }
    }
}

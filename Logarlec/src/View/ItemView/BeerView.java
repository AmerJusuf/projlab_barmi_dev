package View.ItemView;

import Items.Beer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeerView implements ActionListener {
    Beer beer;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;

    JButton pickButton;
    JButton dropButton;
    //JButton activeButton;

    public BeerView(Beer b) {
        beer = b;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/beer.png");
        activeIcon = new ImageIcon("Icons/beeractive.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        label = new JLabel();
        label.setIcon(activeIcon);
        label.setVisible(false);
        panel.add(label);



        /*
        activeButton = new JButton("Activate");
        activeButton.addActionListener(this);
        activeButton.setFocusable(false);
        panel.add(activeButton);

         */

        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickButton.setFocusable(false);
        pickButton.setVisible(false);
        panel.add(pickButton);

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
        /*
        if (e.getSource() == activeButton) {
            System.out.println("Button clicked");
            label.setIcon(activeIcon);
            activeButton.setEnabled(false);
        }
         */
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
            label.setIcon(defIcon);
        }
    }
}

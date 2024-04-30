package View.ItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeerView implements ActionListener {
    JPanel panel = new JPanel();

    JButton activeButton;

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;

    public BeerView() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/beer.png");
        activeIcon = new ImageIcon("Icons/beeractive.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        activeButton = new JButton("Activate");
        activeButton.addActionListener(this);
        activeButton.setFocusable(false);
        panel.add(activeButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == activeButton) {
            System.out.println("Button clicked");
            label.setIcon(activeIcon);
            activeButton.setEnabled(false);
        }
    }
}

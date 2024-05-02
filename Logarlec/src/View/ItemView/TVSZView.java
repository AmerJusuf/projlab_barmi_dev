package View.ItemView;

import Items.TVSZ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TVSZView implements ActionListener {
    TVSZ tvsz;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon fakeIcon;
    ImageIcon oneLifeIcon;
    ImageIcon twoLifeIcon;
    ImageIcon threeLifeIcon;

    JButton pickButton;
    JButton dropButton;

    public TVSZView(TVSZ t) {
        tvsz = t;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/tvsz.png");
        fakeIcon = new ImageIcon("Icons/tvszfake.png");
        oneLifeIcon = new ImageIcon("Icons/tvsz1life.png");
        twoLifeIcon = new ImageIcon("Icons/tvsz2life.png");
        threeLifeIcon = new ImageIcon("Icons/tvsz3life.png");

        label = new JLabel();
        setLabel();
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
        dropButton.setVisible(true);
        panel.add(dropButton);

        panel.setVisible(true);
    }

    public JPanel getPanel() { return panel; }

    public void setLabel() {
        if (tvsz.getOwner() == null)
            label.setIcon(defIcon);
        else if (tvsz.isFake())
            label.setIcon(fakeIcon);
        else if (tvsz.getSavesLeft() == 1)
            label.setIcon(oneLifeIcon);
        else if (tvsz.getSavesLeft() == 2)
            label.setIcon(twoLifeIcon);
        else if (tvsz.getSavesLeft() == 3)
            label.setIcon(threeLifeIcon);
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

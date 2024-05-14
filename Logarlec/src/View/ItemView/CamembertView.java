package View.ItemView;

import Game.Labyrinth;
import Items.Camembert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamembertView extends ItemView {
    ImageIcon defIcon = new ImageIcon("Icons/camembert.png");

    JButton openButton;

    public CamembertView(Camembert c) {
        super(c, new ImageIcon("Icons/camembert.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            Labyrinth.currentPlayer.pickItem(item);
        } else if (e.getSource() == dropButton) {
            Labyrinth.currentPlayer.dropItem(item);
        }
        else if (e.getSource() == openButton) {
            item.open();
        }
    }

    @Override
    void uniqueButtons() {
        openButton = new JButton("Open");
        openButton.addActionListener(this);
        openButton.setFocusable(false);
        openButton.setVisible(false);
        panel.add(openButton);
    }

    @Override
    void setUniqueButtonsVisibility(boolean visibility) {
        openButton.setVisible(visibility);
        panel.repaint();
    }

    public void updateItemIcon(boolean isActive) {
        label.setIcon(defIcon);
    }

}

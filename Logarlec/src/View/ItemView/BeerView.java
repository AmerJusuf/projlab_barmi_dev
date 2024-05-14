package View.ItemView;

import Items.Beer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeerView extends ItemView {
    private ImageIcon icon = new ImageIcon("Icons/beer.png");
    private ImageIcon activeIcon = new ImageIcon("Icons/beeractive.png");

    public BeerView(Beer b) {
        super(b, new ImageIcon("Icons/beer.png"));
    }

    @Override
    void uniqueButtons() {
        //empty
    }

    @Override
    void setUniqueButtonsVisibility(boolean visibility) {
        //empty
    }

    public void updateItemIcon(boolean isActive) {
        if(isActive) {
            label.setIcon(activeIcon);
        } else {
            label.setIcon(icon);
        }
    }
}

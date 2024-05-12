package View.ItemView;

import Items.TVSZ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TVSZView extends ItemView {

    private final TVSZ tvsz; //TVSZ is stored for savesLeft
    ImageIcon defIcon = new ImageIcon("Icons/tvsz.png");
    ImageIcon fakeIcon = new ImageIcon("Icons/tvszfake.png");
    ImageIcon  oneLifeIcon = new ImageIcon("Icons/tvsz1life.png");
    ImageIcon twoLifeIcon = new ImageIcon("Icons/tvsz2life.png");
    ImageIcon  threeLifeIcon = new ImageIcon("Icons/tvsz3life.png");


    public TVSZView(TVSZ t) {
        super(t, new ImageIcon("Icons/tvsz.png"));
        tvsz = t;
    }


    @Override
    void uniqueButtons() {
        // No unique buttons
    }

    @Override
    void setUniqueButtonsVisibility(boolean visibility) {
        // No unique buttons
    }

    @Override
    void updateItemIcon(boolean isActive) {
        if(item.isFake()){
            label.setIcon(fakeIcon);
        } else if(tvsz.getSavesLeft() == 1){
            label.setIcon(oneLifeIcon);
        } else if(tvsz.getSavesLeft() == 2){
            label.setIcon(twoLifeIcon);
        } else if(tvsz.getSavesLeft() == 3){
            label.setIcon(threeLifeIcon);
        } else {
            label.setIcon(defIcon);
        }
    }
}

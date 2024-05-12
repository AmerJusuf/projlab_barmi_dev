package View.ItemView;

import Items.Logarlec;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogarlecView extends ItemView{
    ImageIcon defIcon = new ImageIcon("Icons/logarlec.png");;
    ImageIcon fakeIcon = new ImageIcon("Icons/logarlecfake.png");


    public LogarlecView(Logarlec l) {
        super(l, l.isFake() ? new ImageIcon("Icons/logarlecfake.png") : new ImageIcon("Icons/logarlec.png"));
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
        } else {
            label.setIcon(defIcon);
        }
    }
}

package View.ItemView;

import Items.FFP2;

import javax.swing.*;

/**
 * A view, to store an FFP2 mask entity.
 */
public class FFP2View extends ItemView {
    ImageIcon defIcon = new ImageIcon("Icons/ffp2.png");
    ImageIcon activeIcon = new ImageIcon("Icons/ffp2active.png");
    ImageIcon fakeIcon = new ImageIcon("Icons/ffp2fake.png");

    public FFP2View(FFP2 f) {
        super(f, f.isFake() ? new ImageIcon("Icons/ffp2fake.png") : new ImageIcon("Icons/ffp2.png"));
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
        } else if(isActive){
            label.setIcon(activeIcon);
        } else {
            label.setIcon(defIcon);
        }
    }
}

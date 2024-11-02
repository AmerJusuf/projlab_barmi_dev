package View.ItemView;

import Items.Rag;

import javax.swing.*;

/**
 * A view, to store a rag entity.
 */
public class RagView extends ItemView {

    ImageIcon defIcon = new ImageIcon("Icons/rag.png");
    ImageIcon activeIcon = new ImageIcon("Icons/ragactive.png");


    public RagView(Rag r) {
        super(r, new ImageIcon("Icons/rag.png"));
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
        if(isActive){
            label.setIcon(activeIcon);
        } else {
            label.setIcon(defIcon);
        }
    }
}

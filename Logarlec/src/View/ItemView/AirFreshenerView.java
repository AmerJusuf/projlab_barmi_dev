package View.ItemView;

import Items.AirFreshener;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A view, to store an airfreshener object.
 */
public class AirFreshenerView extends ItemView {
    private JButton useButton;
    private ImageIcon icon = new ImageIcon("Icons/airfreshener.png");

    public AirFreshenerView(AirFreshener air) {
       super(air, new ImageIcon("Icons/airfreshener.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == useButton) {
            System.out.println("AirFreshener used | AirFreshenerView: actionPerformed(ActionEvent e)");
            item.unToxicateRoom();
        }
    }

    /**
     * Stores a use button to the airfeshener entity.
     */
    @Override
    void uniqueButtons() {
        useButton = new JButton("Use");
        useButton.addActionListener(this);
        useButton.setFocusable(false);
        useButton.setVisible(false);
        panel.add(useButton);
    }

    @Override
    void setUniqueButtonsVisibility(boolean visibility) {
        useButton.setVisible(visibility);

    }

    public void updateItemIcon(boolean isActive) {
        label.setIcon(icon);
    }


}

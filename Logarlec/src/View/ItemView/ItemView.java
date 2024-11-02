package View.ItemView;

import Game.Labyrinth;
import Items.Item;
import View.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view, to store an item.
 */
public abstract class ItemView implements IView, ActionListener {
    protected Item item;
    protected JPanel panel;
    protected JLabel label;

    protected JButton pickButton;

    protected JButton dropButton;

    /**
     * Creates a new itemview with the corresponding imageicon.
     * @param item The stored item.
     * @param icon The look of the item.
     */
    protected ItemView(Item item, ImageIcon icon) {
        this.item = item;

        panel = new JPanel();
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        label = new JLabel();
        label.setIcon(icon);
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
        dropButton.setVisible(false);
        panel.add(dropButton);

        uniqueButtons();

        if(item.getOwner() == null && Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(true);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
        } else if (item.getOwner() == null && !Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(false);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
        }else if(item.getOwner() != null){
            pickButton.setVisible(false);
            dropButton.setVisible(true);
            setUniqueButtonsVisibility(true);
        }
        panel.setVisible(true);

    }

    /**
     * Function to handle interactions with buttons - connected to the items.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            Labyrinth.currentPlayer.pickItem(item);
        } else if (e.getSource() == dropButton) {
            Labyrinth.currentPlayer.dropItem(item);
        }
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * If the item has special actions, then the corresponding activation buttons are stored in this inherited function.
     */
    abstract void uniqueButtons();

    /**
     * Makes the special buttons visible, if the icon has an owner.
     * @param visibility
     */
    abstract void setUniqueButtonsVisibility(boolean visibility);

    /**
     * Changes the icon of the current item.
     * @param isActive
     */
    abstract void updateItemIcon(boolean isActive);

    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Updates the state of the item.
     * Checks whether it has an owner and sets the look and buttons based on that information.
     */
    @Override
    public void update() {
        if(item.getOwner() == null && Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(true);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
        } else if (item.getOwner() == null && !Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(false);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
            updateItemIcon(item.getisActive());
        }else if(item.getOwner() != null){
            pickButton.setVisible(false);
            dropButton.setVisible(true);
            setUniqueButtonsVisibility(true);
            updateItemIcon(item.getisActive());
        }
        panel.updateUI();
    }

    /**
     * Returns the label of the item.
     * @return The label.
     */
    @Override
    public JLabel getLabel() {
        return label;
    }
}

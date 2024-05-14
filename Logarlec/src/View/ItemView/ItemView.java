package View.ItemView;

import Game.Labyrinth;
import Items.Item;
import View.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ItemView implements IView, ActionListener {
    protected Item item;
    protected JPanel panel;
    protected JLabel label;

    private JButton pickButton;

    private JButton dropButton;

    public ItemView(Item item, ImageIcon icon) {
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

//        if(item.getOwner() == null && Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
//            pickButton.setVisible(true);
//        } else {
//            dropButton.setVisible(true);
//            setUniqueButtonsVisibility(true);
//        }
//        update();
        if(item.getOwner() == null && Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(true);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
        } else if (item.getOwner() == null && !Labyrinth.currentPlayer.getRoom().getItems().contains(item)){
            pickButton.setVisible(false);
            dropButton.setVisible(false);
            setUniqueButtonsVisibility(false);
           // updateItemIcon(item.getisActive());
        }else if(item.getOwner() != null){
            pickButton.setVisible(false);
            dropButton.setVisible(true);
            setUniqueButtonsVisibility(true);
           // updateItemIcon(item.getisActive());
        }
        panel.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            item.pickedByStudent(Labyrinth.currentPlayer);
        } else if (e.getSource() == dropButton) {
            item.drop();
        }
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    abstract void uniqueButtons();
    abstract void setUniqueButtonsVisibility(boolean visibility);

    abstract void updateItemIcon(boolean isActive);

    @Override
    public JPanel getPanel() {
        return panel;
    }

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

    @Override
    public JLabel getLabel() {
        return label;
    }
}

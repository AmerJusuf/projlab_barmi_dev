package View.ItemView;

import Game.Labyrinth;
import Items.Transistor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransistorView extends ItemView {
    private final Transistor transistor; //Only the transistor

    ImageIcon defIcon = new ImageIcon("Icons/transistor.png");
    ImageIcon activeIcon = new ImageIcon("Icons/transistoractive.png");;
    ImageIcon pairedIcon = new ImageIcon("Icons/transistorpaired.png");;
    ImageIcon placedIcon = new ImageIcon("Icons/transistorplaced.png");;

    JButton activate;
    JButton pair;
    JButton place;

    public TransistorView(Transistor tr) {
        super(tr, new ImageIcon("Icons/transistor.png"));
        transistor = tr;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            Labyrinth.currentPlayer.pickItem(item);
        } else if (e.getSource() == dropButton) {
            Labyrinth.currentPlayer.dropItem(item);
        }
        else if (e.getSource() == activate) {
            System.out.println("Button activate clicked");
            transistor.setIsTurnedOn(!transistor.getisActive());
        }
        else if (e.getSource() == pair && transistor.getisActive()) {
            System.out.println("Button pair clicked");
            transistor.pairAutomatically();
        }
        else if (e.getSource() == place && transistor.getisActive() && transistor.getPairTransistor() != null) {
            System.out.println("Button placed clicked");
            item.placeTransistor();
        }
        updateItemIcon(transistor.getisActive());
    }

    @Override
    void uniqueButtons() {
        activate = new JButton("Activate");
        activate.addActionListener(this);
        activate.setFocusable(false);
        activate.setVisible(false);
        panel.add(activate);

        pair = new JButton("Pair");
        pair.addActionListener(this);
        pair.setFocusable(false);
        pair.setVisible(false);
        panel.add(pair);

        place = new JButton("Place");
        place.addActionListener(this);
        place.setFocusable(false);
        place.setVisible(false);
        panel.add(place);
    }

    @Override
    void setUniqueButtonsVisibility(boolean visibility) {
        activate.setVisible(visibility);
        pair.setVisible(visibility);
        place.setVisible(visibility);
    }

    @Override
    void updateItemIcon(boolean isActive) {
        if(isActive){
            if(transistor.getPairTransistor() != null){
                label.setIcon(pairedIcon);
            }
            else if(transistor.getPlaceLocation() != null){
                label.setIcon(placedIcon);
            }
            else {
                label.setIcon(activeIcon);
            }
        }
        else {
            label.setIcon(defIcon);
        }
    }


    //TODO mindkét transistor lerakása után, miután a játékost átviszi a másik szobába, pick helyett drop van
    @Override
    public void update() {
        if((item.getOwner() == null && Labyrinth.currentPlayer.getRoom().getItems().contains(item))){
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

}

package View.ItemView;

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
        if (e.getSource() == activate) {
            System.out.println("Button activevate clicked");
            transistor.setIsTurnedOn(true);
        }
        if (e.getSource() == pair && transistor.getisActive()) {
            System.out.println("Button pair clicked");
            //TODO: Studentnek fuggveny ami osszeparositja a nala levo ket aktiv transzisztort

        }
        if (e.getSource() == place && transistor.getisActive() && transistor.getPairTransistor() != null) {
            System.out.println("Button placed clicked");
            item.placeTransistor();
        }
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
            label.setIcon(activeIcon);
        } else if(transistor.getPairTransistor() != null){
            label.setIcon(pairedIcon);
        } else if(transistor.getPlaceLocation() != null){
            label.setIcon(placedIcon);
        } else {
            label.setIcon(defIcon);
        }
    }

}

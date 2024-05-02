package View.ItemView;

import Items.Transistor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransistorView implements ActionListener {
    Transistor transistor;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;
    ImageIcon placedIcon;
    ImageIcon pairedIcon;

    JButton activate;
    JButton pair;
    JButton place;

    JButton dropButton;

    //asd
    JPanel pickItemPanel;
    JButton pickButton;

    public TransistorView(Transistor tr) {
        transistor = tr;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/transistor.png");
        activeIcon = new ImageIcon("Icons/transistoractive.png");
        placedIcon = new ImageIcon("Icons/transistorplaced.png");
        pairedIcon = new ImageIcon("Icons/transistorpaired.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        activate = new JButton("Activate");
        activate.addActionListener(this);
        activate.setFocusable(false);
        panel.add(activate);

        pair = new JButton("Pair");
        pair.addActionListener(this);
        pair.setFocusable(false);
        panel.add(pair);

        place = new JButton("Place");
        place.addActionListener(this);
        place.setFocusable(false);
        panel.add(place);

//        pickButton = new JButton("Pick");
//        pickButton.addActionListener(this);
//        pickButton.setFocusable(false);
//        pickButton.setVisible(false);
//        panel.add(pickButton);

        dropButton = new JButton("Drop");
        dropButton.addActionListener(this);
        dropButton.setFocusable(false);
        dropButton.setVisible(false);
        panel.add(dropButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == activate) {
            System.out.println("Button activevate clicked");
            label.setIcon(activeIcon);
            transistor.setIsTurnedOn(true);
        }
        if (e.getSource() == pair && transistor.getisActive()) {
            System.out.println("Button pair clicked");
            label.setIcon(pairedIcon);

            // BEALLITANI A MASIK TRANSISTORT PARNAK
            //transistor.setPairTransistor();
        }
        if (e.getSource() == place && transistor.getisActive() && transistor.getPairTransistor() != null) {
            System.out.println("Button placed clicked");
            label.setIcon(placedIcon);

            // BEALLITANI A SZOBAT
            //transistor.setPlaceLocation();
        }
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
        }
    }

    public JPanel getPickItemPanel() {
        pickItemPanel = new JPanel();
        pickItemPanel.setBackground(Color.LIGHT_GRAY);
        pickItemPanel.add(new JLabel(defIcon));
        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickButton.setFocusable(false);
        pickButton.setVisible(true);
        pickItemPanel.add(pickButton);

        pickItemPanel.setVisible(true);
        return pickItemPanel;
    }

}

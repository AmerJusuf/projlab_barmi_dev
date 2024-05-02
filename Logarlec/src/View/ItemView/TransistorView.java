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

    JButton pickButton;
    JButton dropButton;

    JPanel pickItemPanel;

    public TransistorView(Transistor tr) {
        transistor = tr;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/transistor.png");
        activeIcon = new ImageIcon("Icons/transistoractive.png");
        pairedIcon = new ImageIcon("Icons/transistorpaired.png");
        placedIcon = new ImageIcon("Icons/transistorplaced.png");

        label = new JLabel();
        setLabel();
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

        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickButton.setFocusable(false);
        pickButton.setVisible(false);
        panel.add(pickButton);

        dropButton = new JButton("Drop");
        dropButton.addActionListener(this);
        dropButton.setFocusable(false);
        dropButton.setVisible(true);
        panel.add(dropButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setLabel() {
        if (transistor.getOwner() == null)
            label.setIcon(defIcon);
        else if (transistor.getisActive())
            label.setIcon(activeIcon);
        else if (transistor.getPairTransistor() != null)
            label.setIcon(pairedIcon);
        else if (transistor.getPlaceLocation() != null)
            label.setIcon(placedIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == activate) {
            System.out.println("Button activevate clicked");
            transistor.setIsTurnedOn(true);
            setLabel();
        }
        if (e.getSource() == pair && transistor.getisActive()) {
            System.out.println("Button pair clicked");
            setLabel();

            // BEALLITANI A MASIK TRANSISTORT PARNAK
            //transistor.setPairTransistor();
        }
        if (e.getSource() == place && transistor.getisActive() && transistor.getPairTransistor() != null) {
            System.out.println("Button placed clicked");
            setLabel();

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

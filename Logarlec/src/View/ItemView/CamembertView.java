package View.ItemView;

import Items.Camembert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamembertView implements ActionListener {
    Camembert camembert;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;

    JButton pickButton;
    JButton dropButton;
    JButton openButton;

    public CamembertView(Camembert c) {
        camembert = c;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/camembert.png");

        label = new JLabel();
        setLabel();
        label.setVisible(true);
        panel.add(label);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        openButton.setFocusable(false);
        panel.add(openButton);

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

    public void setLabel()  { label.setIcon(defIcon); }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            System.out.println("Button clicked");
        }
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
        }
    }
}

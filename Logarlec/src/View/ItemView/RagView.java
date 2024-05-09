package View.ItemView;

import Items.Rag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RagView implements ActionListener {
    Rag rag;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;

    JButton pickButton;
    JButton dropButton;

    public RagView(Rag r) {
        rag = r;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/rag.png");
        activeIcon = new ImageIcon("Icons/ragactive.png");

        label = new JLabel();
        setLabel();
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
        dropButton.setVisible(true);
        panel.add(dropButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setLabel() {
        if (rag.getOwner() == null)
            //label.setIcon(defIcon);
            label.setIcon(new ImageIcon("Icons/rag.png"));
        else if (rag.getisActive())
            label.setIcon(activeIcon);
        //label.setIcon(defIcon);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
        }
    }
}

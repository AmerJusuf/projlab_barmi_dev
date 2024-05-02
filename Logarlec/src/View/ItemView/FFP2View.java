package View.ItemView;

import Items.FFP2;

import Items.FFP2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FFP2View implements ActionListener {
    FFP2 ffp2;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;
    ImageIcon fakeIcon;


    JButton pickButton;
    JButton dropButton;

    public FFP2View(FFP2 f) {
        ffp2 = f;

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/ffp2.png");
        activeIcon = new ImageIcon("Icons/ffp2active.png");
        fakeIcon = new ImageIcon("Icons/ffp2fake.png");

        label = new JLabel();
        if (ffp2.isFake())
            label.setIcon(fakeIcon);
        else
            label.setIcon(defIcon);
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

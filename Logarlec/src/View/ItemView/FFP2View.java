package View.ItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FFP2View implements ActionListener {
    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon activeIcon;
    ImageIcon fakeIcon;

    JButton activeButton;
    JButton pickButton;
    JButton dropButton;

    public FFP2View(boolean isFake) {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));
        //panel.setBounds(0, 0, 500, 60);


        defIcon = new ImageIcon("Icons/ffp2.png");
        activeIcon = new ImageIcon("Icons/ffp2active.png");
        fakeIcon = new ImageIcon("Icons/ffp2fake.png");

        label = new JLabel();
        if (isFake)
            label.setIcon(fakeIcon);
        else
            label.setIcon(defIcon);
        //label.setBounds(0, 0, 20, 20);
        //label.setHorizontalAlignment(JLabel.LEFT);
        label.setVisible(true);
        panel.add(label);


        activeButton = new JButton("Activate");
        activeButton.addActionListener(this);
        activeButton.setFocusable(false);
        panel.add(activeButton);

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

        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == activeButton) {
            System.out.println("Button clicked");
            label.setIcon(activeIcon);
            activeButton.setEnabled(false);
        }
        if (e.getSource() == pickButton) {
            System.out.println("Picked");
        }
        if (e.getSource() == dropButton) {
            System.out.println("Dropped");
        }
    }
}

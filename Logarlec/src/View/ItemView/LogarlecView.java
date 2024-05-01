package View.ItemView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogarlecView implements ActionListener {
    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon;
    ImageIcon fakeIcon;

    JButton pickButton;
    JButton dropButton;

    LogarlecView(boolean isFake) {
        label = new JLabel();
        defIcon = new ImageIcon("Icons/logarlec.png");
        fakeIcon = new ImageIcon("Icons/logarlecfake.png");

        if (isFake)
            label.setIcon(fakeIcon);
        else
            label.setIcon(defIcon);
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

        panel.setVisible(true);
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

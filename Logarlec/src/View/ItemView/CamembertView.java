package View.ItemView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamembertView implements ActionListener {
    JPanel panel = new JPanel();

    JButton openButton;

    JLabel label;

    ImageIcon defIcon;

    public CamembertView() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 60));

        defIcon = new ImageIcon("Icons/camembert.png");

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        openButton.setFocusable(false);
        panel.add(openButton);

        panel.setVisible(true);
    }

    public JPanel getPanel()
    {
        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            System.out.println("Button clicked");
            openButton.setEnabled(false);
        }
    }
}

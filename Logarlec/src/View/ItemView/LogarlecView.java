package View.ItemView;

import javax.swing.*;

public class LogarlecView {
    JLabel label;
    ImageIcon defIcon;
    ImageIcon fakeIcon;

    LogarlecView(boolean isFake) {
        label = new JLabel();
        defIcon = new ImageIcon("Icons/logarlec.png");
        fakeIcon = new ImageIcon("Icons/logarlecfake.png");

        if (isFake)
            label.setIcon(fakeIcon);
        else
            label.setIcon(defIcon);
    }
}

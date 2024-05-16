package View.MenuView;

import javax.swing.*;
import java.awt.*;

public class ControlWindow extends JFrame {
    ControlWindow() {
        super("Control");
        setSize(500, 500);

        // tray icon
        ImageIcon image = new ImageIcon("Icons/logarlec.png");
        this.setIconImage(image.getImage());

        // custom cursor
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(0, 5), "HandCursor");
        this.setCursor(customCursor);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);
        setVisible(true);
    }
}

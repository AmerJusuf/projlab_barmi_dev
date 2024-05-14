package View.CharacterView;

import View.IView;

import javax.swing.*;
import java.awt.*;

public abstract class CharacterView implements IView {
    protected JPanel panel;
    protected JLabel label;
    protected JLabel title;


    public JLabel getLabel(){
        return label;
    }
    public JPanel getPanel() { return panel; }

    @Override
    public void update() {
        //empty, studentView overrides it
    }
}

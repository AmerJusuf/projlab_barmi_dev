package View;

import javax.swing.*;

public interface IView {
    void update();

    JPanel getPanel();

    JLabel getLabel();

    default void setX(int x){}

    default void setY(int y){}

    default void setBounds(int x, int y){}
}

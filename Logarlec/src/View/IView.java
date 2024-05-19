package View;

import javax.swing.*;

/**
 * An interface, which has basic functions to views such as roomviews, characterviews or itemviews.
 */
public interface IView {
    void update();

    JPanel getPanel();

    JLabel getLabel();

    default void setX(int x){}

    default void setY(int y){}

    default void setBounds(int x, int y){}
}

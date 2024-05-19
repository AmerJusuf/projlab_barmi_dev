package View.CharacterView;

import Characters.Instructor;

import javax.swing.*;

/**
 * A view, which is connected to an instructor entity.
 */
public class InstructorView extends CharacterView{
    Instructor instructor;

    ImageIcon defIcon = new ImageIcon("Icons/instructor.png");

    public InstructorView(Instructor inst) {
        instructor = inst;
        panel = new JPanel();
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }

}

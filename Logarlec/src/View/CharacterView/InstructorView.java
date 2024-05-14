package View.CharacterView;

import Characters.Instructor;

import javax.swing.*;

public class InstructorView extends CharacterView{
    Instructor instructor;

    ImageIcon defIcon = new ImageIcon("Icons/instructor.png");

    public InstructorView(Instructor inst) {
        instructor = inst;
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        panel.setVisible(true);
    }

}

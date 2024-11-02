package View.CharacterView;

import Characters.Student;
import Items.Item;
import View.WindowView.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * A view, which is connected to a student entity - controlled by a player.
 */
public class StudentView extends CharacterView {
    /**
     * The concrete student.
     */
    Student student;
    /**
     * The icon of the student.
     */
    ImageIcon defIcon = new ImageIcon("Icons/student.png");

    /**
     * Creates a view, to show the properties of a student entity.
     * @param st The stored student entity.
     */
    public StudentView(Student st) {
        student = st;

        title = new JLabel("Student");

        panel = new JPanel();
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));
        panel.setLayout(new GridLayout(6,1,0,0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        label.setPreferredSize(new Dimension(60, 60));
        panel.add(label);
        updateItemsList();

        panel.setVisible(true);
    }

    public Student getStudent() {
        return student;
    }

    /**
     * After the student has finished his round, this function is called, to show the next student on the screen.
     */
    @Override
    public void update() {
        panel.removeAll();
        panel.add(title);

        updateItemsList();

        panel.setVisible(true);
    }

    /**
     * If the student picked, dropped or activated an item, this function is called to update his view.
     */
    public void updateItemsList() {
        for(Item item : student.getItems()) {
            MainWindow.viewsByObjects.get(item).update();
            panel.add(MainWindow.viewsByObjects.get(item).getPanel());
        }
        panel.updateUI();
    }
}

package View.CharacterView;

import Characters.Student;
import Items.Item;
import View.WindowView.MainWindow;

import javax.swing.*;
import java.awt.*;

public class StudentView extends CharacterView {
    Student student;
    ImageIcon defIcon = new ImageIcon("Icons/student.png");

    JLabel title = new JLabel("Student");

    public StudentView(Student st) {
        student = st;

        panel = new JPanel();
        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));
        panel.setLayout(new GridLayout(6,1,0,0));
        panel.add(title);
        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        label.setPreferredSize(new Dimension(60, 60));
        panel.add(label);
        panel.add(new JLabel("Items:"));
        updateItemsList();

        panel.setVisible(true);
    }

    @Override
    public void update() {
        panel.removeAll();
        panel.add(title);
        panel.add(label);
        updateItemsList();
        panel.setVisible(true);
        //panel.repaint(); //kell?
    }

    public void updateItemsList() {
        for(Item item : student.getItems()) {
            panel.add(MainWindow.viewsByObjects.get(item).getPanel());
        }
        panel.updateUI();
    }
}

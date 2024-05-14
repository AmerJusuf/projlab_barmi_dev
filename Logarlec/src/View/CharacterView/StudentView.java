package View.CharacterView;

import Characters.Student;
import Items.Item;
import View.WindowView.MainWindow;

import javax.swing.*;

public class StudentView extends CharacterView {
    Student student;
    ImageIcon defIcon = new ImageIcon("Icons/student.png");

    public StudentView(Student st) {
        student = st;

        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));
        label = new JLabel();
        label.setIcon(defIcon);
        panel.add(label);
        updateItemsList();

        panel.setVisible(true);
    }

    @Override
    public void update() {
        panel.removeAll();
        panel.add(label);
        updateItemsList();
        panel.setVisible(true);
        panel.repaint(); //kell?
    }

    public JPanel getPanel() { return panel; }

    public void updateItemsList() {
        for(Item item : student.getItems()) {
            panel.add(MainWindow.viewsByObjects.get(item).getPanel());
        }
    }
}

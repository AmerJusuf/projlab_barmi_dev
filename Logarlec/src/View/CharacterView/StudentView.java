package View.CharacterView;

import Characters.Student;

import javax.swing.*;

public class StudentView {
    Student student;

    JPanel panel = new JPanel();

    JLabel label;

    ImageIcon defIcon = new ImageIcon("Icons/student.png");

    public StudentView(Student st) {
        student = st;

        panel.setBackground(java.awt.Color.LIGHT_GRAY);
        panel.setPreferredSize(new java.awt.Dimension(200, 60));

        label = new JLabel();
        label.setIcon(defIcon);
        label.setVisible(true);
        panel.add(label);

        updateItemsList();

        panel.setVisible(true);
    }

    public JPanel getPanel() { return panel; }

    public void updateItemsList() {

        // TODO: ez fogja frissiteni a student inventory-jat

        // Nem tudom ez mi copilot csinalta, de valamiért jónak tűnik megvalósítás szempontjából
        /*
        panel.removeAll();
        panel.add(label);
        for (int i = 0; i < student.getItems().size(); i++) {
            panel.add(student.getItems().get(i).getView().getPanel());
        }
        */
    }
}

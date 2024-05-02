package  View.WindowView;

import Characters.Student;
import Game.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Map {
    JPanel panel = new JPanel();
    Labyrinth labyrinth;

    Map() {
        List<Student> students;
        Student st = new Student();
        Student st2 = new Student();
        Student st3 = new Student();
        Student st4 = new Student();
        students = List.of(st, st2, st3, st4);

        labyrinth = new Labyrinth(students);
        //panel.setBounds(0, 0, 500, 500);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
    }
}

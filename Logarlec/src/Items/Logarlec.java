package Items;

import Characters.Instructor;
import Characters.Student;

public class Logarlec extends Item {

    public Logarlec() {
        super();
    }

    public void pickedByStudent(Student student) {
        System.out.println("Logarlec picked by student | Logarlec: pickedByStudent(Student student)");
        student.addItem(this);
    }

    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Logarlec does not picked by instructor | Logarlec: pickedByInstructor(Instructor instructor)");
    }
}

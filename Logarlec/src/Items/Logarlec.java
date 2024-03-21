package Items;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Game.GameState;
import Game.Labyrinth;

public class Logarlec extends Item {

    public Logarlec() {
        super();
    }

    public void pickedByStudent(Student student) {
        System.out.println("Logarlec picked by student | Logarlec: pickedByStudent(Student student)");
        this.setOwner(student);
        student.addItem(this);

        Labyrinth.setGameState(GameState.WIN);
    }

    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Logarlec does not picked by instructor | Logarlec: pickedByInstructor(Instructor instructor)");
    }
}

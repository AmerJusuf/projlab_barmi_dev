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


    /**
     * Indicates that the Logarlec item was picked by a student.
     * This method logs a message stating that the Logarlec item was picked
     * by the specified student. It then sets the owner of the Logarlec item
     * to the given student, adds the item to the student's collection, and
     * sets the game state to WIN.
     *
     * @param student The student who picked the Logarlec item.
     */
    public void pickedByStudent(Student student) {
        System.out.println("Logarlec picked by student | Logarlec: pickedByStudent(Student student)");
        this.setOwner(student);
        student.addItem(this);
        Labyrinth.setGameState(GameState.WIN);
    }

    /**
     * Indicates that the Logarlec item was not picked by an instructor.
     * This method logs a message stating that the Logarlec item was not picked
     * by the specified instructor.
     *
     * @param instructor The instructor who did not pick the Logarlec item.
     */
    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Logarlec does not picked by instructor | Logarlec: pickedByInstructor(Instructor instructor)");
    }
}

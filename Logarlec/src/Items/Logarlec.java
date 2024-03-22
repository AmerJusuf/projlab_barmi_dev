package Items;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Game.GameState;
import Game.Labyrinth;

public class Logarlec extends Item {

    /**
     * This constructor is used to create a Logarlec object.
     */
    public Logarlec() {
        super();
    }


    /**
     * Records Logarlec being picked by a student.
     * Updates the owner to the specified student, adds Logarlec to the student's collection,
     * and sets the game state to WIN.
     *
     * @param student The student who picked Logarlec.
     */
    public void pickedByStudent(Student student) {
        System.out.println("Logarlec picked by student | Logarlec: pickedByStudent(Student student)");
        this.setOwner(student);
        student.addItem(this);
        Labyrinth.setGameState(GameState.WIN);
    }

    /**
     * Logarlec cannot picked by an instructor.
     *
     * @param instructor The instructor who cannot pick the Logarlec.
     */
    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Logarlec does not picked by instructor | Logarlec: pickedByInstructor(Instructor instructor)");
    }
}

package Game;

import Characters.Instructor;
import Characters.Student;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private static GameState gameState;
    private int starterNumberOfRooms;
    private List<IRoom> rooms;
    private List<Student> students;
    private List<Instructor> instructors;

    //for testing
    public Labyrinth() {
        rooms = new ArrayList<>();
        students = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public void removeRoom(IRoom room) {
        rooms.remove(room);
    }

    public void mergeAndSplit() {
        // Merge and split rooms
    }

    public void generateMap() {
        // Generate the map
    }

    public void startGame() {
        // Start the game
    }

    public void nextRound() {
        // Go to the next round
    }

    public void endGame() {
        // End the game
    }

    public static void setGameState(GameState state) {
        gameState = state;
    }

    public void removeStudent(Student student) {
        System.out.println("Student removed from labyrinth | Labyrinth: removeStudent(Student student)");
    }

    public List<IRoom> getRooms() {
        return rooms;
    }
}

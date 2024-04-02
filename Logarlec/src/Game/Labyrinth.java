package Game;

import Characters.Instructor;
import Characters.Student;
import Items.Item;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Labyrinth {
    private static GameState gameState;
    private int starterNumberOfRooms;
    private List<IRoom> rooms;
    private List<Student> students;
    private List<Instructor> instructors;

    private List<Cleaner> cleaners;

    private List<Item> items;

    //for testing
    public Labyrinth() {
        rooms = new ArrayList<>();
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        items = new ArrayList<>();
        gameState = GameState.PLAYING;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public void removeRoom(IRoom room) {
        rooms.remove(room);
    }

    public void mergeAndSplitRandomly() {
        // Merge and split rooms
        Random rand = new Random();
        int idx1 = rand.nextInt(rooms.size());
        int idx2 = rand.nextInt(rooms.size());

        boolean mergeDone = false;
        boolean splitDone = false;

        while (!mergeDone){
            if (rooms.get(idx1).getNumberOfCharacters() == 0) {
                IRoom neighbour = getAcceptableNeighbour(rooms.get(idx1));
                merge(idx1, rooms.indexOf(neighbour));
                mergeDone = true;
            } else {
                idx1 = rand.nextInt(rooms.size());
            }
        }

        while (!splitDone){
            if (rooms.get(idx2).getNumberOfCharacters() == 0) {
                split(idx2);
                splitDone = true;
            } else {
                idx2 = rand.nextInt(rooms.size());
            }
        }

        //TODO: ha elfogytak az indexek break, fuggveny a mergelheto szobakra

    }

    private IRoom getAcceptableNeighbour(IRoom room) {
        List<IRoom> neighbours = room.getNeighbours();
        for (IRoom neighbour : neighbours) {
            if (neighbour.getNumberOfCharacters() == 0) {
                return neighbour;
            }
        }
        return null;
    }

    public void merge(int idx1, int idx2) {
        IRoom room1 = rooms.get(idx1);
        IRoom room2 = rooms.get(idx2);
        room1.mergeRooms(room2);
    }

    public void split(int idx) {
        IRoom room = rooms.get(idx);
        room.splitRoom();
    }

    public void generateMap() {
        // Generate the map
    }

    public void startGame() {
        while (gameState == GameState.PLAYING) {
            nextRound();
        }
        endGame();
    }

    public void nextRound() {
            for (Student student : students) {
                student.nextRound();
                triggerKickStudents();
            }
            for (Instructor instructor : instructors) {
                instructor.nextRound();
            }
            for (Cleaner cleaner : cleaners) {
                cleaner.nextRound();
            }
            for (Item item: items) {
                item.step();
            }
            mergeAndSplitRandomly();
    }

    public void endGame() {
        if(gameState == GameState.WIN) {
            System.out.println("Students won!");
        } else {
            System.out.println("Students lost!");
        }
    }

    private void triggerKickStudents(){
        for (Instructor instructor : instructors) {
            instructor.kickStudents();
        }
    }

    public static void setGameState(GameState state) {
        gameState = state;
    }

    public void removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            System.out.println("Student removed from labyrinth | Labyrinth: removeStudent(Student student)");
        }
    }

    public List<IRoom> getRooms() {
        return rooms;
    }

    public void replaceRooms(IRoom roomToRemove, IRoom roomToAdd) {
        rooms.remove(roomToRemove);
        rooms.add(roomToAdd);
    }
}

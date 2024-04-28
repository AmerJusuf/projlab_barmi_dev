package Game;

import Characters.Character;
import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Items.Item;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Labyrinth {
    private static GameState gameState = GameState.PLAYING;
    private int starterNumberOfRooms;
    private List<IRoom> rooms;
    private List<Student> students;
    private List<Instructor> instructors;

    private List<Cleaner> cleaners;


    public Labyrinth() {
        rooms = new ArrayList<>();
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        cleaners = new ArrayList<>();
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

        int maxAttempts = rooms.size() * 2; // Maximum próbálkozások száma
        int currentAttempts = 0;

        while (!mergeDone && currentAttempts < maxAttempts) {
            if (canMergeAnyRoom()) {
                if (rooms.get(idx1).getNumberOfCharacters() == 0) {
                    IRoom neighbour = getAcceptableNeighbour(rooms.get(idx1));
                    if (neighbour != null) { // Biztosítjuk, hogy a szomszéd létezik
                        merge(idx1, rooms.indexOf(neighbour));
                        mergeDone = true;
                    }
                }
                idx1 = rand.nextInt(rooms.size()); // Új index, ha a korábbi nem volt megfelelő
            }
            currentAttempts++; // Növeljük a próbálkozások számát
        }

        currentAttempts = 0; // Visszaállítjuk a próbálkozások számát a split művelethez

        while (!splitDone && currentAttempts < maxAttempts) {
            if (rooms.get(idx2).getNumberOfCharacters() == 0) {
                split(idx2);
                splitDone = true;
            } else {
                idx2 = rand.nextInt(rooms.size()); // Új index, ha a korábbi nem volt megfelelő
            }
            currentAttempts++; // Növeljük a próbálkozások számát
        }
        //TODO: ha elfogytak az indexek break, fuggveny a mergelheto szobakra
    }

    private boolean hasEmptyNeighbour(IRoom room) {
        for (IRoom neighbour : room.getNeighbours()) {
            if (neighbour.getNumberOfCharacters() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean canMergeAnyRoom() {
        for (IRoom room : rooms) {
            if (room.getNumberOfCharacters() == 0 && hasEmptyNeighbour(room)) {
                return true;
            }
        }
        return false;
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
                triggerKickStudents(); //TODO
            }
            for (Instructor instructor : instructors) {
                instructor.nextRound();
            }
            for (Cleaner cleaner : cleaners) {
                cleaner.nextRound();
            }
           // Osszes szoba tarygara es osszes karakterek targyaira step() fuggveny meghivasa

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

    public GameState getGameState() {return gameState;}

    public void removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            System.out.println("Student removed from labyrinth | Labyrinth: removeStudent(Student student)");
        }
    }
    //szükség lesz rá, mert például a tvsz tulajdonosa hallgató vagy oktató is lehet (a removeStudent helyett)
    public void removeCharacter(Character character){
        System.out.println("Character removed from labyrinth | Labyrinth: removeCharacter(Character character)");
    }

    public List<IRoom> getRooms() {
        return rooms;
    }

    public void replaceRooms(IRoom roomToRemove, IRoom roomToAdd) {
        rooms.remove(roomToRemove);
        rooms.add(roomToAdd);
    }

    public void addStudent(Student ch) {
        students.add(ch);
    }

    public void addInstructor(Instructor ch) {
        instructors.add(ch);
    }

    public void addCleaner(Cleaner ch) {
        cleaners.add(ch);
    }

    public void stepItems(){
        for(Student student : students){
            for(Item item : student.getItems()){
                item.step();
            }
        }
        for ( Instructor instructor : instructors){
            for(Item item : instructor.getItems()){
                item.step();
            }
        }
        for ( IRoom room : rooms){
            for(Item item : room.getItems()){
                item.step();
            }
        }
    }

    //Only for testing
    public List<Student> getStudents() {
        return students;
    }
}

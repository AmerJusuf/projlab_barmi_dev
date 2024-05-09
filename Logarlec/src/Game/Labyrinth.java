package Game;

import Characters.Character;
import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Items.*;
import Rooms.*;
import View.WindowView.MainWindow;
import View.WindowView.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Labyrinth {
    private static GameState gameState = GameState.PLAYING;
    private int starterNumberOfRooms;
    private List<IRoom> rooms;
    private List<Student> students;
    private List<Instructor> instructors;

    private List<Cleaner> cleaners;

    public static Student currentPlayer;

    private Map map;

    public Labyrinth() {
        rooms = new ArrayList<>();
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        cleaners = new ArrayList<>();
    }

    public Labyrinth(List<Student> students){
        this.students = students;
        instructors = new ArrayList<>();
        for(int i = 0; i < (students.size()*2); i++){
            Instructor instructor = new Instructor();
            instructors.add(instructor);
        }
        cleaners = new ArrayList<>();
        for(int i = 0; i < students.size(); i++){
            Cleaner cleaner = new Cleaner();
            cleaners.add(cleaner);
        }
        generateMap();
    }

    public Labyrinth(List<Student> students, Map map){
        this.students = students;
        instructors = new ArrayList<>();
        for(int i = 0; i < (students.size()*2); i++){
            Instructor instructor = new Instructor();
            instructors.add(instructor);
        }
        cleaners = new ArrayList<>();
        for(int i = 0; i < students.size(); i++){
            Cleaner cleaner = new Cleaner();
            cleaners.add(cleaner);
        }
        this.map = map;
        generateMap();

    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public void removeRoom(IRoom room) {
        rooms.remove(room);
    }

    public void mergeAndSplitRandomly() {
        if(rooms.size() < 2) return; // Ha nincs elég szoba, akkor nem lehet mergelni és splittelni

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

//        while (!splitDone && currentAttempts < maxAttempts) {
//            if (rooms.get(idx2).getNumberOfCharacters() == 0) {
//                split(idx2);
//                splitDone = true;
//            } else {
//                idx2 = rand.nextInt(rooms.size()); // Új index, ha a korábbi nem volt megfelelő
//            }
//            currentAttempts++; // Növeljük a próbálkozások számát
//        }
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



    public void startGame() {
        while (gameState == GameState.PLAYING) {
            nextRound();
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            map.removeAll();
            map.repaint();
        }
        endGame();
    }

    private int round = 0;
    public int getRound() {
        return round;
    }

    public String nextRound() {
        String fileContent = "";
            for (Student student : students) {
                currentPlayer = student;
                student.nextRound();
            }
            for (Instructor instructor : instructors) {
                instructor.nextRound();
            }
            if(cleaners != null)
                for (Cleaner cleaner : cleaners) {
                    cleaner.nextRound();
                }
           // Osszes szoba tarygara es osszes karakterek targyaira step() fuggveny meghivasa

           mergeAndSplitRandomly();



            stepItems();
            round++;
            return fileContent;
    }

    public void endGame() {
        if(gameState == GameState.WIN) {
            System.out.println("Students won!");
        } else {
            System.out.println("Students lost!");
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


    public void generateMap() {
        rooms = new ArrayList<>();

        //Row1
        BasicRoom room1 = new BasicRoom(3);
        CursedRoomDecorator room2 = new CursedRoomDecorator(new BasicRoom(4));
        BasicRoom room3 = new BasicRoom(5);
        PoisonedRoomDecorator room4 = new PoisonedRoomDecorator(new BasicRoom(3));
        BasicRoom room5 = new BasicRoom(2);

        //Row2
        PoisonedRoomDecorator room6 = new PoisonedRoomDecorator(new BasicRoom(3));
        BasicRoom room7 = new BasicRoom(4);
        CursedRoomDecorator room8 = new CursedRoomDecorator(new BasicRoom(5));
        BasicRoom room9 = new BasicRoom(3);
        BasicRoom room10 = new BasicRoom(2);

        //Row3
        BasicRoom room11 = new BasicRoom(3);
        BasicRoom room12 = new BasicRoom(4);
        CursedRoomDecorator room13 = new CursedRoomDecorator(new BasicRoom(5));
        PoisonedRoomDecorator room14 = new PoisonedRoomDecorator(new BasicRoom(3));
        BasicRoom room15 = new BasicRoom(2);

        //Row4
        BasicRoom room16 = new BasicRoom(3);
        PoisonedRoomDecorator room17 = new PoisonedRoomDecorator(new BasicRoom(4));
        BasicRoom room18 = new BasicRoom(5);
        CursedRoomDecorator room19 = new CursedRoomDecorator(new BasicRoom(3));
        BasicRoom room20 = new BasicRoom(2);

        //Row5
        BasicRoom room21 = new BasicRoom(3);
        BasicRoom room22 = new BasicRoom(4);
        CursedRoomDecorator room23 = new CursedRoomDecorator(new BasicRoom(5));
        BasicRoom room24 = new BasicRoom(3);
        PoisonedRoomDecorator room25 = new PoisonedRoomDecorator(new BasicRoom(2));


        //Adding neighbours
        room1.addNeighbour(room6);
        room1.addNeighbour(room2);

        //room2.addNeighbour(room1);
        room2.addNeighbour(room7);
        room2.addNeighbour(room3);

        room3.addNeighbour(room2);
        //room3.addNeighbour(room8);
        room3.addNeighbour(room4);

        room4.addNeighbour(room3);
        room4.addNeighbour(room9);
        //room4.addNeighbour(room5);

        room5.addNeighbour(room4);
        room5.addNeighbour(room10);

        room6.addNeighbour(room1);
        room6.addNeighbour(room11);
        room6.addNeighbour(room7);

        //room7.addNeighbour(room2);
        room7.addNeighbour(room6);
        room7.addNeighbour(room12);
        room7.addNeighbour(room8);

        room8.addNeighbour(room3);
        //room8.addNeighbour(room7);
        room8.addNeighbour(room13);
        room8.addNeighbour(room9);

        room9.addNeighbour(room4);
        room9.addNeighbour(room8);
        //room9.addNeighbour(room14);
        room9.addNeighbour(room10);

        room10.addNeighbour(room5);
        room10.addNeighbour(room9);
        //room10.addNeighbour(room15);

        room11.addNeighbour(room6);
        room11.addNeighbour(room16);
        //room11.addNeighbour(room12);

        room12.addNeighbour(room7);
        room12.addNeighbour(room11);
        //room12.addNeighbour(room17);
        room12.addNeighbour(room13);

        room13.addNeighbour(room8);
        //room13.addNeighbour(room12);
        room13.addNeighbour(room18);
        room13.addNeighbour(room14);

        room14.addNeighbour(room9);
        room14.addNeighbour(room13);
        room14.addNeighbour(room19);
        //room14.addNeighbour(room15);

        room15.addNeighbour(room10);
        //room15.addNeighbour(room14);
        room15.addNeighbour(room20);

        //room16.addNeighbour(room11);
        room16.addNeighbour(room21);
        room16.addNeighbour(room17);

        room17.addNeighbour(room12);
        room17.addNeighbour(room16);
        room17.addNeighbour(room22);
        //room17.addNeighbour(room18);

        room18.addNeighbour(room13);
        //room18.addNeighbour(room17);
        room18.addNeighbour(room23);
        room18.addNeighbour(room19);

        room19.addNeighbour(room14);
        room19.addNeighbour(room18);
        room19.addNeighbour(room24);
        //room19.addNeighbour(room20);

        room20.addNeighbour(room15);
        //room20.addNeighbour(room19);
        room20.addNeighbour(room25);

        room21.addNeighbour(room16);
        room21.addNeighbour(room22);

        room22.addNeighbour(room17);
        //room22.addNeighbour(room21);
        room22.addNeighbour(room23);

        room23.addNeighbour(room18);
        room23.addNeighbour(room22);
        //room23.addNeighbour(room24);

        room24.addNeighbour(room19);
       // room24.addNeighbour(room23);
        room24.addNeighbour(room25);

        room25.addNeighbour(room20);
        room25.addNeighbour(room24);

        room9.addNeighbour(room15);
        room1.addNeighbour(room7);

        //add each room to rooms

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(room9);
        rooms.add(room10);
        rooms.add(room11);
        rooms.add(room12);
        rooms.add(room13);
        rooms.add(room14);
        rooms.add(room15);
        rooms.add(room16);
        rooms.add(room17);
        rooms.add(room18);
        rooms.add(room19);
        rooms.add(room20);
        rooms.add(room21);
        rooms.add(room22);
        rooms.add(room23);
        rooms.add(room24);
        rooms.add(room25);


        for(IRoom room: rooms){
            room.setLabyrinth(this);
        }

        //add characters to rooms
        for(int i = 0; i < students.size(); i++){
            int j = i % 5;
            rooms.get(24-j).addCharacter(students.get(i));
            students.get(i).setRoom(rooms.get(24-j));
        }

        for(int i = 0; i < instructors.size(); i++){
            int j = i % 5;
            rooms.get(j).addCharacter(instructors.get(i));
            instructors.get(i).setRoom(rooms.get(j));
        }

        for (int i = 0; i < cleaners.size(); i++) {
            int j = new Random().nextInt(0,24);
            rooms.get(j).addCharacter(cleaners.get(i));
            cleaners.get(i).setRoom(rooms.get(j));
        }

        //add items to rooms
        for (IRoom room : rooms) {
            for(int i = 0; i < 5; i++){
                int j = new Random().nextInt(0, 6);
                boolean isFake = new Random().nextBoolean();
                switch (j){
                    case 0: {
                        room.addItem(new AirFreshener());
                        break;
                    }
                    case 1: {
                        room.addItem(new Beer(isFake));
                        break;
                    }
                    case 2: {
                        room.addItem(new Camembert(isFake));
                        break;
                    }
                    case 3: {
                        room.addItem(new FFP2(isFake));
                        break;
                    }
                    case 4: {
                        room.addItem(new Rag(isFake));
                        break;
                    }
                    case 5: {
                        room.addItem(new Transistor(isFake));
                    }
                    case 6: {
                        room.addItem(new TVSZ(isFake,3));
                    }
                }
            }
        }
        rooms.get(10).addItem(new Logarlec(false));
        rooms.get(6).addItem(new Logarlec(true));
    }

}

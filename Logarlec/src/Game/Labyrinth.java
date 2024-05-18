package Game;

import Characters.Character;
import Characters.Cleaner;
import Characters.Instructor;
import Characters.Student;
import Controller.Notifiable;
import Items.*;
import Rooms.*;
import View.CharacterView.CleanerView;
import View.CharacterView.InstructorView;
import View.CharacterView.StudentView;
import View.ItemView.*;
import View.WindowView.MainWindow;
import View.WindowView.Map;
import View.WindowView.RoomNodeView;
import View.WindowView.RoomView;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Labyrinth {
    private Notifiable controller;
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
        currentPlayer = students.getFirst();
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

    public Labyrinth(List<Student> students, Map map, Notifiable controller){
        this.controller = controller;
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

    public void setMap(Map map) {
        this.map = map;
    }

    public void setController(Notifiable controller){
        this.controller = controller;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public void removeRoom(IRoom room) {
        rooms.remove(room);
    }

    public void mergeAndSplitRandomly() {
//        if(rooms.size() < 2) return; // Ha nincs elég szoba, akkor nem lehet mergelni és splittelni
//
//        // Merge and split rooms
//        Random rand = new Random();
//        int idx1 = rand.nextInt(rooms.size());
//        int idx2 = rand.nextInt(rooms.size());
//
//        boolean mergeDone = false;
//        boolean splitDone = false;
//
//        int maxAttempts = rooms.size() * 2; // Maximum próbálkozások száma
//        int currentAttempts = 0;
//
//        while (!mergeDone && currentAttempts < maxAttempts) {
//            if (canMergeAnyRoom()) {
//                if (rooms.get(idx1).getNumberOfCharacters() == 0) {
//                    IRoom neighbour = getAcceptableNeighbour(rooms.get(idx1));
//                    if (neighbour != null) { // Biztosítjuk, hogy a szomszéd létezik
//                        merge(idx1, rooms.indexOf(neighbour));
//                        mergeDone = true;
//                    }
//                }
//                idx1 = rand.nextInt(rooms.size()); // Új index, ha a korábbi nem volt megfelelő
//            }
//            currentAttempts++; // Növeljük a próbálkozások számát
//        }

//        currentAttempts = 0; // Visszaállítjuk a próbálkozások számát a split művelethez
//
//        while (!splitDone && currentAttempts < maxAttempts) {
//            if (rooms.get(idx2).getNumberOfCharacters() == 0) {
//                split(idx2);
//                splitDone = true;
//            } else {
//                idx2 = rand.nextInt(rooms.size()); // Új index, ha a korábbi nem volt megfelelő
//            }
//            currentAttempts++; // Növeljük a próbálkozások számát
//        }

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

    public void redrawMap(){
        map.removeAll();
        map.repaint();
    }



    public void startGame() {
        currentPlayer = students.get(0);
        while (gameState == GameState.PLAYING) {
            nextRound();
            System.out.println("NextRound done! XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            redrawMap();
            if(students.isEmpty() || round >= 20){
                Labyrinth.setGameState(GameState.LOSE);
                controller.notifyModelChanged();
            }
        }
        endGame();
    }

    private int round = 0;
    public int getRound() {
        return round;
    }

    public static List<Student> kickedStudents;
    private Student asd; // ez nem csinál semmit, mire van?
    public String nextRound() {
        String fileContent = "";
        kickedStudents = new ArrayList<>();
        for (Student student : students) {
            if (asd != null && student == asd) {
                int i = -1;
            }
            currentPlayer = student;
            controller.notifyModelChanged();
            student.nextRound();
            for (Instructor instructor : instructors) {
                if (instructor.getRoom() == student.getRoom()) {
                    student.getCaught();
                }
            }
            controller.notifyModelChanged();
        }
        if (!kickedStudents.isEmpty()) {
            students.removeAll(kickedStudents);
            asd = kickedStudents.getFirst();
        }
        kickedStudents = new ArrayList<>();
        controller.notifyModelChanged();
        for (Instructor instructor : instructors) {
            instructor.nextRound();
        }
        if (cleaners != null)
            for (Cleaner cleaner : cleaners) {
                cleaner.nextRound();
            }
        for (IRoom room : rooms) {
            room.decorate();
        }

        // Osszes szoba tarygara es osszes karakterek targyaira step() fuggveny meghivasa
        mergeAndSplitRandomly();

        stepItems();
        round++;
        controller.notifyModelChanged();

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

    public static GameState getGameState() {return gameState;}

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

    public Notifiable getController(){
        return controller;
    }

    //Only for testing
    public List<Student> getStudents() {
        return students;
    }


    public void generateMap() {
        rooms = new ArrayList<>();

        //Row1
        BasicRoom room1 = new BasicRoom(3);
        MainWindow.viewsByObjects.put(room1, new RoomNodeView(room1, false ,false ,false, controller));

        CursedRoomDecorator room2 = new CursedRoomDecorator(new BasicRoom(4));
        RoomNodeView roomNodeView2 = new RoomNodeView(room2, false ,true ,false, controller);
        MainWindow.viewsByObjects.put(room2, roomNodeView2);

        BasicRoom room3 = new BasicRoom(5);
        MainWindow.viewsByObjects.put(room3, new RoomNodeView(room3, false ,false ,false, controller));


        PoisonedRoomDecorator room4 = new PoisonedRoomDecorator(new BasicRoom(3));
        MainWindow.viewsByObjects.put(room4, new RoomNodeView(room4, true ,false ,false, controller));

        BasicRoom room5 = new BasicRoom(2);
        MainWindow.viewsByObjects.put(room5, new RoomNodeView(room5, false ,false ,false, controller));

        //Row2
        PoisonedRoomDecorator room6 = new PoisonedRoomDecorator(new BasicRoom(3));
        MainWindow.viewsByObjects.put(room6, new RoomNodeView(room6, true ,false ,false, controller));

        BasicRoom room7 = new BasicRoom(4);
        MainWindow.viewsByObjects.put(room7, new RoomNodeView(room7, false ,false ,false, controller));

        CursedRoomDecorator room8 = new CursedRoomDecorator(new BasicRoom(5));
        MainWindow.viewsByObjects.put(room8, new RoomNodeView(room8, false ,true ,false, controller));

        BasicRoom room9 = new BasicRoom(3);
        MainWindow.viewsByObjects.put(room9, new RoomNodeView(room9, false ,false ,false, controller));

        BasicRoom room10 = new BasicRoom(2);
        MainWindow.viewsByObjects.put(room10, new RoomNodeView(room10, false ,false ,false, controller));

        //Row3
        BasicRoom room11 = new BasicRoom(3);
        MainWindow.viewsByObjects.put(room11, new RoomNodeView(room11, false ,false ,false, controller));

        BasicRoom room12 = new BasicRoom(4);
        MainWindow.viewsByObjects.put(room12, new RoomNodeView(room12, false ,false ,false, controller));

        PoisonedRoomDecorator room13 = new PoisonedRoomDecorator(new BasicRoom(5));
        MainWindow.viewsByObjects.put(room13, new RoomNodeView(room13, true ,false ,false, controller));

        PoisonedRoomDecorator room14 = new PoisonedRoomDecorator(new BasicRoom(3));
        MainWindow.viewsByObjects.put(room14, new RoomNodeView(room14, true ,false ,false, controller));

        BasicRoom room15 = new BasicRoom(2);
        RoomNodeView roomNodeView15 = new RoomNodeView(room15, false ,false ,false, controller);
        MainWindow.viewsByObjects.put(room15, new RoomNodeView(room15, false ,false ,false, controller));

        //Row4
        BasicRoom room16 = new BasicRoom(3);
        MainWindow.viewsByObjects.put(room16, new RoomNodeView(room16, false ,false ,false, controller));

        PoisonedRoomDecorator room17 = new PoisonedRoomDecorator(new BasicRoom(4));
        MainWindow.viewsByObjects.put(room17, new RoomNodeView(room17, true ,false ,false, controller));

        BasicRoom room18 = new BasicRoom(5);
        MainWindow.viewsByObjects.put(room18, new RoomNodeView(room18, false ,false ,false, controller));

        CursedRoomDecorator room19 = new CursedRoomDecorator(new BasicRoom(3));
        MainWindow.viewsByObjects.put(room19, new RoomNodeView(room19, false ,true ,false, controller));

        BasicRoom room20 = new BasicRoom(2);
        RoomNodeView roomNodeView20 = new RoomNodeView(room20, false ,false ,false, controller);
        MainWindow.viewsByObjects.put(room20, roomNodeView20);

        //Row5
        BasicRoom room21 = new BasicRoom(3);
        RoomNodeView roomNodeView21 = new RoomNodeView(room21, false ,false ,false, controller);
        MainWindow.viewsByObjects.put(room21, roomNodeView21);

        BasicRoom room22 = new BasicRoom(4);
        RoomNodeView roomNodeView22 = new RoomNodeView(room22, false ,false ,false, controller);
        MainWindow.viewsByObjects.put(room22, roomNodeView22);

        CursedRoomDecorator room23 = new CursedRoomDecorator(new BasicRoom(5));
        RoomNodeView roomNodeView23 = new RoomNodeView(room23, false ,true ,false, controller);
        MainWindow.viewsByObjects.put(room23, roomNodeView23);

        BasicRoom room24 = new BasicRoom(3);
        RoomNodeView roomNodeView24 = new RoomNodeView(room24, false ,false ,false, controller);
        MainWindow.viewsByObjects.put(room24, roomNodeView24);

        PoisonedRoomDecorator room25 = new PoisonedRoomDecorator(new BasicRoom(2));
        RoomNodeView roomNodeView25 = new RoomNodeView(room25, true ,false ,false, controller);
        MainWindow.viewsByObjects.put(room25, roomNodeView25);
        RoomNodeView.lastClickedRoom = roomNodeView15;

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


        int x = 200;
        int y = 100;
        for(IRoom room: rooms){
            room.setLabyrinth(this);
            MainWindow.viewsByObjects.get(room).setBounds(x, y);

            x += 220;
            if(x >= 1200){
                x = 200;
                y += 200;
            }
            MainWindow.viewsByObjects.get(room).update();
        }


//        AirFreshener airFreshener3 = new AirFreshener();
//        students.get(0).addItem(airFreshener3);
//        AirFreshenerView airFreshenerView2 = new AirFreshenerView(airFreshener3);
//        MainWindow.viewsByObjects.put(airFreshener3, airFreshenerView2);
        Labyrinth.currentPlayer = students.get(0);
        //add characters to rooms
        for(int i = 0; i < students.size(); i++){




            StudentView studentView = new StudentView(students.get(i));
            MainWindow.viewsByObjects.put(students.get(i), studentView);
            int j = i % 5;
            rooms.get(24-j).addCharacter(students.get(i));
            students.get(i).setRoom(rooms.get(24-j));

//            AirFreshener airFreshener3 = new AirFreshener();
//            students.get(i).addItem(airFreshener3);
//            airFreshener3.setOwner(students.get(i));
//            AirFreshenerView airFreshenerView2 = new AirFreshenerView(airFreshener3);
//            MainWindow.viewsByObjects.put(airFreshener3, airFreshenerView2);
//            MainWindow.viewsByObjects.get(students.get(i)).update();
        }



        for(int i = 0; i < instructors.size()-1; i++){
            if( i != 15) {
                MainWindow.viewsByObjects.put(instructors.get(i), new InstructorView(instructors.get(i)));
                int j = i % 5;
                rooms.get(j).addCharacter(instructors.get(i));
                instructors.get(i).setRoom(rooms.get(j));
            }
        }
        MainWindow.viewsByObjects.put(instructors.get(7), new InstructorView(instructors.get(7)));
        rooms.get(15).addCharacter(instructors.get(7));
        instructors.get(7).setRoom(rooms.get(15));

        for (int i = 0; i < cleaners.size(); i++) {
            MainWindow.viewsByObjects.put(cleaners.get(i), new CleanerView(cleaners.get(i)));
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
                        AirFreshener airFreshener = new AirFreshener();
                        room.addItem(airFreshener);
                        AirFreshenerView airFreshenerView = new AirFreshenerView(airFreshener);
                        MainWindow.viewsByObjects.put(airFreshener, airFreshenerView);
                        break;
                    }
                    case 1: {
                        Beer beer = new Beer(isFake);
                        room.addItem(beer);
                        BeerView beerView = new BeerView(beer);
                        MainWindow.viewsByObjects.put(beer, beerView);
                        break;
                    }
                    case 2: {
                        Camembert camembert = new Camembert(isFake);
                        room.addItem(camembert);
                        CamembertView camembertView = new CamembertView(camembert);
                        MainWindow.viewsByObjects.put(camembert, camembertView);
                        break;
                    }
                    case 3: {
                        FFP2 ffp2 = new FFP2(isFake);
                        room.addItem(ffp2);
                        FFP2View ffp2View = new FFP2View(ffp2);
                        MainWindow.viewsByObjects.put(ffp2, ffp2View);
                        break;
                    }
                    case 4: {
                        Rag rag = new Rag(isFake);
                        room.addItem(rag);
                        RagView ragView = new RagView(rag);
                        MainWindow.viewsByObjects.put(rag, ragView);
                        break;
                    }
                    case 5: {
                        Transistor transistor = new Transistor(isFake);
                        room.addItem(transistor);
                        TransistorView transistorView = new TransistorView(transistor);
                        MainWindow.viewsByObjects.put(transistor, transistorView);
                        break;
                    }
                    case 6: {
                        TVSZ tvsz = new TVSZ(isFake,3);
                        room.addItem(tvsz);
                        TVSZView tvszView = new TVSZView(tvsz);
                        MainWindow.viewsByObjects.put(tvsz, tvszView);
                    }
                }
            }
        }
        Logarlec logarlec = new Logarlec(false);
        rooms.get(10).addItem(logarlec);
        MainWindow.viewsByObjects.put(logarlec, new LogarlecView(logarlec));
        Logarlec logarlecFake = new Logarlec(true);
        rooms.get(6).addItem(logarlecFake);
        MainWindow.viewsByObjects.put(logarlecFake, new LogarlecView(logarlecFake));
    }

    public void setControllerToItems(){
        for (IRoom room : rooms){
            for(Item item : room.getItems()){
                item.setController(controller);
            }
        }
        for ( Student student : students){
            student.setController(controller);
        }
        for ( Instructor instructor : instructors){
            instructor.setController(controller);
        }
        for ( Cleaner cleaner : cleaners){
            cleaner.setController(controller);
        }
    }

}

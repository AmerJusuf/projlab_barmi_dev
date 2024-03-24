import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.*;
import Rooms.BasicRoom;
import Rooms.CursedRoomDecorator;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public void studentMoves() {
        System.out.println("Testing student move:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom();
        currentRoom.addNeighbour(nextRoom);

        student.move(nextRoom);
    }

    public void studentCannotMoveFullCapacity() {
        System.out.println("Testing student cannot move because nextRoom is full:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom(0);
        currentRoom.addNeighbour(nextRoom);

        student.move(nextRoom);
    }

    public void studentPicksItem() {
        System.out.println("Testing student picks item:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);

        student.pickItem(new Camembert());
    }

    public void instructorPicksItem() {
        System.out.println("Testing instructor picks item:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);

        instructor.pickItem(new Camembert());
    }

    public void studentCanNotPickItemFullInventory() {
        System.out.println("Testing student can not pick item full inventory:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        for (int i = 0; i < 5; i++) {
            student.addItem(new Camembert());
        }

        student.pickItem(new Camembert());
    }

    public void instructorCanNotPickItemFullInventory() {
        System.out.println("Testing instructor can not pick item full inventory:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        for (int i = 0; i < 5; i++) {
            instructor.addItem(new Camembert());
        }

        instructor.pickItem(new Camembert());
    }

    public void studentPicksLogarlec() {
        System.out.println("Testing student picks logarlec:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);

        student.pickItem(new Logarlec());
    }

    public void instructorCanNotPickLogarlec() {
        System.out.println("Testing instructor can not pick logarlec:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);

        instructor.pickItem(new Logarlec());
    }

    public void TVSZProtectsStudent() {
        System.out.println("Testing TVSZ protects student:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(3);
        student.addItem(tvsz);
        tvsz.setOwner(student);

        tvsz.protectStudent();
    }

    public void TVSZProtectsStudentThenExpires() {
        System.out.println("Testing TVSZ protects student then expires:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(1);
        student.addItem(tvsz);
        tvsz.setOwner(student);

        tvsz.protectStudent();
    }

    public void camembertOpen() {
        System.out.println("Testing camembert open:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        Instructor instructor = new Instructor(currentRoom);
        currentRoom.addCharacter(student);
        currentRoom.addCharacter(instructor);
        Camembert camembert = new Camembert();
        student.addItem(camembert);
        camembert.setOwner(student);

        camembert.open();
    }

    public void instructorKicksStudent() {
        System.out.println("Testing instructor kicks student:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        Student student = new Student(currentRoom);
        currentRoom.addCharacter(student);
        currentRoom.addCharacter(instructor);

        instructor.kickStudents();
    }

    public void studentGetsCaughtButProtected() {
        System.out.println("Testing student gets caught but protected:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(2);
        student.addItem(tvsz);
        tvsz.setOwner(student);

        student.getCaught();
    }

    public void studentGetsCaught() {
        System.out.println("Testing student gets caught:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);

        student.getCaught();
    }

    public void instructorGetsCaught() {
        System.out.println("Testing instructor gets caught:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);

        instructor.getCaught();
    }

    public void studentGetsDisabledButProtected() {
        System.out.println("Testing student gets disabled but protected:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        FFP2 ffp2 = new FFP2();
        student.addItem(ffp2);
        ffp2.setOwner(student);

        student.disable();
    }

    public void studentGetsDisabled() {
        System.out.println("Testing student gets disabled:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);

        student.disable();
    }

    public void onlyInstructorGetsDisabled() {
        System.out.println("Testing only instructor gets disabled:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        Student student = new Student(currentRoom);

        student.disableInstructor();
        instructor.disableInstructor();
    }

    public void transistorSwitchOn() {
        System.out.println("Testing transistor switch on:");
        Transistor transistor = new Transistor();
        transistor.setIsTurnedOn(false);

        transistor.switchTransistor();
    }

    public void transistorSwitchOff() {
        System.out.println("Testing transistor switch off:");
        Transistor transistor = new Transistor();
        transistor.setIsTurnedOn(true);

        transistor.switchTransistor();
    }

    public void pairTransistorsBothNotPairedYet() {
        System.out.println("Testing pair transistors, if both transistors are not paired yet:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();

        tr.pairTransistor(pair);


    }

    public void canNotPairTransistorsBecauseFirstIsPaired() {
        System.out.println("Testing pair transistors and first transistor is paired:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();

        tr.pairTransistor(pair);
    }

    public void canNotPairTransistorsBecauseSecondIsPaired() {
        System.out.println("Testing pair transistors and second transistor is paired:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();

        tr.pairTransistor(pair);
    }

    public void placeSecondTransistorAndMove() {
        System.out.println("Testing place transistor and move:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom ptPlaceLocation = new BasicRoom();
        Student owner = new Student(ptPlaceLocation);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        tr.setPlaceLocation(ptPlaceLocation);

        tr.place();

    }

    public void placeFirstTransistorActiveAndPaired() {
        System.out.println("Testing place first transistor if active and paired:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom ptPlaceLocation = new BasicRoom();
        Student owner = new Student(ptPlaceLocation);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        tr.setPlaceLocation(ptPlaceLocation);

        tr.place();
    }

    public void canNotPlaceTransistorBecauseNotActive() {
        System.out.println("Testing place transistor if not active:");
        Transistor tr = new Transistor();

        tr.place();
    }

    public void canNotPlaceTransistorBecauseNotHaveAPair() {
        System.out.println("Testing place transistor if do not have pair:");
        Transistor tr = new Transistor();

        tr.place();
    }

    public void placeSecondTransistorActiveAndCanNotMoveBecauseOfCapacity() {
        System.out.println("Testing place transistor if can not move because the room do not have enough capacity:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom ptPlaceLocation = new BasicRoom();
        Student owner = new Student(ptPlaceLocation);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        tr.setPlaceLocation(ptPlaceLocation);

        tr.place();
    }

    public void poisonedRoomToxicatesCharacters() {}

    public void mergeRooms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1# Test merge rooms success");
        System.out.println("2# Test merge rooms fail");
        switch (scanner.nextInt()) {
            case 1:
                merrgeRoomsSuccess();
                break;
            case 2:
                mergeRoomsFail();
                break;
            default:
                break;
        }
        scanner.close();
    }

    public void merrgeRoomsSuccess() {
        System.out.println("Testing merge rooms success:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room1 = new BasicRoom();
        room1.setCapacity(7);
        BasicRoom room2 = new BasicRoom();
        room2.setCapacity(5);
        Camembert camembert = new Camembert();
        room2.addItem(camembert);
        labyrinth.addRoom(room1);
        labyrinth.addRoom(room2);

        room1.mergeRooms(room2);
    }

    public void mergeRoomsFail() {
        System.out.println("Testing merge rooms fail:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room1 = new BasicRoom();
        BasicRoom room2 = new BasicRoom();
        Student student = new Student(room1);
        room2.addCharacter(student);
        labyrinth.addRoom(room1);
        labyrinth.addRoom(room2);

        room1.mergeRooms(room2);
    }

    public void splitRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1# Test split room success");
        System.out.println("2# Test split room fail");
        switch (scanner.nextInt()) {
            case 1:
                splitRoomSuccess();
                break;
            case 2:
                splitRoomFail();
                break;
            default:
                break;
        }
        scanner.close();
    }

    public void splitRoomSuccess() {
        System.out.println("Testing split room success:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        cursedRoomDecorator.setCapacity(6);
        Camembert camembert = new Camembert();
        cursedRoomDecorator.addItem(camembert);
        labyrinth.addRoom(room);

        cursedRoomDecorator.splitRoom();
    }

    public void splitRoomFail() {
        System.out.println("Testing split room fail:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        cursedRoomDecorator.setCapacity(6);
        Camembert camembert = new Camembert();
        cursedRoomDecorator.addItem(camembert);
        Student student = new Student(room);
        cursedRoomDecorator.addCharacter(student);
        labyrinth.addRoom(room);

        cursedRoomDecorator.splitRoom();
    }

    public void cursedRoomManagesDoors() {
        System.out.println("Testing cursed room manages doors:");
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        BasicRoom neighbour = new BasicRoom();
        cursedRoomDecorator.setNeighbours(new ArrayList<IRoom>());
        List<IRoom> neighbours = new ArrayList<>();
        neighbours.add(neighbour);
        cursedRoomDecorator.setHiddenNeighbours(neighbours);
    }

    public void ragStunsInstructor() {
        System.out.println("Testing rag stuns instructor:");
        BasicRoom room = new BasicRoom();
        Instructor instructor = new Instructor(room);
        Rag rag = new Rag();
        Student student = new Student(room);
        student.addItem(rag);
        rag.setOwner(student);
        room.addCharacter(instructor);
        room.addCharacter(student);

        rag.stunInstructor();
    }

    public void beerProtectsStudent() {
        System.out.println("Testing beer protects student:");
        BasicRoom room = new BasicRoom();
        Student student = new Student(room);
        Beer beer = new Beer();
        student.addItem(beer);
        beer.setOwner(student);

        student.getCaught();
    }

    public void ragProtectsStudent() {
        System.out.println("Testing rag protects student:");
        BasicRoom room = new BasicRoom();
        Student student = new Student(room);
        Rag rag = new Rag();
        student.addItem(rag);
        rag.setOwner(student);

        student.getCaught();
    }
}

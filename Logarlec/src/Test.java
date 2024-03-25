import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.*;
import Rooms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public void studentMoves() {
        System.out.println("Testing student move:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom();
        currentRoom.addNeighbour(nextRoom);
        System.out.println("Test:");

        student.move(nextRoom);
    }

    public void studentCannotMoveFullCapacity() {
        System.out.println("Testing student cannot move because nextRoom is full:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom(0);
        currentRoom.addNeighbour(nextRoom);
        System.out.println("Test:");

        student.move(nextRoom);
    }

    public void studentPicksItem() {
        System.out.println("Testing student picks item:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        System.out.println("Test:");

        student.pickItem(new Camembert());
    }

    public void instructorPicksItem() {
        System.out.println("Testing instructor picks item:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        System.out.println("Test:");

        instructor.pickItem(new Camembert());
    }

    public void studentCanNotPickItemFullInventory() {
        System.out.println("Testing student can not pick item full inventory:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        for (int i = 0; i < 5; i++) {
            student.addItem(new Camembert());
        }
        System.out.println("Test:");

        student.pickItem(new Camembert());
    }

    public void instructorCanNotPickItemFullInventory() {
        System.out.println("Testing instructor can not pick item full inventory:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        for (int i = 0; i < 5; i++) {
            instructor.addItem(new Camembert());
        }
        System.out.println("Test:");

        instructor.pickItem(new Camembert());
    }

    public void studentPicksLogarlec() {
        System.out.println("Testing student picks logarlec:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        System.out.println("Test:");

        student.pickItem(new Logarlec());
    }

    public void instructorCanNotPickLogarlec() {
        System.out.println("Testing instructor can not pick logarlec:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        System.out.println("Test:");

        instructor.pickItem(new Logarlec());
    }

    public void TVSZProtectsStudent() {
        System.out.println("Testing TVSZ protects student:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(3);
        student.addItem(tvsz);
        tvsz.setOwner(student);
        System.out.println("Test:");

        tvsz.protectStudent();
    }

    public void TVSZProtectsStudentThenExpires() {
        System.out.println("Testing TVSZ protects student then expires:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(1);
        student.addItem(tvsz);
        tvsz.setOwner(student);
        System.out.println("Test:");

        tvsz.protectStudent();
    }

    public void camembertOpen() {
        System.out.println("Testing camembert open:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        Instructor instructor = new Instructor(currentRoom);
        currentRoom.addCharacter(student);
        currentRoom.addCharacter(instructor);
        Camembert camembert = new Camembert();
        student.addItem(camembert);
        camembert.setOwner(student);
        System.out.println("Test:");

        camembert.open();
    }

    public void instructorKicksStudent() {
        System.out.println("Testing instructor kicks student:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        Student student = new Student(currentRoom);
        currentRoom.addCharacter(student);
        currentRoom.addCharacter(instructor);
        System.out.println("Test:");

        instructor.kickStudents();
    }

    public void studentGetsCaughtButProtected() {
        System.out.println("Testing student gets caught but protected:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        TVSZ tvsz = new TVSZ(2);
        student.addItem(tvsz);
        tvsz.setOwner(student);
        System.out.println("Test:");

        student.getCaught();
    }

    public void studentGetsCaught() {
        System.out.println("Testing student gets caught:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        System.out.println("Test:");

        student.getCaught();
    }

    public void instructorGetsCaught() {
        System.out.println("Testing instructor gets caught:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        System.out.println("Test:");

        instructor.getCaught();
    }

    public void studentGetsDisabledButProtected() {
        System.out.println("Testing student gets disabled but protected:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        FFP2 ffp2 = new FFP2();
        student.addItem(ffp2);
        ffp2.setOwner(student);
        System.out.println("Test:");

        student.disable();
    }

    public void studentGetsDisabled() {
        System.out.println("Testing student gets disabled:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        System.out.println("Test:");

        student.disable();
    }

    public void onlyInstructorGetsDisabled() {
        System.out.println("Testing only instructor gets disabled:");
        System.out.println("Setup test:");
        BasicRoom currentRoom = new BasicRoom();
        Instructor instructor = new Instructor(currentRoom);
        Student student = new Student(currentRoom);
        System.out.println("Test:");

        student.disableInstructor();
        instructor.disableInstructor();
    }

    public void transistorSwitchOn() {
        System.out.println("Testing transistor switch on:");
        System.out.println("Setup test:");
        Transistor transistor = new Transistor();
        transistor.setIsTurnedOn(false);
        System.out.println("Test:");

        transistor.switchTransistor();
    }

    public void transistorSwitchOff() {
        System.out.println("Testing transistor switch off:");
        System.out.println("Setup test:");
        Transistor transistor = new Transistor();
        transistor.setIsTurnedOn(true);
        System.out.println("Test:");

        transistor.switchTransistor();
    }

    public void pairTransistorsBothNotPairedYet() {
        System.out.println("Testing pair transistors, if both transistors are not paired yet:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();
        System.out.println("Test:");

        tr.pairTransistor(pair);
    }

    public void canNotPairTransistorsBecauseFirstIsPaired() {
        System.out.println("Testing pair transistors and first transistor is paired:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();
        tr.setPairTransistor(pair);
        System.out.println("Test:");

        tr.pairTransistor(pair);
    }

    public void canNotPairTransistorsBecauseSecondIsPaired() {
        System.out.println("Testing pair transistors and second transistor is paired:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pair = new Transistor();
        pair.setPairTransistor(tr);
        System.out.println("Test:");

        tr.pairTransistor(pair);
    }

    public void placeSecondTransistorAndMove() {
        System.out.println("Testing place transistor and move:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom ptPlaceLocation = new BasicRoom();
        BasicRoom currentRoom = new BasicRoom();
        Student owner = new Student(currentRoom);
        currentRoom.addNeighbour(ptPlaceLocation);
        tr.setIsTurnedOn(true);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        pairTransistor.setPlaceLocation(ptPlaceLocation);
        System.out.println("Test:");

        tr.place();

    }

    public void placeFirstTransistorActiveAndPaired() {
        System.out.println("Testing place first transistor if active and paired:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom currentRoom = new BasicRoom();
        Student owner = new Student(currentRoom);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        tr.setIsTurnedOn(true);
        System.out.println("Test:");

        tr.place();
    }

    public void canNotPlaceTransistorBecauseNotActive() {
        System.out.println("Testing place transistor if not active:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom currentRoom = new BasicRoom();
        Student owner = new Student(currentRoom);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        System.out.println("Test:");

        tr.place();
    }

    public void canNotPlaceTransistorBecauseNotHaveAPair() {
        System.out.println("Testing place transistor if does not have pair:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        BasicRoom currentRoom = new BasicRoom();
        Student owner = new Student(currentRoom);
        tr.setOwner(owner);
        tr.setIsTurnedOn(true);
        System.out.println("Test:");

        tr.place();
    }

    public void placeSecondTransistorActiveAndCanNotMoveBecauseOfCapacity() {
        System.out.println("Testing place transistor if can not move because the room do not have enough capacity:");
        System.out.println("Setup test:");
        Transistor tr = new Transistor();
        Transistor pairTransistor = new Transistor();
        BasicRoom currentRoom = new BasicRoom();
        BasicRoom ptPlaceLocation = new BasicRoom(0);
        Student owner = new Student(currentRoom);
        currentRoom.addNeighbour(ptPlaceLocation);
        tr.setIsTurnedOn(true);
        tr.setPairTransistor(pairTransistor);
        tr.setOwner(owner);
        pairTransistor.setPlaceLocation(ptPlaceLocation);
        System.out.println("Test:");

        tr.place();
    }

    public void poisonedRoomToxicatesCharacters() {
        System.out.println("Testing poisoned room toxicates characters:");
        System.out.println("Setup test:");
        PoisonedRoomDecorator poisonedRoom = new PoisonedRoomDecorator(new BasicRoom());
        Student student = new Student(poisonedRoom);
        Instructor instructor = new Instructor(poisonedRoom);
        BasicRoom currentRoom = new BasicRoom();
        currentRoom.addCharacter(student);
        currentRoom.addCharacter(instructor);
        currentRoom.addNeighbour(poisonedRoom);
        System.out.println("\nTest: Student moves in toxicatedRoom");
        poisonedRoom.acceptCharacter(student);
        System.out.println("\nTest: Instructor moves in toxicatedRoom");
        poisonedRoom.acceptCharacter(instructor);
    }

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
    }

    public void merrgeRoomsSuccess() {
        System.out.println("Testing merge rooms success:");
        System.out.println("Merging a PoisonedRoomDecorator and a CursedRoomDecorator.");
        System.out.println("Setup test:");
        Labyrinth labyrinth = new Labyrinth();
        PoisonedRoomDecorator room1 = new PoisonedRoomDecorator(new BasicRoom());
        room1.setCapacity(7);
        CursedRoomDecorator room2 = new CursedRoomDecorator(new BasicRoom());
        room2.setCapacity(5);
        room1.addNeighbour(room2);
        room2.addNeighbour(room1);
        Camembert camembert = new Camembert();
        room1.setLabyrinth(labyrinth);
        room2.setLabyrinth(labyrinth);
        room2.addItem(camembert);
        labyrinth.addRoom(room1);
        labyrinth.addRoom(room2);
        System.out.println("Test:");

        MergeRoomsVisitor visitor = new MergeRoomsVisitor(room1);
        IRoom mergedRoom =  room2.acceptMerge(visitor);

        //Print for test:
        System.out.println("Print merged room type:");
        IRoom iterator = mergedRoom;
        while(iterator != null){
            System.out.print("RoomType: " + iterator.getClass() + " | ");
            iterator = iterator.getChild();
        }
    }

    public void mergeRoomsFail() {
        System.out.println("Testing merge rooms fail, because it is not empty:");
        System.out.println("Setup test:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room1 = new BasicRoom();
        BasicRoom room2 = new BasicRoom();
        Student student = new Student(room1);
        room2.addCharacter(student);
        labyrinth.addRoom(room1);
        labyrinth.addRoom(room2);
        System.out.println("Test:");

        MergeRoomsVisitor visitor = new MergeRoomsVisitor(room1);
        room2.acceptMerge(visitor);
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
    }

    public void splitRoomSuccess() {
        System.out.println("Testing split room success:");
        System.out.println("Setup test:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        cursedRoomDecorator.setCapacity(6);
        Camembert camembert = new Camembert();
        cursedRoomDecorator.addItem(camembert);
        labyrinth.addRoom(room);
        System.out.println("Test:");

        cursedRoomDecorator.splitRoom();
    }

    public void splitRoomFail() {
        System.out.println("Testing split room fail:");
        System.out.println("Setup test:");
        Labyrinth labyrinth = new Labyrinth();
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        cursedRoomDecorator.setCapacity(6);
        Camembert camembert = new Camembert();
        cursedRoomDecorator.addItem(camembert);
        Student student = new Student(room);
        cursedRoomDecorator.addCharacter(student);
        labyrinth.addRoom(room);
        System.out.println("Test:");

        cursedRoomDecorator.splitRoom();
    }

    public void cursedRoomManagesDoors() {
        System.out.println("Testing cursed room manages doors:");
        System.out.println("Setup test:");
        BasicRoom room = new BasicRoom();
        CursedRoomDecorator cursedRoomDecorator = new CursedRoomDecorator(room);
        BasicRoom neighbour = new BasicRoom();
        cursedRoomDecorator.setNeighbours(new ArrayList<IRoom>());
        List<IRoom> neighbours = new ArrayList<>();
        neighbours.add(neighbour);
        cursedRoomDecorator.setHiddenNeighbours(neighbours);
        System.out.println("Test:");

        cursedRoomDecorator.manageDoors();
    }

    public void ragStunsInstructor() {
        System.out.println("Testing rag stuns instructor:");
        System.out.println("Setup test:");
        BasicRoom room = new BasicRoom();
        Instructor instructor = new Instructor(room);
        Rag rag = new Rag();
        Student student = new Student(room);
        student.addItem(rag);
        rag.setOwner(student);
        room.addCharacter(instructor);
        room.addCharacter(student);
        System.out.println("Test:");

        rag.stunInstructor();
    }

    public void beerProtectsStudent() {
        System.out.println("Testing beer protects student:");
        System.out.println("Setup test:");
        BasicRoom room = new BasicRoom();
        Student student = new Student(room);
        Beer beer = new Beer();
        student.addItem(beer);
        beer.setOwner(student);
        System.out.println("Test:");

        student.getCaught();
    }

    public void ragProtectsStudent() {
        System.out.println("Testing rag protects student:");
        System.out.println("Setup test:");
        BasicRoom room = new BasicRoom();
        Student student = new Student(room);
        Rag rag = new Rag();
        student.addItem(rag);
        rag.setOwner(student);
        System.out.println("Test:");

        student.getCaught();
    }
}

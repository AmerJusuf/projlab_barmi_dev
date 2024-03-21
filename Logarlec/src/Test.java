import Characters.Instructor;
import Characters.Student;
import Items.*;
import Rooms.BasicRoom;

public class Test {
    public void studentMoves() {
        System.out.println("Testing student move:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom();

        student.move(nextRoom);
    }

    public void studentCannotMoveFullCapacity() {
        System.out.println("Testing student cannot move because nextRoom is full:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom(0);

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

    public void pairTransistorsBothNotPairedYet() {}

    public void canNotPairTransistorsBecauseFirstIsPaired() {}

    public void canNotPairTransistorsBecauseSecondIsPaired() {}

    public void placeSecondTransistorAndMove() {}

    public void placeFirstTransistorActiveAndPaired() {}

    public void canNotPlaceTransistorBecauseNotActive() {}

    public void canNotPlaceTransistorBecauseNotHaveAPair() {}

    public void placeSecondTransistorActiveAndCanNotMoveBecauseOfCapacity() {}

    public void poisonedRoomToxicatesCharacters() {}

    public void mergeRooms() {}

    public void splitRoom() {}

    public void cursedRoomManagesDoors() {}

    public void ragStunsInstructor() {}

    public void beerProtectsStudent() {}

    public void ragProtectsStudent() {}
}

import Characters.Instructor;
import Characters.Student;
import Items.Camembert;
import Items.Logarlec;
import Items.TVSZ;
import Rooms.BasicRoom;

public class Test {
    public void studentMoves() {
        System.out.println("Testing student move:");
        BasicRoom currentRoom = new BasicRoom();
        Student student = new Student(currentRoom);
        BasicRoom nextRoom = new BasicRoom();
        student.move(nextRoom);
    }

    public void studentCannotMoveFullCapacity() {}

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
        instructor.kickStudent(student);
    }

    public void studentGetsCaughtButProtected() {}

    public void studentGetsCaught() {}

    public void instructorGetsCaught() {}

    public void studentGetsDisabledButProtected() {}

    public void studentGetsDisabled() {}

    public void onlyInstructorGetsDisabled() {}

    public void transistorSwitchOn() {}

    public void transistorSwitchOff() {}

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

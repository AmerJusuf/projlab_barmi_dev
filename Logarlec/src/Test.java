import Characters.Student;
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

    public void studentPicksItem() {}

    public void instructorPicksItem() {}

    public void studentCanNotPickItemFullInventory() {}

    public void instructorCanNotPickItemFullInventory() {}

    public void studentPicksLogarlec() {}

    public void instructorCanNotPickLogarlec() {}

    public void TVSZProtectsStudent() {}

    public void TVSZProtectsStudentThenExpires() {}

    public void camembertOpen() {}

    public void instructorKicksStudent() {}

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

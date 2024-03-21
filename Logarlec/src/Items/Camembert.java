package Items;

import Rooms.BasicRoom;
import Characters.Character;
import Characters.Student;
import Characters.Instructor;

public class Camembert extends Item {

    public Camembert() {
        super();
    }

    public void open() {
        System.out.println("Camembert opened | Camembert: open()");
        BasicRoom currentRoom = owner.getRoom();
        // Character characters = currentRoom.getCharacters();
        owner.disable();
        //for (Character ch : characters) {
        //    ch.disable();
        //}
    }
}

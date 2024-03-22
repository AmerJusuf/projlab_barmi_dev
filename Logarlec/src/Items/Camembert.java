package Items;

import Rooms.BasicRoom;
import Characters.Character;
import Characters.Student;
import Characters.Instructor;

import java.util.List;

public class Camembert extends Item {

    /**
     * This constructor is used to create a Camembert object.
     */
    public Camembert() {
        super();
    }

    /**
     * This method is used to open the Camembert.
     * It retrieves the current room of the owner, disables the owner,
     * and disables other characters in the same room.
     */
    public void open() {
        System.out.println("Camembert opened | Camembert: open()");
        BasicRoom currentRoom = owner.getRoom();
        List<Character> characters = currentRoom.getCharacters();
        owner.disable();
        for (Character ch : characters) {
            ch.disable();
        }
    }
}

package Items;

import Characters.Character;
import Rooms.IRoom;

import java.util.List;

public class Camembert extends Item {



    /**
     * This constructor is used to create a Camembert object.
     */
    public Camembert(){
        super();
    }


    public Camembert(boolean isFake){
        super(isFake);
    }

    @Override
    public boolean getisActive() {
        return false;
    }

    /**
     * This method is used to open the Camembert.
     * It retrieves the current room of the owner, disables the owner,
     * and disables other characters in the same room.
     */
    @Override
    public void open() {
        System.out.println("Camembert opened | Camembert: open()");

            IRoom currentRoom = owner.getRoom();
            List<Character> characters = currentRoom.getCharacters();
            owner.disable();
            for (Character ch : characters) {
                ch.disable();
            }
            owner.removeItem(this);
            removeOwner();
            controller.notifyModelChanged();
    }
}

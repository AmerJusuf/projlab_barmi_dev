package Characters;

import Items.Item;
import Rooms.IRoom;

import java.util.List;

public class Cleaner extends Character{
    /**
     * This constructor is used to create a Cleaner object.
     *
     * @param currentRoom The room where the cleaner is placed.
     */
    public Cleaner(IRoom currentRoom){
        super(currentRoom);
    }

    /**
     * This method is used to pick an item.
     * The cleaner however, can't pick up items, so it does nothing.
     *
     * @param item The item to be picked.
     */
    public void pickItem(Item item){
        System.out.println("Cleaner doesn't picks item | Cleaner: pickItem(Item item)");
    }

    /**
     * This method is used to disable (only) the instructor.
     * On Cleaner object it logs a message that the cleaner is not disabled.
     */
    public void disableInstructor(){
        System.out.println("Cleaner is NOT disabled | Cleaner: disableInstructor()");
    }

    /**
     * This method is used to get caught by the instructor.
     * However, cleaners cannot be caught, the method logs a message that the cleaner
     * is in the same room, as an instructor.
     */
    public void getCaught(){
        System.out.println("Cleaner is in the same room as an instructor | Cleaner: getCaught()");
    }

    /**
     * This method is used to move all other conscious characters ouf of the room.
     * It gets all the characters in the room and moves them out, if they are conscious.
     * The characters are moved to a neighbouring room, if it has free space, if all
     * neighbouring rooms are full, the remaining characters stay in this room.
     */
    public void moveCharacters(){
        System.out.println("Cleaner moves characters ouf of the room | Cleaner: moveCharacters()");
        List<Character> characters = currentRoom.getCharacters();
        List<IRoom> neighbours = currentRoom.getNeighbours();
        for(Character character: characters){
            if(character != this && !character.isPoisoned){
                boolean isAccepted = false;
                for(IRoom room : neighbours){
                    isAccepted = room.acceptCharacter(this);
                    if(isAccepted){
                        currentRoom.removeCharacter(character);
                        character.setRoom(room);
                        break;
                    }
                }
            }
        }
    }

    /**
     * This method is used to clean the current room from poison.
     */
    public void unToxicateRoom(){
        System.out.println("Cleaner ventilated room | Cleaner: unToxicateRoom()");
        currentRoom.unToxicate();
    }
}

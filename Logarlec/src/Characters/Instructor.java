package Characters;

import Items.Item;
import Rooms.BasicRoom;
import Rooms.IRoom;

import java.util.List;

public class Instructor extends Character{

    /**
     * This constructor is used to create an Instructor object.
     *
     * @param currentRoom The room where the instructor is placed.
     */
    public Instructor(IRoom currentRoom){
        super(currentRoom);
    }

    /**
     * This method is used to pick an item.
     * It checks if the item is not null and if the instructor has less than 5 items.
     * If the conditions are met, the item is picked by the instructor.
     *
     * @param item The item to be picked.
     */
    public void pickItem(Item item){
        System.out.println("Instructor picks item | Instructor: pickItem(Item item)");
        if(item != null && items.size() < 5){
            item.pickedByInstructor(this);
        }
    }

    /**
     * This method is used to disable (only) the instructor.
     * It logs a message that the instructor is disabled.
     */
    public void disableInstructor(){
        System.out.println("Instructor is disabled | Instructor: disableInstructor()");
        this.disable();
    }

    /**
     * This method is used to get caught by the instructor.
     * However, instructors cannot be caught, the method logs a message that two instructors
     * are in the same room.
     */
    public void getCaught(){
        System.out.println("Instructors are is same room | Instructor: getCaught()");
    }

    /**
     * This method is used to kick all students in the room.
     * It gets all the characters in the room and kicks them.
     */
    public void kickStudents(){
        System.out.println("Instructor kicks student | Instructor: kickStudent()");
        List<Character> characters = currentRoom.getCharacters();
        for(Character character: characters){
           character.getCaught();
        }
    }

}

package Characters;

import Items.Item;
import Rooms.BasicRoom;

public class Student extends Character{

    public Student(BasicRoom currentRoom){
        super(currentRoom);
    }

    public void pickItem(Item item){}

    public void disableInstructor(){}
}

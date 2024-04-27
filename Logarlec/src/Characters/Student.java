package Characters;

import Items.Item;
import Rooms.IRoom;

public class Student extends Character{

    private boolean isCaught = false;
    /**
     * This constructor is used to create a Student object.
     *
     * @param currentRoom The room where the student is placed.
     */
    public Student(IRoom currentRoom){
        super(currentRoom);
    }


    public Student(){
        super();
    }

    /**
     * This method is used to pick an item.
     * It checks if the item is not null and if the student has less than 5 items.
     * If the conditions are met, the item is picked by the student.
     *
     * @param item The item to be picked.
     */
    public void pickItem(Item item) {
        System.out.println("Student picks item | Student: pickItem(Item item)");
        if (item != null && items.size() < 5) {
            currentRoom.acceptPickByStudent(this, item);
        }
    }

    /**
     * This method is used to disable the instructor.
     * On Student object it logs a message that the student is not disabled.
     */
    public void disableInstructor(){
        System.out.println("Student NOT disabled | Student: disableInstructor()");
    }


    /**
     * This method is used to get caught by the instructor.
     * It checks if the student has an item that can protect him.
     * If the student has an item that can protect him, the method returns.
     * If the student does not have an item that can protect him, the student drops all items,
     * is removed from the current room and the labyrinth.
     */
    public void getCaught(){
        System.out.println("Student is caught | Student: getCaught()");
        isCaught = true;
    }

    /**
     * A régi getCaught logikája ide lett kiszervezve, hogy egy körben csak egyszer fusson le
     */
    public void gotCaught(){
        for(Item item: items){
            if(item.protectStudent()){
                return;
            }
        }
        this.dropAllItem();
        currentRoom.removeCharacter(this);
        currentRoom.getLabyrinth().removeStudent(this);
    }

    @Override
    public void nextRound(){
        if(isCaught){
            gotCaught();
            isCaught = false;
        }
        boolean endTurn = false;
        while(!endTurn){
            System.out.println("Students round, waiting for commands | Student: nextRound()");
            endTurn = true;
        }
        //while !move
            // pickitem
            // pickItem

            // dropitem
            // useItem
            // move -> round is over

        System.out.println("Student next round | Student: nextRound()");
    }
}

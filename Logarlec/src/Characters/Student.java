package Characters;

import Game.Labyrinth;
import Items.Item;
import Rooms.IRoom;
import View.WindowView.RoomNodeView;

public class Student extends Character{
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
        if (item != null && items.size() < CAPACITY) {
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
     * A régi getCaught logikája ide lett kiszervezve, hogy egy körben csak egyszer fusson le
     */
    public void getCaught(){
        for(Item item: items){
            if(item.protectStudent()){
                return;
            }
        }
        this.dropAllItem();
        currentRoom.removeCharacter(this);
        Labyrinth.kickedStudents.add(this);
        controller.notifyModelChanged();
    }


    @Override
    public String nextRound()  {
        System.out.println("Student next round | Student: nextRound()");
        controller.notifyModelChanged();
        if(isPoisoned){
            this.dropAllItem();
            isPoisoned = false;
            return "";
        }
        while (!moved && !isPoisoned){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        moved = false;
        System.out.println("OVER | Student: nextRound()");
        RoomNodeView.lastClickedRoom = null;
        return "";
    }

    public void setStayButtonClicked() {
        this.moved = true;
        controller.notifyModelChanged();
    }



}

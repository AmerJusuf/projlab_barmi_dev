package Items;

import Characters.Character;
import Characters.Student;
import Characters.Instructor;
import Controller.Notifiable;
import Rooms.IRoom;

public abstract class Item {
    protected Character owner;
    protected boolean isFake;
    protected Notifiable controller;

    Item(){
        this.owner = null;
        this.isFake = false;
    }

    Item(boolean isFake){
        this.owner = null;
        this.isFake = isFake;
    }

    public void setController(Notifiable controller){
        this.controller = controller;
    }

    /**
     * This method is used to indicate that the item was picked by a student.
     * It sets the owner of the item to the specified student and adds the item to the student's collection.
     *
     * @param student The student who picked the item.
     */
    public void pickedByStudent(Student student) {
        System.out.println("Item picked by student | Item: pickedByStudent(Student student)");
        this.setOwner(student);
        student.addItem(this);
        controller.notifyModelChanged();
    }

    /**
     * This method is used to indicate that the item was picked by an instructor.
     * It sets the owner of the item to the specified instructor and adds the item to the instructor's collection.
     *
     * @param instructor The instructor who picked the item.
     */
    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Item picked by instructor | Item: pickedByInstructor(Instructor instructor)");
        this.setOwner(instructor);
        instructor.addItem(this);
        controller.notifyModelChanged();
    }

    /**
     * This method is used to set the owner of the item.
     *
     * @param character The character to be set as the owner of the item.
     */
    public void setOwner(Character character) {
        System.out.println("Item owner set | Item: setOwner(Character character)");
        owner = character;
    }

    /**
     * This method is used to remove the owner of the item.
     */
    public void removeOwner() {
        System.out.println("Item owner removed | Item: removeOwner()");
        owner = null;
    }

    /**
     * This method is used to get the owner of the item.
     *
     * @return The owner of the item.
     */
    public Character getOwner(){
        return owner;
    }

    /**
     * This method is used to indicate that the item does not protect the student.
     *
     * @return false as the item does not protect the student.
     */
    public boolean protectStudent() {
        System.out.println("Item does not protect student | Item: protectStudent()");
        return false;
    }

    /**
     * This method is used to indicate that the item does not protect from poison.
     *
     * @return false as the item does not protect from poison.
     */
    public boolean protectFromPoison() {
        System.out.println("Item does not protect from poison | Item: protectFromPoison()");
        return false;
    }

    /**
     * This method is used to indicate that the item does not stun the instructor.
     *
     * @return false as the item does not stun the instructor.
     */
    public boolean stunInstructor() {
        System.out.println("Item does not stun instructor | Item: stunInstructor()");
        return false;
    }

    /** Empty method for the subclasses to override
    */
     public void decreaseRoundsLeft() {}

    /** Empty method for the subclasses to override
    */
     public void setIsActive(boolean isActive) {}

    /**
     * This method is used to drop the item.
     * The transistor overrides this method, hence it cannot be dropped while active or paired
     */
    public boolean drop() {
        System.out.println("Item will be dropped | Item: drop()");
        setIsActive(false);
        removeOwner();
        return true;
    }

    /**
     * This method is used to drop an item from the owners inventory.
     * The beer class uses this method to drop an item, while being used.
     * @param index The index of the item being dropped.
     */
    public void dropItemAt(int index){
        System.out.println("Item will be dropped | Item: dropItemAt()");
        //index számú item kiaktiválása
        owner.getItems().get(index).setIsActive(false);
        //tulajdonos index számú tárgyának szobához hozzáadása
        owner.getRoom().addItem(owner.getItems().get(index));
        //tulajdonos eltávolítása az index számú tárgyától
        owner.getItems().get(index).removeOwner();
    }

    /**
     * This method calls the round decreasing method in temporary items.
     */
    public void step(){
        System.out.println("Item steps | Item: step()");
    }

    public boolean isFake(){
        return isFake;
    }

    /**
     * This method is used to indicate that the item does not toxicate the room.
     * The airfreshener overrides this method, hence it can unToxicate the room.
     */
    public IRoom unToxicateRoom(){
        System.out.println("Item does not toxicate | Item: unToxicateRoom()");
        return null;
    }

    /**
     * This method is used to open the camembert.
     * The Camembert overrides this method
     */
    public void open(){}

    public abstract boolean getisActive();

    /**
     * This method is used to place a transistor.
     * The transistor overrides this method, hence it can be placed in a room.
     */
    public void placeTransistor(){}

}

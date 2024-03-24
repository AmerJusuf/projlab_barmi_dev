package Items;

import Characters.Character;
import Characters.Student;
import Characters.Instructor;

public abstract class Item {
    protected Character owner;

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
    public void drop() {
        System.out.println("Item will be dropped | Item: drop()");
        setIsActive(false);
        owner.getRoom().addItem(this);
        removeOwner();
    }
}

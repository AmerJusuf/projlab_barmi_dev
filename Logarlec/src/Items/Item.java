package Items;

import Characters.Student;
import Characters.Instructor;

public abstract class Item {
    protected Character owner;

    public Item() {
        owner = null;
    }

    public void pickedByStudent(Student student) {
        System.out.println("Item picked by student | Item: pickedByStudent(Student student)");
        student.addItem(this);
    }

    public void pickedByInstructor(Instructor instructor) {
        System.out.println("Item picked by instructor | Item: pickedByInstructor(Instructor instructor)");
        instructor.addItem(this);
    }

    public void setOwner(Character character) {
        System.out.println("Item owner set | Item: setOwner(Character character)");
        owner = character;
    }

    public void removeOwner() {
        System.out.println("Item owner removed | Item: removeOwner()");
        owner = null;
    }

    public boolean protctsStudent() {
        System.out.println("Item does not protect student | Item: protectStudent()");
        return false;
    }
    public boolean protectFromPoison() {
        System.out.println("Item does not protect from poison | Item: protectFromPoison()");
        return false;
    }

    public boolean stunInstructor() {
        System.out.println("Item does not stun instructor | Item: stunInstructor()");
        return false;
    }

    public void decreaseRoundsLeft() {}

    public void setIsActive(boolean isActive) {}

    public void drop() {}
}

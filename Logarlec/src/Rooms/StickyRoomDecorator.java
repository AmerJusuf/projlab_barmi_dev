package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Items.Item;

import java.util.ArrayList;

public class StickyRoomDecorator extends RoomDecorator{

    private int entries = 0;

    public StickyRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
    }

    @Override
    public IRoom acceptMerge(DecoratorHandlerVisitor visitor) {
        return visitor.visitForMerge(this);
    }

    @Override
    public IRoom acceptUnToxicate(DecoratorHandlerVisitor visitor) {
        return visitor.visitForUnToxicate(this);
    }

    @Override
    public void decorate(){
        System.out.println("Sticky room does not decorate | StickyRoomDecorator: decorate");
        decoratedRoom.decorate();
    }

    @Override
    public void acceptPickByStudent(Student st, Item item){
        if(entries < 5){
            decoratedRoom.acceptPickByStudent(st, item);
        }
        else{
            System.out.println("Item cannot be picked from stickyRoom | StickyRoomDecorator: acceptPickByStudent");
            decoratedRoom.setItems(new ArrayList<>());
        }
    }

    @Override
    public void acceptPickByInstructor(Instructor inst, Item item){
        if(entries < 5){
            decoratedRoom.acceptPickByInstructor(inst, item);
        }
        else{
            System.out.println("Item cannot be picked from stickyRoom | StickyRoomDecorator: acceptPickByInstructor");
            decoratedRoom.setItems(new ArrayList<>());
        }
    }


    @Override
    public boolean acceptCharacter(Character ch){
        entries++;
        return decoratedRoom.acceptCharacter(ch);
    }

}

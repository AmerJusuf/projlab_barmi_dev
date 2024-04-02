package Rooms;

import Characters.Instructor;
import Characters.Student;
import Items.Item;

public class StickyRoomDecorator extends RoomDecorator{

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
        System.out.println("Item cannot be picked from stickyRoom | StickyRoomDecorator: acceptPickByStudent");
    }

    @Override
    public void acceptPickByInstructor(Instructor inst, Item item){
        System.out.println("Item cannot be picked from stickyRoom | StickyRoomDecorator: acceptPickByInstructor");
    }

}

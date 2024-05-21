package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<IRoom> splitRoom(){
        List<IRoom> newRooms = new ArrayList<>();
        if(this.getCharacters().isEmpty()){
            StickyRoomDecorator newRoom = new StickyRoomDecorator(decoratedRoom);
            this.addNeighbour(newRoom);
            newRoom.addNeighbour(this);
            newRoom.setCapacity(this.getCapacity());

            //Szomszédok felének átadása az új szobának
            int halftheNeighbours = decoratedRoom.getNeighbours().size()/2;
            for(int i=0; i < halftheNeighbours; i ++){
                newRoom.addNeighbour(decoratedRoom.getNeighbours().get(i));
                this.removeNeighbour(decoratedRoom.getNeighbours().get(i));
            }
            //Itemek felének átadása az új szobának
            int halftheItems = decoratedRoom.getItems().size()/2;
            for(int i=0; i<halftheItems; i++){
                newRoom.addItem(decoratedRoom.getItems().get(i));
                this.removeItem(decoratedRoom.getItems().get(i));
            }
            getLabyrinth().addRoom(newRoom);
            newRooms.add(newRoom);
            newRooms.add(this);
            System.out.println("Room splitted succesfully | StickyRoomDecorator: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | StickyRoomDecorator: splitRoom");
            return Collections.emptyList();
        }
        return newRooms;
    }
}

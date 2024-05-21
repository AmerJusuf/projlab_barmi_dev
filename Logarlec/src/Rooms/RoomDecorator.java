package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Controller.Controller;
import Game.Labyrinth;
import Items.Item;
import View.WindowView.MainWindow;
import View.WindowView.RoomNodeView;
import com.sun.tools.javac.Main;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public abstract class RoomDecorator implements IRoom{

    IRoom decoratedRoom;

    protected RoomDecorator(IRoom decoratedRoom){
        this.decoratedRoom = decoratedRoom;
    }

    @Override
    public void addCharacter(Character ch){
        decoratedRoom.addCharacter(ch);
    }
    @Override
    public int getCapacity(){
        return decoratedRoom.getCapacity();
    }

    @Override
    public int getNumberOfCharacters(){
        return decoratedRoom.getNumberOfCharacters();
    }

    @Override
    public boolean hasPlace(){
        return decoratedRoom.hasPlace();
    }

    @Override
    public List<Character> getCharacters(){
        return decoratedRoom.getCharacters();
    }

    @Override
    public List<IRoom> getNeighbours(){
        return decoratedRoom.getNeighbours();
    }

    @Override
    public boolean isNeighbour(IRoom room){
        return decoratedRoom.isNeighbour(room);
    }

    @Override
    public Labyrinth getLabyrinth(){
        return decoratedRoom.getLabyrinth();
    }

    @Override
    public void setLabyrinth(Labyrinth labyrinth){
        decoratedRoom.setLabyrinth(labyrinth);
    }

    @Override
    public void addItem(Item it){
        decoratedRoom.addItem(it);
    }

    @Override
    public void setItems(List<Item> items){
        decoratedRoom.setItems(items);
    }

    @Override
    public void removeItem(Item it){
        decoratedRoom.removeItem(it);
    }

    @Override
    public void acceptPickByStudent(Student st, Item item){
        decoratedRoom.acceptPickByStudent(st, item);
    }

    @Override
    public void acceptPickByInstructor(Instructor inst, Item item){
        decoratedRoom.acceptPickByInstructor(inst, item);
    }

    @Override
    public boolean acceptCharacter(Character ch){
        return decoratedRoom.acceptCharacter(ch);
    }

    @Override
    public void removeCharacter(Character ch){
        decoratedRoom.removeCharacter(ch);
    }

    @Override
    public void addNeighbour(IRoom room){
        decoratedRoom.addNeighbour(room);
    }

    @Override
    public void removeNeighbour(IRoom room){
        decoratedRoom.removeNeighbour(room);
    }

    @Override
    public List<IRoom> splitRoom(){
        return decoratedRoom.splitRoom();
    }

    @Override
    public void setCapacity(int i){
        decoratedRoom.setCapacity(i);
    }

    @Override
    public void setNeighbours(List<IRoom> neighbours){
        decoratedRoom.setNeighbours(neighbours);
    }

    @Override
    public IRoom makeSticky() {
        return decoratedRoom.makeSticky();
    }

    @Override
    public List<Item> getItems(){
        return decoratedRoom.getItems();
    }

    @Override
    public IRoom getDecoratedRoom(){
        return decoratedRoom.getDecoratedRoom();
    }

    @Override
    public void cleanPoisonedRoom(){}

    @Override
    public boolean isPoisonedRoomCleaned(){return false;}

    @Override
    public IRoom getChild(){
        return decoratedRoom;
    }



    @Override
    public IRoom unToxicate(){
        // do nothing, only in poisoned
        return this;
    }

    @Override
    public IRoom mergeRooms(IRoom room) {
        if (room.getCharacters().isEmpty() && this.getCharacters().isEmpty()) {
            DecoratorHandlerVisitor visitor = new DecoratorHandlerVisitor(room);
            IRoom newMergedRoom = acceptMerge(visitor);

            visitor.handleNeighboursWhenReplacing(this, newMergedRoom);
            visitor.handleNeighboursWhenReplacing(room, newMergedRoom);

            RoomNodeView oldRoomView1 = (RoomNodeView) MainWindow.viewsByObjects.get(this);
            RoomNodeView oldRoomView2 = (RoomNodeView) MainWindow.viewsByObjects.get(room);
            RoomNodeView newRoomNodeView = new RoomNodeView(newMergedRoom, false,false,false, (oldRoomView1.getX() + oldRoomView2.getX())/2, (oldRoomView1.getY() + oldRoomView2.getY())/2, getLabyrinth().getController());
            newRoomNodeView.handleRoomTypes(oldRoomView1);
            newRoomNodeView.handleRoomTypes(oldRoomView2);

            getLabyrinth().removeRoom(room);
            getLabyrinth().removeRoom(this);
            getLabyrinth().addRoom(newMergedRoom);

            MainWindow.viewsByObjects.remove(this);
            MainWindow.viewsByObjects.remove(room);
            MainWindow.viewsByObjects.put(newMergedRoom, newRoomNodeView);

            getLabyrinth().getController().notifyModelChanged();

            RoomNodeView room1 = (RoomNodeView) MainWindow.viewsByObjects.get(this);
            RoomNodeView room2 = (RoomNodeView) MainWindow.viewsByObjects.get(room);

            room1.handleRoomTypes(room2);

            MainWindow.viewsByObjects.put(newMergedRoom, new RoomNodeView(newMergedRoom, room1.getPoisoned(), room1.getCursed(), room1.getSticky(), (room1.getX() + room2.getX()) / 2 + 100, (room1.getY() + room2.getY()) / 2, room1.getController()));
            newMergedRoom.getLabyrinth().getController().notifyModelChanged();

            return newMergedRoom; //It could be a void method, returning for test cases and prototype
        } else {
            return null;
        }
    }


}

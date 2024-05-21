package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
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
        BasicRoom basicRoom = new BasicRoom();
        basicRoom.setCapacity(this.getCapacity());
//        basicRoom.setItems(this.getItems());
//        basicRoom.setNeighbours(this.getNeighbours());
        basicRoom.setLabyrinth(this.getLabyrinth());
        for(Character ch : this.getCharacters()){
            basicRoom.addCharacter(ch);
        }
        for( Character ch : basicRoom.getCharacters()){
            ch.setRoom(basicRoom);
        }

        DecoratorHandlerVisitor visitor = new DecoratorHandlerVisitor(basicRoom);
        IRoom newUntoxicatedRoom = acceptUnToxicate(visitor);

        visitor.handleNeighboursWhenReplacing(this, newUntoxicatedRoom);
        getLabyrinth().replaceRooms(this, newUntoxicatedRoom);
        RoomNodeView roomNodeView = (RoomNodeView) MainWindow.viewsByObjects.get(this);
        roomNodeView.setPoisoned(false);

        RoomNodeView newRoomNodeView = new RoomNodeView(newUntoxicatedRoom, false ,false, false, roomNodeView.getX(), roomNodeView.getY(),  basicRoom.getLabyrinth().getController());

        newRoomNodeView.handleRoomTypes(roomNodeView);


        MainWindow.viewsByObjects.put(newUntoxicatedRoom, newRoomNodeView);
        MainWindow.viewsByObjects.remove(this);
        basicRoom.getLabyrinth().getController().notifyModelChanged();
        //basicRoom.getLabyrinth().redrawMap();
        return newUntoxicatedRoom;
    }

    @Override
    public IRoom mergeRooms(IRoom room) {
        if (room.getCharacters().isEmpty() && this.getCharacters().isEmpty()) {
            DecoratorHandlerVisitor visitor = new DecoratorHandlerVisitor(room);
            IRoom newMergedRoom = acceptMerge(visitor);

            visitor.handleNeighboursWhenReplacing(this, newMergedRoom);
            visitor.handleNeighboursWhenReplacing(room, newMergedRoom);
            getLabyrinth().replaceRooms(this, newMergedRoom);
            getLabyrinth().getRooms().remove(room);

            RoomNodeView room1 = (RoomNodeView) MainWindow.viewsByObjects.get(this);
            RoomNodeView room2 = (RoomNodeView) MainWindow.viewsByObjects.get(room);

            room1.handleRoomTypes(room2);

            MainWindow.viewsByObjects.put(newMergedRoom, new RoomNodeView(newMergedRoom, room1.getPoisoned(), room1.getCursed(), room1.getSticky(), (room1.getX() + room2.getX()) / 2, (room1.getY() + room2.getY()) / 2, room1.getController()));


            return newMergedRoom; //It could be a void method, returning for test cases and prototype
        } else {
            return null;
        }
    }


}

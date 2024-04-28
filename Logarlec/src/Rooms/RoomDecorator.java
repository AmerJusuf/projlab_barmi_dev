package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.Item;

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
    public IRoom getChild(){
        return decoratedRoom;
    }

    @Override
    public void unToxicate(){
        BasicRoom basicRoom = new BasicRoom();
        basicRoom.setCapacity(this.getCapacity());
        basicRoom.setItems(this.getItems());
        basicRoom.setNeighbours(this.getNeighbours());
        for(Character ch : this.getCharacters()){
            basicRoom.addCharacter(ch);
        }

        DecoratorHandlerVisitor visitor = new DecoratorHandlerVisitor(basicRoom);
        IRoom newUntoxicatedRoom = acceptUnToxicate(visitor);

        visitor.handleNeighboursWhenReplacing(this, newUntoxicatedRoom);
        getLabyrinth().replaceRooms(this, newUntoxicatedRoom);
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

            return newMergedRoom; //It could be a void method, returning for test cases and prototype
        } else {
            return null;
        }
    }


}

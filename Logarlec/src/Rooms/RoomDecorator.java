package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public abstract class RoomDecorator implements IRoom{

    IRoom decoratedRoom;

    public RoomDecorator(IRoom decoratedRoom){
        this.decoratedRoom = decoratedRoom;
    }

    public abstract void decorate();

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
    public void addItem(Item it){
        decoratedRoom.addItem(it);
    }

    @Override
    public void removeItem(Item it){
        decoratedRoom.removeItem(it);
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
    public void mergeRooms(IRoom room){
        decoratedRoom.mergeRooms(room);
    }

    @Override
    public void splitRoom(){
        decoratedRoom.splitRoom();
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
    public List<Item> getItems(){
        return decoratedRoom.getItems();
    }

}

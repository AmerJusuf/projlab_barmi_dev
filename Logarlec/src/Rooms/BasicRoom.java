package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

public class BasicRoom implements IRoom{

    private int capacity;
    private Labyrinth labyrinth;
    protected IRoom[] neighbours;

    protected Character[] characters;

    public boolean isNeighbour(BasicRoom nextRoom){
        System.out.println("nextRoom is neighbour: true | BasicRoom: isNeighbour()");
        return true;
        //else
        //System.out.println("nextRoom is neighbour: false | basicRoom.isNeighbour()");
    }

    public boolean acceptCharacter(Character character) {
        System.out.println("Character accepted for nextRoom | BasicRoom: acceptCharacter()");
        return true;
        //else
        // System.out.println("Character is not accepted because capacity is full | basicRoom.acceptCharacter()");
    }

    public void removeCharacter(Character character) {
        System.out.println("Character is removed from currentRoom");
    }

    @Override
    public void mergeRooms(IRoom room) {

    }


    public void splitRoom(){

    }

    public int getCapacity(){
        return 0;
    }

    public int getNumberOfCharacters(){
        return 0;
    }

    public void setNumberOfCharacters(int i){

    }

    public boolean hasPlace(){
        return true;
    }
    public java.lang.Character[] getCharacters(){
        return null;
    }

    public IRoom[] getNeighbours(){
        return null;
    }

    public boolean isNeighbour(IRoom room){
        return false;
    }

    public Labyrinth getLabyrinth(){
        return null;
    }

    public void addItem(Item it){

    }

    public void removeItem(Item it){

    }


    public int setCapacity(int i){
        return 0;
    }

    public void addNeighbour(IRoom room){

    }
    public void setNeighbour(IRoom[] room){

    }

    public void removeNeighbour(IRoom room){

    }
}

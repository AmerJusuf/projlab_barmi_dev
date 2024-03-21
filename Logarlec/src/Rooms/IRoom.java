package Rooms;

import Game.Labyrinth;
import Items.Item;

public interface IRoom {
    public default void mergeRooms(IRoom room){

    }
    public default void splitRoom(){

    }

    public default int getCapacity(){
        return 0;
    }

    public default int getNumberOfCharacters(){
        return 0;
    }

    public default void setNumberOfCharacters(int i){

    }

    public default boolean hasPlace(){
        return true;
    }
    public default Character[] getCharacters(){
        return null;
    }

    public default IRoom[] getNeighbours(){
        return null;
    }

    public default boolean isNeighbour(IRoom room){
        return false;
    }

    public default Labyrinth getLabyrinth(){
        return null;
    }

    public default void addItem(Item it){

    }

    public default void removeItem(Item it){

    }

    public default boolean acceptCharacter(Character ch){
        return true;
    }

    public default void removeCharacter(Character ch){

    }

    public default int setCapacity(int i){
        return 0;
    }

    public default void addNeighbour(IRoom room){

    }
    public default void setNeighbour(IRoom[] room){

    }

    public default void removeNeighbour(IRoom room){

    }

}

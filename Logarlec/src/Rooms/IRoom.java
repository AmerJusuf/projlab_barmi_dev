package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public interface IRoom {
    public default void mergeRooms(IRoom room){

    }
    public default void splitRoom(){

    }

    public default int getCapacity(){

        return 5;
    }

    public default int getNumberOfCharacters(){

        return 0;
    }

    public default void setNumberOfCharacters(int i){

    }

    public default boolean hasPlace(){

        return true;
    }
    public default List<Character> getCharacters(){

        return null;
    }

    public default List<IRoom> getNeighbours(){

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

    public default void addCharacter(Character character){

    }

    public default void setCapacity(int i){

    }

    public default void addNeighbour(IRoom room){

    }
    public default void setNeighbours(List<IRoom> neighbours){

    }

    public default void removeNeighbour(IRoom room){

    }

    public default List<Item> getItems(){

        return null;
    }



}

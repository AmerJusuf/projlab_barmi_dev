package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public interface IRoom {
    public default void mergeRooms(IRoom room){
        System.out.println("|IRoom: mergeRooms");
    }
    public default void splitRoom(){
        System.out.println("|IRoom: splitRoom");
    }

    public default int getCapacity(){
        System.out.println("|IRoom: getCapacity");
        return 5;
    }

    public default int getNumberOfCharacters(){
        System.out.println("|IRoom: getNumberOfCharacters");
        return 0;
    }

    public default void setNumberOfCharacters(int i){
        System.out.println("|IRoom: setNumberOfCharacters");
    }

    public default boolean hasPlace(){
        System.out.println("|IRoom: hasPlace");
        return true;
    }
    public default List<Character> getCharacters(){
        System.out.println("|IRoom: getCharacters");
        return null;
    }

    public default List<IRoom> getNeighbours(){
        System.out.println("|IRoom: getNeighbours");
        return null;
    }

    public default boolean isNeighbour(IRoom room){
        System.out.println("|IRoom: isNeighbour");
        return false;
    }

    public default Labyrinth getLabyrinth(){
        System.out.println("|IRoom: getLabyrinth");
        return null;
    }

    public default void addItem(Item it){
        System.out.println("|IRoom: addItem");
    }

    public default void removeItem(Item it){
        System.out.println("|IRoom: removeItem");
    }

    public default boolean acceptCharacter(Character ch){
        System.out.println("|IRoom: acceptCharacter");
        return true;
    }

    public default void removeCharacter(Character ch){
        System.out.println("|IRoom: removeCharacter");
    }

    public default void addCharacter(Character character){
        System.out.println("|IRoom: addCharacter");
    }

    public default void setCapacity(int i){
        System.out.println("|IRoom: setCapacity");
    }

    public default void addNeighbour(IRoom room){
        System.out.println("|IRoom: addNeighbour");
    }
    public default void setNeighbours(List<IRoom> neighbours){
        System.out.println("|IRoom: setNeighbours");
    }

    public default void removeNeighbour(IRoom room){
        System.out.println("|IRoom: removeNeighbour");
    }

    public default List<Item> getItems(){
        System.out.println("|IRoom: getItems");
        return null;
    }



}

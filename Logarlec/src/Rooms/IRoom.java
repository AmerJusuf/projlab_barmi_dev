package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public interface IRoom {
    void mergeRooms(IRoom room);
    void splitRoom();

    int getCapacity();

    int getNumberOfCharacters();

    boolean hasPlace();
     List<Character> getCharacters();

     List<IRoom> getNeighbours();

     boolean isNeighbour(IRoom room);
     Labyrinth getLabyrinth();

     void addItem(Item it);

     void removeItem(Item it);
     boolean acceptCharacter(Character ch);

     void removeCharacter(Character ch);

     void addCharacter(Character character);

     void setCapacity(int i);

     void addNeighbour(IRoom room);

     void setNeighbours(List<IRoom> neighbours);

     void removeNeighbour(IRoom room);

     List<Item> getItems();

}

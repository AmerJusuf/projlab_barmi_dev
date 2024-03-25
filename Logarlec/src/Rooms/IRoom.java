package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public interface IRoom {
    IRoom acceptMerge(MergeRoomsVisitor visitor);

    void splitRoom();

    int getCapacity();

    int getNumberOfCharacters();

    boolean hasPlace();
     List<Character> getCharacters();

     List<IRoom> getNeighbours();

     boolean isNeighbour(IRoom room);
     Labyrinth getLabyrinth();

     void setLabyrinth(Labyrinth labyrinth);

     void addItem(Item it);

     void removeItem(Item it);

     void setItems(List<Item> items);

     boolean acceptCharacter(Character ch);

     void removeCharacter(Character ch);

     void addCharacter(Character character);

     void setCapacity(int i);

     void addNeighbour(IRoom room);

     void setNeighbours(List<IRoom> neighbours);

     void removeNeighbour(IRoom room);

     List<Item> getItems();

     IRoom getDecoratedRoom();

     void decorate();

     //for testing
    IRoom getChild();
}

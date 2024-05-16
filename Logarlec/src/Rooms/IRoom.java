package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public interface IRoom {
    IRoom acceptMerge(DecoratorHandlerVisitor visitor);

    IRoom acceptUnToxicate(DecoratorHandlerVisitor visitor);

    IRoom unToxicate();

    IRoom mergeRooms(IRoom room);

    List<IRoom> splitRoom();

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

     void acceptPickByStudent(Student st, Item item);

     void acceptPickByInstructor(Instructor inst, Item item);

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

     IRoom makeSticky();

     void cleanPoisonedRoom();

     boolean isPoisonedRoomCleaned();

     //for testing
    IRoom getChild();
}

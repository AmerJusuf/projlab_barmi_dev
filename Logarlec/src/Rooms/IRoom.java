package Rooms;

import Characters.Character;
import Characters.Instructor;
import Characters.Student;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

/**
 * Interface representing a room in the game.
 */
public interface IRoom {
    /**
     * Accepts a visitor to handle the merge operation.
     *
     * @param visitor The visitor handling the merge operation.
     * @return The merged room.
     */
    IRoom acceptMerge(DecoratorHandlerVisitor visitor);
    /**
     * Accepts a visitor to handle the unToxicate operation.
     *
     * @param visitor The visitor handling the unToxicate operation.
     * @return The room after unToxicate operation.
     */
    IRoom acceptUnToxicate(DecoratorHandlerVisitor visitor);
    /**
     * UnToxicates the room.
     *
     * @return The room after unToxicate operation.
     */
    IRoom unToxicate();
    /**
     * Merges the current room with another room.
     *
     * @param room The room to merge with.
     * @return The merged room.
     */
    IRoom mergeRooms(IRoom room);
    /**
     * Splits the room into multiple rooms.
     *
     * @return A list of rooms after splitting.
     */
    List<IRoom> splitRoom();
    /**
     * Gets the capacity of the room.
     *
     * @return The capacity of the room.
     */
    int getCapacity();
    /**
     * Gets the number of characters in the room.
     *
     * @return The number of characters in the room.
     */
    int getNumberOfCharacters();
    /**
     * Checks if the room has place for more characters.
     *
     * @return true if the room has place, false otherwise.
     */
    boolean hasPlace();
    /**
     * Gets the list of characters in the room.
     *
     * @return The list of characters in the room.
     */
     List<Character> getCharacters();
    /**
     * Gets the list of neighboring rooms.
     *
     * @return The list of neighboring rooms.
     */
     List<IRoom> getNeighbours();
    /**
     * Checks if a room is a neighbor of the current room.
     *
     * @param room The room to check.
     * @return true if the room is a neighbor, false otherwise.
     */
     boolean isNeighbour(IRoom room);
    /**
     * Gets the labyrinth the room belongs to.
     *
     * @return The labyrinth the room belongs to.
     */
     Labyrinth getLabyrinth();
    /**
     * Sets the labyrinth the room belongs to.
     *
     * @param labyrinth The labyrinth to set.
     */
     void setLabyrinth(Labyrinth labyrinth);
    /**
     * Adds an item to the room.
     *
     * @param it The item to add.
     */
     void addItem(Item it);
    /**
     * Removes an item from the room.
     *
     * @param it The item to remove.
     */
     void removeItem(Item it);
    /**
     * Accepts the pick operation by a student.
     *
     * @param st The student picking the item.
     * @param item The item being picked.
     */
     void acceptPickByStudent(Student st, Item item);
    /**
     * Accepts the pick operation by an instructor.
     *
     * @param inst The instructor picking the item.
     * @param item The item being picked.
     */
     void acceptPickByInstructor(Instructor inst, Item item);
    /**
     * Sets the items in the room.
     *
     * @param items The list of items to set.
     */
     void setItems(List<Item> items);
    /**
     * Accepts a character into the room.
     *
     * @param ch The character to accept.
     * @return true if the character is accepted, false otherwise.
     */
     boolean acceptCharacter(Character ch);
    /**
     * Removes a character from the room.
     *
     * @param ch The character to remove.
     */
     void removeCharacter(Character ch);
    /**
     * Adds a character to the room.
     *
     * @param character The character to add.
     */
     void addCharacter(Character character);
    /**
     * Sets the capacity of the room.
     *
     * @param i The capacity to set.
     */
     void setCapacity(int i);
    /**
     * Adds a neighbor to the room.
     *
     * @param room The room to add as a neighbor.
     */
     void addNeighbour(IRoom room);
    /**
     * Sets the neighbors of the room.
     *
     * @param neighbours The list of neighbors to set.
     */
     void setNeighbours(List<IRoom> neighbours);
    /**
     * Removes a neighbor from the room.
     *
     * @param room The room to remove from neighbors.
     */
     void removeNeighbour(IRoom room);
    /**
     * Gets the items in the room.
     *
     * @return The list of items in the room.
     */
     List<Item> getItems();
    /**
     * Gets the decorated version of the room.
     *
     * @return The decorated room.
     */
     IRoom getDecoratedRoom();
    /**
     * Decorates the room.
     */
     void decorate();
    /**
     * Makes the room sticky.
     *
     * @return The sticky room.
     */
     IRoom makeSticky();
    /**
     * Cleans a poisoned room.
     */
     void cleanPoisonedRoom();
    /**
     * Checks if the poisoned room is cleaned.
     *
     * @return true if the poisoned room is cleaned, false otherwise.
     */
     boolean isPoisonedRoomCleaned();

    /**
     * Gets the child room for testing.
     *
     * @return The child room.
     */
    IRoom getChild();
}

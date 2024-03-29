package Characters;

import Items.Item;
import Rooms.BasicRoom;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected List<Item> items;
    protected IRoom currentRoom;
    protected boolean isPoisoned;

    public Character(IRoom currentRoom){
        this.items = new ArrayList<>();
        this.currentRoom = currentRoom;
        this.isPoisoned = false;
    }

    /** This method is used to move the character to the next room.
     * It checks if the next room is a neighbour of the current room.
     * If it is, it tries to move the character to the next room.
     * If the move is accepted, the character is removed from the current room
     *
     * @param nextRoom The room to move to.
     */
    public void move(IRoom nextRoom){
        System.out.println("Character is trying to move | Character: move(BasicRoom nextRoom)");
        if(currentRoom.isNeighbour(nextRoom)){
            boolean isAccepted = currentRoom.acceptCharacter(this);
            if(isAccepted){
                currentRoom.removeCharacter(this);
                this.setRoom(nextRoom);
            }
        }
    }

    public abstract void pickItem(Item item);

    /**
     * This method is used to add an item to the character's items list.
     *
     * @param item The item to be added to the character's collection.
     */
    public void addItem(Item item){
        System.out.println("Item added to character | Character: addItem(Item item)");
        items.add(item);
    }

    /**
     * This method is used to remove an item from the character's items list.
     *
     * @param item The item to be removed from the character's collection.
     */
    public void removeItem(Item item){
        System.out.println("Item removed from character | Character: removeItem(Item item)");
        items.remove(item);
    }

    /**
     * This method is used to get the items of the character.
     *
     * @return The items of the character.
     */
    public List<Item> getItems(){
        return items;
    }

    /**
     * This method is used to drop all the items of the character.
     */
    public void dropAllItem(){
        List<Item> itemsCopy = new ArrayList<>(items);
        for(Item item : itemsCopy){
            this.dropItem(item);
        }
    }

    /**
     * This method is used to drop an item from the character's items list.
     *
     * @param item The item to be dropped from the character's collection.
     */
    public void dropItem(Item item){
        if(items.contains(item)){
            item.drop();
            this.removeItem(item);
            System.out.println("Item removed from character's inventory | Character: dropItem()");
            currentRoom.addItem(item);
        }
    }

    /**
     * This method is used to set the room of the character.
     *
     * @param room The room to be set for the character.
     */
    public void setRoom(IRoom room){
        System.out.println("Room set for character | Character: setRoom(BasicRoom basicRoom)");
        this.currentRoom = room;
    }

    /**
     * This method is used to get the room of the character.
     *
     * @return The room of the character.
     */
    public IRoom getRoom(){
        return currentRoom;
    }

    /**
     * This method is used to check if the character is poisoned.
     *
     * @return True if the character is poisoned, false otherwise.
     */
    public void setPoisoned(boolean isPoisoned){
        this.isPoisoned = isPoisoned;
    }

    public void nextRound(){
        //TODO: Implement nextRound
        //handle the next round
        //clickevent listener?
    }

    public abstract void disableInstructor();

    /**
     * This method is used to disable the character.
     * It checks if the character has an item that can protect him.
     * If the character has an item that can protect him, the method returns.
     * If the character does not have an item that can protect him, the character drops all items,
     * set as poisoned
     */
    public void disable(){
        for(Item item : items){
           if(item.protectFromPoison()){
               return;
           }
        }
        System.out.println("Character disabled | Character: disable()");
        this.dropAllItem();
        this.setPoisoned(true);
    }


    public abstract void getCaught();
}

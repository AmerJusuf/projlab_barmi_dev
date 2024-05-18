package Characters;

import Controller.Notifiable;
import Items.Item;
import Items.Transistor;
import Rooms.IRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character {
    protected List<Item> items;
    protected IRoom currentRoom;
    protected boolean isPoisoned;
    protected Transistor transistorReadyToPair;

    Notifiable controller;

    boolean moved = false;

    public Character(IRoom currentRoom){
        this.items = new ArrayList<>();
        this.currentRoom = currentRoom;
        this.isPoisoned = false;
    }

    public Character(){
        this.items = new ArrayList<>();
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
            boolean isAccepted = nextRoom.acceptCharacter(this);
            if(isAccepted){
                currentRoom.removeCharacter(this);
                this.setRoom(nextRoom);
                moved = true;
            }
        }
        //controller.notifyModelChanged();
    }

    /** This method is used to move the character to the next room.
     * If the move is accepted, the character is removed from the current room
     *
     * @param nextRoom The room to move to.
     */
    public void moveAnywhere(IRoom nextRoom){
        System.out.println("Character is trying to move anywhere | Character: moveAnywhere(BasicRoom nextRoom)");
        boolean isAccepted = nextRoom.acceptCharacter(this);
        if (isAccepted) {
            currentRoom.removeCharacter(this);
            this.setRoom(nextRoom);
            moved = true;
        }
        //controller.notifyModelChanged();
    }

    public void setController(Notifiable controller){
        this.controller = controller;
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
        if(items.contains(item) && item.drop()) { //remove owner
                this.removeItem(item);
                System.out.println("Item removed from character's inventory | Character: dropItem()");
                currentRoom.addItem(item);
        }
        controller.notifyModelChanged();
    }

    public void dropNotBeer(Item caller){
        System.out.println("Item will be dropped not beer | Character: dropNotBeer()");
        for(Item item : items){
            if(item != caller){
                dropItem(item);
                break;
            }
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

    public abstract String nextRound() throws InterruptedException;

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
        //this.dropAllItem();
        this.setPoisoned(true);
    }


    public abstract void getCaught();

    public void gotCaught(){}

    public boolean getPoisoned(){
        if(this.isPoisoned){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method moves the character to a neighbouring room,
     * if any of them have a space for this character,
     * and if the character is not poisoned
     */
    public void moveToRandom(){
        if(isPoisoned){
            isPoisoned = false;
            return;
            }
        List<IRoom> neighbours = currentRoom.getNeighbours();
        int attempts = 0;
        while(attempts < 10){
            if(neighbours.isEmpty()){
                return;
            }
            int randomIndex = new Random().nextInt(neighbours.size());
            if(neighbours.get(randomIndex).acceptCharacter(this)){
                currentRoom.removeCharacter(this);
                this.setRoom(neighbours.get(randomIndex));
                break;
            }
            attempts++;
        }
    }

    public Transistor getTransistorReadyToPair(){
        return transistorReadyToPair;
    }

    public void setTransistorReadyToPair(Transistor t){
        transistorReadyToPair = t;
    }

}

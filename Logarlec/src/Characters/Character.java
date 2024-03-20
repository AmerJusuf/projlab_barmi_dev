package Characters;

import Items.Item;
import Rooms.BasicRoom;

import java.util.List;

public abstract class Character {
    protected List<Item> items;
    protected BasicRoom currentRoom;
    protected boolean isPoisoned;

    public Character(BasicRoom currentRoom){
        this.currentRoom = currentRoom;
    }

    public void move(BasicRoom nextRoom){
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

    public void addItem(Item item){}

    public void removeItem(Item item){}

    public List<Item> getItems(){
        return items;
    }

    public void dropAllItem(){}

    public void setRoom(BasicRoom basicRoom){
        System.out.println("Room set for character | Character: setRoom()");
        this.currentRoom = basicRoom;
    }

    public BasicRoom getRoom(){
        return currentRoom;
    }

    public void setPoisoned(boolean isPoisoned){
        this.isPoisoned = isPoisoned;
    }

    public void nextRound(){}

    public abstract void disableInstructor();

    public void disable(){}

    public void getCaught(){}
}

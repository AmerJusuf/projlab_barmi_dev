package Items;

import Rooms.BasicRoom;

//import java.util.List;
public class Transistor extends Item{
    private boolean isTurnedOn;
    protected BasicRoom currentRoom;
    public Transistor(boolean isTurnedOn) {
        super();
        this.isTurnedOn = isTurnedOn;
    }

    public void setPairTransistor(Transistor transistor){
        System.out.println(" Trying to pair transistor | Transistor: setPairTransistor(Transistor tr)");

    }

    public Transistor getPairTransistor(){
        return null;
    }

    public void setPlaceLocation(BasicRoom basicRoom){
        System.out.println(" Trying to place transistor | Transistor: setPlaceLocation(Basicroom basicroom)");
        this.currentRoom = basicRoom;
    }

    public BasicRoom getPlaceLocation() {
        return currentRoom;
    }

    public void place(){
        System.out.println(" Trying to place transistor | Transistor: place");

    }

    public void switchTr(){
        System.out.println(" Trying to switch transistor | Transistor: switch");

    }

    public boolean setIsTurnedOn(){
        return true;
    }
}

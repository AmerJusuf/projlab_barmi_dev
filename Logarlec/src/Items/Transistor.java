package Items;

import Rooms.BasicRoom;

//import java.util.List;
public class Transistor extends Item{
    private boolean isTurnedOn;
    Transistor pairTransistor;
    protected BasicRoom placeLocation;
    public Transistor() {
        super();
        isTurnedOn = false;
    }

    public void setPairTransistor(Transistor transistor){
        System.out.println(" Trying to pair transistor | Transistor: setPairTransistor(Transistor transistor)");
        pairTransistor = transistor;
    }

    public Transistor getPairTransistor(){
        return pairTransistor;
    }

    public void setPlaceLocation(BasicRoom basicRoom){
        System.out.println(" Trying to place transistor | Transistor: setPlaceLocation(Basicroom basicroom)");
        BasicRoom currentRoom =owner.getRoom();
    }

    public BasicRoom getPlaceLocation() {
        return owner.getRoom();
    }

    public void place(){
        System.out.println(" Trying to place transistor | Transistor: place()");
        if(isTurnedOn){
            if(pairTransistor.getPlaceLocation() == null) {
                setPlaceLocation(owner.getRoom());
            }
            else{

            }
        }
    }

    public void switchTransistor(){
        System.out.println("Switch transistor | Transistor: switchTransistor()");
        isTurnedOn = !isTurnedOn;
    }

    public void setIsTurnedOn(boolean isTurnedOn){
        this.isTurnedOn = isTurnedOn;
    }
}

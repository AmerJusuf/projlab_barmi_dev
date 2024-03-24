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
        System.out.println(" Trying to place transistor | Transistor: setPlaceLocation(BasicRoom basicRoom)");
        placeLocation = basicRoom;
    }

    public BasicRoom getPlaceLocation() {
        return placeLocation;
    }


    //TODO: printelesek, pl nincs parja, van parja, tranzisztor lepteti a studentet stb
    public void place(){
        System.out.println(" Trying to place transistor | Transistor: place()");
        BasicRoom pl = pairTransistor.getPlaceLocation();
        BasicRoom currRoom = owner.getRoom();
        if(pairTransistor != null) {
            if (pl != null) {
                if (pl.hasPlace() && isTurnedOn) {
                    setPlaceLocation(currRoom);
                    owner.removeItem(this);
                    owner.move(pl);
                    owner.addItem(pairTransistor);
                }
            }
            else if(isTurnedOn) {
                setPlaceLocation(currRoom);
            }
        }
    }

    public void switchTransistor() {
        System.out.println("Switch transistor | Transistor: switchTransistor()");
        isTurnedOn = !isTurnedOn;
    }

    public void setIsTurnedOn(boolean isTurnedOn){
        this.isTurnedOn = isTurnedOn;
    }

    public void pairTransistor(Transistor pair){
        if(pairTransistor != null && pair.getPairTransistor() != null){
            setPairTransistor(pair);
            pair.setPairTransistor(this);
        }
    }
}

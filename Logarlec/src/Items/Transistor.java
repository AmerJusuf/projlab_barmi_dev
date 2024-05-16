package Items;

import Rooms.BasicRoom;
import Rooms.IRoom;

public class Transistor extends Item{
    private boolean isTurnedOn;
    Transistor pairTransistor;
    protected IRoom placeLocation;

    public Transistor(boolean isFake){
        super(isFake);
        isTurnedOn = false;
        pairTransistor = null;
        owner = null;
    }

    public Transistor() {
        super();
        isTurnedOn = false;
        pairTransistor = null;
    }

    public void setPairTransistor(Transistor transistor){
        //System.out.println("Pair transistor set | Transistor: setPairTransistor(Transistor transistor)");
        pairTransistor = transistor;
    }

    public Transistor getPairTransistor(){
        return pairTransistor;
    }

    public void setPlaceLocation(IRoom room){
        //System.out.println("Transistor place location set | Transistor: setPlaceLocation(BasicRoom basicRoom)");
        placeLocation = room;
    }

    public IRoom getPlaceLocation() {
        return placeLocation;
    }

    @Override
    public void placeTransistor(){
        if(!isTurnedOn){
            //System.out.println("Transistor is not active: Cannot place | Transistor: place()");
            return;
        }
        //System.out.println("Trying to place transistor | Transistor: place()");
        if(pairTransistor != null) {
            //System.out.println("Transistor does have a pair | Transistor: place()");
            IRoom pl = pairTransistor.getPlaceLocation();
            IRoom currRoom = owner.getRoom();
            if (pl != null) {
                //System.out.println("Transistor's pair is placed in a room | Transistor: place()");
                if (pl.hasPlace()) {
                    //System.out.println("Pair transistor's room has space | Transistor: place()");
                    //System.out.println("Placing second transistor, and moving Student | Transistor: place()");
                    setPlaceLocation(currRoom);
                    owner.move(pl);
                    owner.addItem(pairTransistor);
                    owner.removeItem(this);
                } else {
                    //System.out.println("Cannot place: The room is full | Transistor: place()");

                }
            }
            else {
                owner.removeItem(this);
                setPlaceLocation(currRoom);
            }
        } else{
            //System.out.println("Cannot place: Transistor does not have a pair | Transistor: place()");
        }
        controller.notifyModelChanged();
    }

    public void switchTransistor() {
        if(isTurnedOn){
            //System.out.println("Switch transistor ON -> OFF | Transistor: switchTransistor()");
        } else {
            //System.out.println("Switch transistor OFF -> ON | Transistor: switchTransistor()");
        }
        isTurnedOn = !isTurnedOn;
        controller.notifyModelChanged();
    }

    public void setIsTurnedOn(boolean isTurnedOn){
        this.isTurnedOn = isTurnedOn;
    }

    public void pairTransistor(Transistor pair){
        if(pairTransistor == null && pair.getPairTransistor() == null){
            //System.out.println("None of them are paired yet | Transistor: pairTransistor()");
            setPairTransistor(pair);
            pair.setPairTransistor(this);
        } else{
            //System.out.println("Cannot pair: atleast one of them is already paired | Transistor: pairTransistor()");
        }
        controller.notifyModelChanged();
    }

    @Override
    public void drop(){
        if(isTurnedOn || pairTransistor != null){
            //System.out.println("Transistor is active or has Pair: Cannot drop | Transistor: drop()");
        } else{
            //System.out.println("Transistor is dropped | Transistor: drop()");
//            owner.getRoom().addItem(this);
//            removeOwner();
            setIsActive(false);
            removeOwner();
        }
        controller.notifyModelChanged();
    }

    @Override
    public boolean getisActive() {
        return isTurnedOn;
    }

}

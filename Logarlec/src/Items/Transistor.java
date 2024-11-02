package Items;

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
        System.out.println("Pair transistor set | Transistor: setPairTransistor(Transistor transistor)");
        pairTransistor = transistor;
    }

    public Transistor getPairTransistor(){
        return pairTransistor;
    }

    public void setPlaceLocation(IRoom room){
        placeLocation = room;
    }

    public IRoom getPlaceLocation() {
        return placeLocation;
    }

    @Override
    public void placeTransistor(){
        if(!isTurnedOn){
            System.out.println("Transistor is not active: Cannot place | Transistor: place()");
            return;
        }
        System.out.println("Trying to place transistor | Transistor: place()");
        if(pairTransistor != null && pairTransistor.getisActive()) {
            System.out.println("Transistor does have a pair | Transistor: place()");
            IRoom pl = pairTransistor.getPlaceLocation();
            IRoom currRoom = owner.getRoom();
            if (pl != null) {
                System.out.println("Transistor's pair is placed in a room | Transistor: place()");
                if (pl.hasPlace()) {
                    System.out.println("Pair transistor's room has space | Transistor: place()");
                    System.out.println("Placing second transistor, and moving Student | Transistor: place()");
                    setPlaceLocation(currRoom);
                    owner.removeItem(this);
                    currRoom.addItem(this);
                    owner.moveAnywhere(pl);
                    owner.addItem(pairTransistor);
                    removeOwner();
                    setIsTurnedOn(false);
                } else {
                    System.out.println("Cannot place: The room is full | Transistor: place()");

                }
            }
            else {
                owner.removeItem(this);
                setPlaceLocation(currRoom);
            }
        } else{
            System.out.println("Cannot place: Transistor does not have a pair | Transistor: place()");
        }
        controller.notifyModelChanged();
    }

    public void switchTransistor() {
        isTurnedOn = !isTurnedOn;
        controller.notifyModelChanged();
    }

    public void setIsTurnedOn(boolean isTurnedOn){
        this.isTurnedOn = isTurnedOn;
    }

    public void pairTransistor(Transistor pair){
        if(pairTransistor == null && pair.getPairTransistor() == null){
            System.out.println("None of them are paired yet | Transistor: pairTransistor()");
            setPairTransistor(pair);
            pair.setPairTransistor(this);
        } else{
            System.out.println("Cannot pair: atleast one of them is already paired | Transistor: pairTransistor()");
        }
        controller.notifyModelChanged();
    }

    public void pairAutomatically(){
        System.out.println("start | Transistor: pairAutomatically()");
        if(pairTransistor == null){
            Transistor other = owner.getTransistorReadyToPair();
            if(other != null && other != this){
                System.out.println("pairing | Transistor: pairAutomatically()");
                pairTransistor(other);
                owner.setTransistorReadyToPair(null);
            }
            else {
                System.out.println("adding to character | Transistor: pairAutomatically()");
                owner.setTransistorReadyToPair(this);
            }
        }
        controller.notifyModelChanged();
    }

    @Override
    public boolean drop(){
        if(isTurnedOn || pairTransistor != null){
            System.out.println("Transistor is active of has Pair: Cannot drop | Transistor: drop()");
            return false;
        } else{
            System.out.println("Transistor is dropped | Transistor: drop()");
            removeOwner();
            setIsActive(false);
        }
        controller.notifyModelChanged();
        return true;
    }

    @Override
    public boolean getisActive() {
        return isTurnedOn;
    }

}

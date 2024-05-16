package Items;

import Rooms.IRoom;

public class AirFreshener extends Item {
    /**
     * This constructor is used to create an AirFreshener object.
     */
    public AirFreshener() {}

    //public AirFreshener(boolean isFake) { super(isFake); }

    @Override
    public boolean getisActive() {
        return false;
    }

    /**
     * This method is used to clean the current room from poison.
     */
    @Override
    public IRoom unToxicateRoom(){
        System.out.println("AirFreshener used | AirFreshener: unToxicateRoom()");
        IRoom room = owner.getRoom().unToxicate();
        room.cleanPoisonedRoom();
        owner.removeItem(this);
        this.removeOwner();
        controller.notifyModelChanged();
        return room;
    }
}

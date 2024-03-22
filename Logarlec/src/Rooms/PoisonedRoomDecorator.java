package Rooms;

public class PoisonedRoomDecorator extends RoomDecorator{
    private IRoom[] hiddenNeighbours;

    public PoisonedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
    }
    public void manageDoors(){

    }
}

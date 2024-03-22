package Rooms;

import java.util.ArrayList;
import java.util.List;

public class CursedRoomDecorator extends RoomDecorator{

    List<IRoom> hiddenNeighbours;

    public CursedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
        hiddenNeighbours = new ArrayList<>();
    }
    public void toxicate(){

    }

    public void setHiddenNeighbours(List<IRoom> hiddenNeighbours){
        this.hiddenNeighbours = hiddenNeighbours;
    }
}

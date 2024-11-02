package Rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CursedRoomDecorator extends RoomDecorator{
    /**
     * The list, which contains the hidden adjacent rooms to the current room.
     */
    List<IRoom> hiddenNeighbours;
    List<IRoom> doorsToThisRoom;
    /**
     * The list containing the actual neighbours of the current room.
     */
    public CursedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
        hiddenNeighbours = new ArrayList<>();
        doorsToThisRoom = new ArrayList<>();
    }

    /**
     * A function, to set the hiddenNeighbours of a Cursed room.
     * @param hiddenNeighbours
     */
    public void setHiddenNeighbours(List<IRoom> hiddenNeighbours){
        this.hiddenNeighbours = hiddenNeighbours;
    }

    /**
     * Hides or connects the current neighbours of a room, based on it's state.
     */
    public void manageDoors(){
        //szomszédok megjelenítése
        if(decoratedRoom.getNeighbours().isEmpty()){
            System.out.println("Neighbours hidden currently| CursedRoomDecorator: manageDoors");
            List<IRoom> neighboursToRemove = new ArrayList<>(hiddenNeighbours);
            for (IRoom neighbour : neighboursToRemove) {
                decoratedRoom.addNeighbour(neighbour);
                hiddenNeighbours.remove(neighbour);
            }

            // Copy doorsToThisRoom to avoid ConcurrentModificationException
            List<IRoom> doorsToAddThisRoom = new ArrayList<>(doorsToThisRoom);
            for (IRoom room : doorsToAddThisRoom) {
                room.addNeighbour(this);
            }
            doorsToThisRoom = new ArrayList<>();
            System.out.println("Showing neighbours | CursedRoomDecorator: manageDoors");
        }
        //szomszédok elrejtése
        else{
            System.out.println("Neighbours shown currently | CursedRoomDecorator: manageDoors");

            List<IRoom> neighboursToAdd = new ArrayList<>(decoratedRoom.getNeighbours());
            for (IRoom neighbour : neighboursToAdd) {
                this.hiddenNeighbours.add(neighbour);
                decoratedRoom.removeNeighbour(neighbour);
            }

            List<IRoom> roomsToAdd = getRoomsThatHasDoorToThisRoom();
            for (IRoom room : roomsToAdd) {
                doorsToThisRoom.add(room);
                room.removeNeighbour(this);
            }
            System.out.println("Hiding neighbour | CursedRoomDecorator: manageDoors");
        }
        decoratedRoom.getLabyrinth().getController().notifyModelChanged();



    }

    private List<IRoom> getRoomsThatHasDoorToThisRoom(){
        List<IRoom> rooms = new ArrayList<>();
        for(IRoom room: getLabyrinth().getRooms()){
            if(room.getNeighbours().contains(this)){
                rooms.add(room);
            }
        }
        return rooms;
    }

    boolean isDoorToThisRoom(){
        for( IRoom room: getLabyrinth().getRooms()){
            if(room.getNeighbours().contains(this)){
                return true;
            }
        }
        return false;
    }

    @Override
    public IRoom acceptMerge(DecoratorHandlerVisitor visitor) {
        return visitor.visitForMerge(this);
    }

    @Override
    public IRoom acceptUnToxicate(DecoratorHandlerVisitor visitor) {
        return visitor.visitForUnToxicate(this);
    }

    /**
     * Splits the room into two, if it is possible.
     */
    @Override
    public List<IRoom> splitRoom(){
        List<IRoom> newRooms = new ArrayList<>();
        if(this.getCharacters().isEmpty()){
            CursedRoomDecorator newRoom = new CursedRoomDecorator(decoratedRoom);
            this.addNeighbour(newRoom);
            newRoom.addNeighbour(this);
            newRoom.setCapacity(this.getCapacity());

            //Szomszédok felének átadása az új szobának
            int halftheNeighbours = decoratedRoom.getNeighbours().size()/2;
            for(int i=0; i < halftheNeighbours; i ++){
                newRoom.addNeighbour(decoratedRoom.getNeighbours().get(i));
                this.removeNeighbour(decoratedRoom.getNeighbours().get(i));
            }
            //Itemek felének átadása az új szobának
            int halftheItems = decoratedRoom.getItems().size()/2;
            for(int i=0; i<halftheItems; i++){
                newRoom.addItem(decoratedRoom.getItems().get(i));
                this.removeItem(decoratedRoom.getItems().get(i));
            }
            getLabyrinth().addRoom(newRoom);
            newRooms.add(newRoom);
            newRooms.add(this);
            System.out.println("Room splitted succesfully | CursedRoomDecorator: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | CursedRoomDecorator: splitRoom");
            return Collections.emptyList();
        }
        return newRooms;
    }

    public void decorate(){
        manageDoors();
    }

}

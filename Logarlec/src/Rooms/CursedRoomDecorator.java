package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class CursedRoomDecorator extends RoomDecorator{
    /**
     * The list, which contains the hidden adjacent rooms to the current room.
     */
    List<IRoom> hiddenNeighbours;
    /**
     * The list containing the actual neighbours of the current room.
     */
    public CursedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
        hiddenNeighbours = new ArrayList<>();
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
            for(int i = 0; i < hiddenNeighbours.size(); i++){
                decoratedRoom.addNeighbour(getNeighbours().get(i));
                this.hiddenNeighbours.remove(decoratedRoom.getNeighbours().get(i));
            }
            System.out.println("Showing neighbours | CursedRoomDecorator: manageDoors");
        }
        //szomszédok elrejtése
        else{
            for(int i = 0; i < decoratedRoom.getNeighbours().size(); i++){
                this.hiddenNeighbours.add(decoratedRoom.getNeighbours().get(i));
                decoratedRoom.removeNeighbour(getNeighbours().get(i));
            }
            System.out.println("Hiding neighbour | CursedRoomDecorator: manageDoors");
        }
    }

    public void splitRoom(){
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

            System.out.println("Room splitted succesfully | CursedRoomDecorator: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | CursedRoomDecorator: splitRoom");
        }
    }

    public void decorate(){
        manageDoors();
    }
}

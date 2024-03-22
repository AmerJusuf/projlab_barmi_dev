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
    List<IRoom> neighbours;
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
        if(this.neighbours.isEmpty()){
            for(int i = 0; i < hiddenNeighbours.size(); i++){
                this.addNeighbour(neighbours.get(i));
                this.hiddenNeighbours.remove(neighbours.get(i));
            }
        }
        //szomszédok elrejtése
        else{
            for(int i = 0; i < this.neighbours.size(); i++){
                this.hiddenNeighbours.add(neighbours.get(i));
                this.removeNeighbour(neighbours.get(i));
            }
        }
    }

    /*public void splitRoom(){
        if(this.getCharacters().isEmpty()){

            CursedRoomDecorator newRoom = new CursedRoomDecorator(decoratedRoom);
            this.addNeighbour(newRoom);
            newRoom.addNeighbour(this);
            newRoom.setCapacity(this.getCapacity());

            //Szomszédok felének átadása az új szobának
            int halftheNeighbours = this.neighbours.size()/2;
            for(int i=0; i < halftheNeighbours; i ++){
                newRoom.addNeighbour(neighbours.get(i));
                this.removeNeighbour(neighbours.get(i));
            }
            //Itemek felének átadása az új szobának
            int halftheItems = this.items.size()/2;
            for(int i=0; i<halftheItems; i++){
                newRoom.addItem(items.get(i));
                this.removeItem(items.get(i));
            }
            labyrinth.addRoom(newRoom);

            System.out.println("Room splitted succesfully");
        }
        else{
            System.out.println("Can not split room, because it contains characters");
        }
    }*/
}

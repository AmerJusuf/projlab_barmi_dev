package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

public class PoisonedRoomDecorator extends RoomDecorator{

    public PoisonedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
    }


    public void toxicate(List<Character> characters){
        for(int i=0; i < characters.size(); i++){
            //nem éri el a karakterek disable függvényét még, static?
            //characters.get(i).disable();
        }
        System.out.println("Room toxicated");
    }

    /*public void splitRoom(){
        if(this.getCharacters().isEmpty()){

            PoisonedRoomDecorator newRoom = new PoisonedRoomDecorator(decoratedRoom);
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

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
            decoratedRoom.getCharacters().get(i).disable();
        }
        System.out.println("Room toxicated | PoisonedRoomDecorator: toxicate");
    }

    public void splitRoom(){
        if(this.getCharacters().isEmpty()){

            PoisonedRoomDecorator newRoom = new PoisonedRoomDecorator(decoratedRoom);
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

            System.out.println("Room splitted succesfully | PoisonedRoomDecorator: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | PoisonedRoomDecorator: splitRoom");
        }
    }
}

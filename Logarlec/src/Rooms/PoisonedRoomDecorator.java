package Rooms;

import Characters.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PoisonedRoomDecorator extends RoomDecorator{

    private boolean cleaned = false;

    @Override
    public void cleanPoisonedRoom(){
        cleaned = true;
    }

    @Override
    public boolean isPoisonedRoomCleaned(){
        return cleaned;
    }

    public PoisonedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
    }

    /**
     * Toxicate the character
     * @param ch the character to toxicate
     */
    public void toxicate(Character ch){
        System.out.println("Room toxicated | PoisonedRoomDecorator: toxicate");
        ch.disable();
    }

    /**
     * Splits the room into two rooms, the new room will be a PoisonedRoomDecorator
     */
    @Override
    public List<IRoom> splitRoom(){
        List<IRoom> newRooms = new ArrayList<>();
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
            newRooms.add(newRoom);
            newRooms.add(this);
            System.out.println("Room splitted succesfully | PoisonedRoomDecorator: splitRoom");
        }
        else{
            System.out.println("Can not split room, because it contains characters | PoisonedRoomDecorator: splitRoom");
            return Collections.emptyList();
        }
        return newRooms;
    }

    @Override
    public IRoom acceptMerge(DecoratorHandlerVisitor visitor) {
        return visitor.visitForMerge(this);
    }

    @Override
    public IRoom acceptUnToxicate(DecoratorHandlerVisitor visitor) {
//        BasicRoom basicRoom = new BasicRoom();
//        basicRoom.setCapacity(this.getCapacity());
//        basicRoom.setItems(this.getItems());
//        basicRoom.setNeighbours(this.getNeighbours());
//        for(Character ch : this.getCharacters()){
//            basicRoom.addCharacter(ch);
//
//        }
//        for( Character ch : this.getCharacters()){
//            ch.setRoom(basicRoom);
//        }


        return visitor.visitForUnToxicate(this);
    }

    /**
     * Empty, because the toxication is done when the room accepts a character
     */
    @Override
    public void decorate(){
        decoratedRoom.decorate();
    }

    @Override
    public boolean acceptCharacter(Character ch){
        boolean isAccepted = decoratedRoom.acceptCharacter(ch);
        if(isAccepted && !cleaned){
            toxicate(ch);
        }
        return isAccepted;
    }

}

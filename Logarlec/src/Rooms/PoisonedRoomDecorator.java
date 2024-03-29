package Rooms;

import Characters.Character;

public class PoisonedRoomDecorator extends RoomDecorator{
    private boolean cleaned = false;

    public PoisonedRoomDecorator(IRoom decoratedRoom){
        super(decoratedRoom);
    }

    /**
     * If this room hasn't been cleaned previously, it poisons the character
     * @param ch character to be poisoned
     */
    public void toxicate(Character ch){
        if(!cleaned){
            System.out.println("Poisoned room intoxicated character | PoisonedRoomDecorator: toxicate");
            ch.disable();
        }
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

    public IRoom acceptMerge(MergeRoomsVisitor visitor) {
        return visitor.visit(this);
    }

    /**
     * Empty, because the intoxication is done when the room accepts a character
     */
    @Override
    public void decorate(){
        decoratedRoom.decorate();
    }

    /**
     * This method sets the state of this room to cleaned, so then the poison effect won't apply
     */
    @Override
    public void unToxicate() {
        cleaned = true;
    }

    @Override
    public boolean acceptCharacter(Character ch){
        boolean isAccepted = decoratedRoom.acceptCharacter(ch);
        if(isAccepted){
            toxicate(ch);
        }
        return isAccepted;
    }

}

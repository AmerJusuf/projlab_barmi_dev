package Rooms;

import Characters.Character;

public abstract class RoomDecorator implements IRoom{

    IRoom decoratedRoom;

    public RoomDecorator(IRoom decoratedRoom){
        this.decoratedRoom = decoratedRoom;
    }

    public void addCharacter(Character ch){
        decoratedRoom.addCharacter(ch);
    }

    public void decorate(){

    }
}

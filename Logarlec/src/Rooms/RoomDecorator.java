package Rooms;

import Characters.Character;
import Game.Labyrinth;
import Items.Item;

import java.util.List;

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

package Rooms;

import Characters.Character;

public class BasicRoom implements IRoom{

    public boolean isNeighbour(BasicRoom nextRoom){
        System.out.println("nextRoom is neighbour: true | BasicRoom: isNeighbour()");
        return true;
        //else
        //System.out.println("nextRoom is neighbour: false | basicRoom.isNeighbour()");
    }

    public boolean acceptCharacter(Character character) {
        System.out.println("Character accepted for nextRoom | BasicRoom: acceptCharacter()");
        return true;
        //else
        // System.out.println("Character is not accepted because capacity is full | basicRoom.acceptCharacter()");
    }

    public void remove(Character character) {
        System.out.println("Character is removed from currentRoom");
    }
}

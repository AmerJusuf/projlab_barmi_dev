package Items;

import Characters.Character;
import Rooms.IRoom;

import java.util.List;

public class Rag extends TemporaryItem {
    public Rag() {
        super();
    }

    @Override
    public boolean protectStudent() {
        System.out.println("Rag protects student | Rag: protectStudent()");
        setIsActive(true);
        stunInstructor();
        return true;
    }

    @Override
    public boolean stunInstructor() {
        System.out.println("Rag stuns instructor | Rag: stunInstructor()");
        IRoom currentRoom = owner.getRoom();
        List<Character> characters = currentRoom.getCharacters();
        for(Character c: characters){
            c.disableInstructor();
        }
        return true;
    }
}

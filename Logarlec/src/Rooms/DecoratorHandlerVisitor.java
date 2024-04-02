package Rooms;

import Characters.Character;
import Items.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecoratorHandlerVisitor implements RoomVisitor {

    private final IRoom roomToHandle;

    public DecoratorHandlerVisitor(IRoom roomToMerge) {
        this.roomToHandle = roomToMerge;
    }

    /**
     * Visits a BasicRoom and merges the rooms.
     */
    @Override
    public IRoom visitForMerge(BasicRoom room) {
        if (!room.getCharacters().isEmpty() || !roomToHandle.getCharacters().isEmpty()) {
            System.out.println("Cannot merge: at least on room is not empty | DecoratorHandlerVisitor: visit(BasicRoom)");
            return null;
        }
        setupRoomToHandle(room);
        System.out.println("Rooms merged successfully | DecoratorHandlerVisitor: visit(BasicRoom)");
        return roomToHandle;
    }

    /**
     * Visits a CursedRoomDecorator and merges the rooms.
     */
    @Override
    public IRoom visitForMerge(CursedRoomDecorator cursedRoom) {
       return cursedRoom.decoratedRoom.acceptMerge(new DecoratorHandlerVisitor(new CursedRoomDecorator(roomToHandle)));
    }

    /**
     * Visits a PoisonedRoomDecorator and merges the rooms.
     */
    @Override
    public IRoom visitForMerge(PoisonedRoomDecorator poisonedRoom) {
       return poisonedRoom.decoratedRoom.acceptMerge(new DecoratorHandlerVisitor(new PoisonedRoomDecorator(roomToHandle)));
    }

    /**
     * Visits a StickyRoomDecorator and merges the rooms.
     */
    @Override
    public IRoom visitForMerge(StickyRoomDecorator stickyRoom) {
        return stickyRoom.decoratedRoom.acceptMerge(new DecoratorHandlerVisitor(new StickyRoomDecorator(roomToHandle)));
    }

    /**
     * Visits a BasicRoom and visits the decoratedRoom, sets up the new untoxicated room.
     */
    @Override
    public IRoom visitForUnToxicate(BasicRoom room) {
        setupRoomToHandle(room);
        System.out.println("Room untoxicated successfully | DecoratorHandlerVisitor: visit(BasicRoom)");
        return roomToHandle;
    }

    /**
     * Visits a CursedRoomDecorator and visits the decoratedRoom.
     */
    @Override
    public IRoom visitForUnToxicate(CursedRoomDecorator cursedRoom) {
        return cursedRoom.decoratedRoom.acceptUnToxicate(new DecoratorHandlerVisitor(new CursedRoomDecorator(roomToHandle)));
    }

    /**
     * Visits a PoisonedRoomDecorator and visits the decoratedRoom.
     */
    @Override
    public IRoom visitForUnToxicate(PoisonedRoomDecorator poisonedRoom) {
        return poisonedRoom.decoratedRoom.acceptUnToxicate(new DecoratorHandlerVisitor(roomToHandle)); //This will untoxicate the room (we do not crete "new PoisonedRoomDecorator(roomToHandle)")
    }

    /**
     * Visits a StickyRoomDecorator and visits the decoratedRoom.
     */
    @Override
    public IRoom visitForUnToxicate(StickyRoomDecorator stickyRoom) {
        return stickyRoom.decoratedRoom.acceptUnToxicate(new DecoratorHandlerVisitor(new StickyRoomDecorator(roomToHandle)));
    }

    /**
     * Merges the neighbours of two rooms.
     * @param neighbours1 Neighbours of the first room
     * @param neighbours2 Neighbours of the second room
     * @return Merged list of neighbours without duplicates
     */
    private List<IRoom> mergeNeighbours(List<IRoom> neighbours1, List<IRoom> neighbours2) {
        System.out.println("Merging neighbours without duplication | DecoratorHandlerVisitor: mergeNeighbours");
        Set<IRoom> combinedNeighbours = new HashSet<>(neighbours1);
        combinedNeighbours.addAll(neighbours2);
        return new ArrayList<>(combinedNeighbours);
    }

    /**
     * Sets up the room.
     * @param room The room to merge with
     */
    private void setupRoomToHandle(IRoom room){
        System.out.println("Finding max capacity | DecoratorHandlerVisitor: visit(BasicRoom)");
        int newCapacity = Math.max(room.getCapacity(), roomToHandle.getCapacity());
        List<Item> newItems = new ArrayList<>(room.getItems());
        newItems.addAll(room.getItems());
        List<IRoom> newNeighbours = mergeNeighbours(room.getNeighbours(), roomToHandle.getNeighbours());
        roomToHandle.setCapacity(newCapacity);
        roomToHandle.setItems(newItems);
        roomToHandle.setNeighbours(newNeighbours);
    }

    public void handleNeighboursWhenReplacing(IRoom oldRoom, IRoom newRoom){
        List<IRoom> neighbours = oldRoom.getNeighbours();
        for(IRoom neighbour : neighbours){
            if(neighbour.isNeighbour(oldRoom)){
                neighbour.removeNeighbour(oldRoom);
                neighbour.addNeighbour(newRoom);
            }
        }
    }
}


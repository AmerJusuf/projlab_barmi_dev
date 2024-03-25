package Rooms;

import Items.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MergeRoomsVisitor implements RoomVisitor {

    private final IRoom roomToMerge;

    public MergeRoomsVisitor(IRoom roomToMerge) {
        this.roomToMerge = roomToMerge;
    }

    /**
     * Visits a BasicRoom and merges the rooms.
     */
    @Override
    public IRoom visit(BasicRoom room) {
        if (!room.getCharacters().isEmpty() || !roomToMerge.getCharacters().isEmpty()) {
            System.out.println("Cannot merge: at least on room is not empty | MergeRoomVisitor: visit(BasicRoom)");
            return null;
        }

        // Basic merging logic
        System.out.println("Finding max capacity | MergeRoomVisitor: visit(BasicRoom)");
        int newCapacity = Math.max(room.getCapacity(), roomToMerge.getCapacity());
        List<Item> newItems = new ArrayList<>(room.getItems());
        newItems.addAll(room.getItems());
        List<IRoom> newNeighbours = mergeNeighbours(room.getNeighbours(), roomToMerge.getNeighbours());
        roomToMerge.setCapacity(newCapacity);
        roomToMerge.setItems(newItems);
        roomToMerge.setNeighbours(newNeighbours);
        System.out.println("Rooms merged successfully | MergeRoomVisitor: visit(BasicRoom)");
        return roomToMerge;
    }

    /**
     * Visits a CursedRoomDecorator and merges the rooms.
     */
    @Override
    public IRoom visit(CursedRoomDecorator cursedRoom) {
       return cursedRoom.decoratedRoom.acceptMerge(new MergeRoomsVisitor(new CursedRoomDecorator(roomToMerge)));
    }

    /**
     * Visits a PoisonedRoomDecorator and merges the rooms.
     */
    @Override
    public IRoom visit(PoisonedRoomDecorator poisonedRoom) {
       return poisonedRoom.decoratedRoom.acceptMerge(new MergeRoomsVisitor(new PoisonedRoomDecorator(roomToMerge)));
    }

    /**
     * Merges the neighbours of two rooms.
     * @param neighbours1 Neighbours of the first room
     * @param neighbours2 Neighbours of the second room
     * @return Merged list of neighbours without duplicates
     */
    private List<IRoom> mergeNeighbours(List<IRoom> neighbours1, List<IRoom> neighbours2) {
        System.out.println("Merging neighbours without duplication | MergeRoomVisitor: mergeNeighbours");
        Set<IRoom> combinedNeighbours = new HashSet<>(neighbours1);
        combinedNeighbours.addAll(neighbours2);
        return new ArrayList<>(combinedNeighbours);
    }

}


package Rooms;

public interface RoomVisitor {
    IRoom visitForMerge(BasicRoom room);
    IRoom visitForMerge(CursedRoomDecorator cursedRoom);
    IRoom visitForMerge(PoisonedRoomDecorator poisonedRoom);
    IRoom visitForMerge(StickyRoomDecorator stickyRoom);

    IRoom visitForUnToxicate(BasicRoom room);
    IRoom visitForUnToxicate(CursedRoomDecorator cursedRoom);
    IRoom visitForUnToxicate(PoisonedRoomDecorator poisonedRoom);
    IRoom visitForUnToxicate(StickyRoomDecorator stickyRoom);
}

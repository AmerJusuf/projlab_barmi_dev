package Rooms;

public interface RoomVisitor {
    IRoom visit(BasicRoom room);
    IRoom visit(CursedRoomDecorator cursedRoom);
    IRoom visit(PoisonedRoomDecorator poisonedRoom);
}

package Game;

import Rooms.IRoom;

import java.util.List;

public class Labyrinth {
    private static GameState gameState;
    private int starterNumberOfRooms;
    private List<IRoom> rooms;

    public void addRoom(IRoom room) {
        // Add room to the labyrinth
    }

    public void RemoveRoom(IRoom room) {
        // Remove room from the labyrinth
    }

    public void mergeAndSplit() {
        // Merge and split rooms
    }

    public void generateMap() {
        // Generate the map
    }

    public void startGame() {
        // Start the game
    }

    public void nextRound() {
        // Go to the next round
    }

    public void endGame() {
        // End the game
    }

    public static void setGameState(GameState state) {
        gameState = state;
    }
}

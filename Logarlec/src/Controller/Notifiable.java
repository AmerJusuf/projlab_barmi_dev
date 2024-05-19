package Controller;

/**
 * Sends a message to the game, when something changes in the model.
 */
public interface Notifiable {
    void notifyModelChanged();
}

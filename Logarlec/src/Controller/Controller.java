package Controller;


import View.WindowView.MainWindow;

/**
 * A class, which can send notifications to the game to update views, when soemthing changes in the model.
 */
public class Controller implements Notifiable {
    /**
     * The main window of the application.
     */
    MainWindow mainWindow;
    /**
     * Flag indicating whether the labyrinth is ready.
     */
    public boolean labyrinthReady = false;
    /**
     * Default constructor for the Controller class.
     */
    public Controller(){}
    /**
     * Constructor for the Controller class with a MainWindow parameter.
     *
     * @param mainWindow The main window of the application.
     */
    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    /**
     * Sets the main window of the application.
     *
     * @param mainWindow The main window of the application.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    /**
     * Notifies that the model has changed and updates all views in the main window.
     */
    @Override
    public void notifyModelChanged() {
       if (mainWindow != null) {mainWindow.updateAllViews();}
    }
}
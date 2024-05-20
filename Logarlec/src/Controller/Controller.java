package Controller;


import View.WindowView.MainWindow;

/**
 * A class, which can send notifications to the game to update views, when soemthing changes in the model.
 */
public class Controller implements Notifiable {
    MainWindow mainWindow;
    public boolean labyrinthReady = false;

    public Controller(){}

    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void notifyModelChanged() {
       if (mainWindow != null) {mainWindow.updateAllViews();}
    }
}
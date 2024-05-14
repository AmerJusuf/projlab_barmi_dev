package Controller;


import View.WindowView.MainWindow;

public class Controller implements Notifiable {
    MainWindow mainWindow;

    public Controller(){}

    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void notifyModelChanged() {
       mainWindow.updateAllViews();
    }
}
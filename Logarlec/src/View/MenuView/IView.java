package View.MenuView;

import Characters.Student;
import Items.*;
import Rooms.IRoom;
import View.ItemView.*;
import View.WindowView.PlayerView;
import View.WindowView.RoomView;

public class IView {
    public AirFreshenerView createAirFreshenerView(AirFreshener af){
        return new AirFreshenerView(af);
    }
    public BeerView createBeerView(Beer b){
        return new BeerView(b);
    }
    public CamembertView createCamembertView(Camembert camembert){
        return new CamembertView(camembert);
    }
    public FFP2View createFFP2View(FFP2 f){
        return new FFP2View(f);
    }
    public LogarlecView createLogarlecView(Logarlec log){
        return new LogarlecView(log);
    }
    public RagView createRagView(Rag r){
        return new RagView(r);
    }
    public TransistorView createTransistorView(Transistor tr){
        return new TransistorView(tr);
    }
    public TVSZView createTVSZView(TVSZ t){
        return new TVSZView(t);
    }
    public RoomView createRoomView(IRoom room){
        return new RoomView(room, 0);
    }

    public PlayerView createPlayerView(Student student){
        return new PlayerView(student);
    }
}

package Items;

public class Transistor extends Item{

    private boolean isTurnedOn;
    public Transistor() {
        super();
    }

    public void switchTransistor() {
        System.out.println("Transistor switched | Transistor: switchTransistor()");
    }

    public void setIsTurnedOn(boolean isTurnedOn) {
        this.isTurnedOn = isTurnedOn;
    }
}

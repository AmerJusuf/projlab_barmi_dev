package Items;

public abstract class TemporaryItem extends Item {
    protected int roundsLeft;
    protected boolean isActive;

    public TemporaryItem() {
        super();
        // nem tudom ide mi kellene
        roundsLeft = 3;
        isActive = false;
    }

    public void decreaseRoundsLeft() {}
}

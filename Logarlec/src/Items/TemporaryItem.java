package Items;

public abstract class TemporaryItem extends Item {
    protected int roundsLeft;
    protected boolean isActive;

    protected TemporaryItem() {
        super();
        // nem tudom ide mi kellene
        roundsLeft = 3;
        isActive = false;
    }

    @Override
    public void decreaseRoundsLeft() {
        roundsLeft--;
    }
    @Override
    public void setIsActive(boolean po){
        isActive = po;
    }
}

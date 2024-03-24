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
        System.out.println("Rounds left decreased | TemporaryItem: decreaseRoundsLeft()");
        roundsLeft--;
        if(roundsLeft == 0) {
            System.out.println("Rounds left is 0, destroying item | TemporaryItem: decreaseRoundsLeft()");
            owner.removeItem(this);
            removeOwner();
        }
    }
    @Override
    public void setIsActive(boolean po){
        isActive = po;
    }
}

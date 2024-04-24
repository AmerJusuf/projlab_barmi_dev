package Items;

public abstract class TemporaryItem extends Item {
    protected int roundsLeft;
    protected boolean isActive;

    public TemporaryItem(boolean isFake) {
        super(isFake);
        roundsLeft = 3;
        isActive = false;
    }

    protected TemporaryItem() {
        super();
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

    @Override
    public void step() {
        if(isActive){
            decreaseRoundsLeft();
            System.out.println("Item stepping | TemporaryItem: step()");
        }
    }
}

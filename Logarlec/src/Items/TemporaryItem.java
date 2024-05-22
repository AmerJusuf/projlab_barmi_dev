package Items;
/**
 * Abstract class representing a temporary item in the game.
 */
public abstract class TemporaryItem extends Item {
    /**
     * The number of rounds left for the temporary item.
     */
    protected int roundsLeft;
    /**
     * Indicates if the temporary item is active.
     */
    protected boolean isActive;
    /**
     * Constructor for the TemporaryItem class with a fake status.
     *
     * @param isFake Indicates if the item is fake.
     */
    public TemporaryItem(boolean isFake) {
        super(isFake);
        roundsLeft = 3;
        isActive = false;
    }
    /**
     * Default constructor for the TemporaryItem class.
     */
    protected TemporaryItem() {
        super();
        roundsLeft = 3;
        isActive = false;
    }
    /**
     * Decreases the number of rounds left for the temporary item.
     */
    @Override
    public void decreaseRoundsLeft() {
        System.out.println("Rounds left decreased | TemporaryItem: decreaseRoundsLeft()");
        roundsLeft--;
        if(roundsLeft == 0) {
            System.out.println("Rounds left is 0, destroying item | TemporaryItem: decreaseRoundsLeft()");
            owner.removeItem(this);
            removeOwner();
        }
        controller.notifyModelChanged();
    }
    /**
     * Sets the active status of the temporary item.
     *
     * @param po The active status to set.
     */
    @Override
    public void setIsActive(boolean po){
        isActive = po;
    }
    /**
     * Calls the round decreasing method if the temporary item is active.
     */
    @Override
    public void step() {
        if(isActive){
            decreaseRoundsLeft();
            System.out.println("Item stepping | TemporaryItem: step()");
        }
    }

    /**
     * Gets the active status of the temporary item.
     *
     * @return true if the item is active.
     */
    @Override
    public boolean getisActive(){
        return isActive;
    }
}

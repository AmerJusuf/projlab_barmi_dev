package Items;

public class Beer extends TemporaryItem {
    public Beer(boolean isFake) {
        super(isFake);
    }

    public Beer(){
        super();
    }

    @Override
    public boolean protectStudent() {
        System.out.println("Beer protects student | Beer: protectStudent()");
        setIsActive(true);
        return true;
    }

    @Override
    public void decreaseRoundsLeft() {
        System.out.println("Rounds left decreased | Beer: decreaseRoundsLeft()");
        roundsLeft--;
        dropItemAt(0);
        if(roundsLeft == 0) {
            System.out.println("Rounds left is 0, destroying item | TemporaryItem: decreaseRoundsLeft()");
            owner.removeItem(this);
            removeOwner();
        }
        controller.notifyModelChanged();
    }
}

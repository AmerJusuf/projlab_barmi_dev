package Items;

public class TVSZ extends Item {
    private int savesLeft;

    public TVSZ() {
        super();
        savesLeft = 3;
    }

    public boolean protectStudent() {
        System.out.println("TVSZ protects student | TVSZ: protectStudent()");
        decreaseSavesLeft();
        if (savesLeft == 0) {
            owner.removeItem(this);
            this.removeOwner();
        }
        return true;
    }

    public void decreaseSavesLeft() {
        System.out.println("TVSZ saves left: " + savesLeft + " | TVSZ: decreaseSavesLeft()");
        savesLeft--;
        // if savesLeft is 0 expire the item
    }
}

package Items;

public class TVSZ extends Item {
    private int savesLeft;

    public TVSZ(int savesLeft) {
        super();
        this.savesLeft = savesLeft;
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
        savesLeft--;
        System.out.println("TVSZ saves left: " + savesLeft + " | TVSZ: decreaseSavesLeft()");
    }
}

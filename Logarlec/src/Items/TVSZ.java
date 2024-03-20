package Items;

public class TVSZ extends Item {
    private int savesLeft;

    public TVSZ() {
        super();
        savesLeft = 3;
    }

    public boolean protectStudent() {
        System.out.println("TVSZ protects student | TVSZ: protectStudent()");
        return true;
    }

    public void decreaseSavesLeft() {
        System.out.println("TVSZ saves left: " + savesLeft + " | TVSZ: decreaseSavesLeft()");

        // if savesLeft is 0 expire the item
    }
}

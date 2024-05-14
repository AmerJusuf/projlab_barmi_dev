package Items;

public class TVSZ extends Item {
    private int savesLeft;

    //módosítás előtti konstruktor
    /*public TVSZ(int savesLeft) {
        super();
        this.savesLeft = savesLeft;
    }*/
    /**
     * This constructor is used to create a TVSZ object with a specified number of saves left.
     *
     * @param savesLeft The number of saves left for the TVSZ object.
     * @param fake Determines whether a tvsz object is fake or real.
     */
    public TVSZ(boolean fake, int savesLeft){
        super();
        this.savesLeft = savesLeft;
        this.isFake = fake;
    }

    /**
     * This method is used to protect the student using the TVSZ.
     * It decreases the number of saves left for the TVSZ, and if the saves run out,
     * it removes the TVSZ from its owner's items and removes the owner.
     * If the tvsz is fake, then it does not protect its owner, resulting in removing him from the game.
     * @return true if the student is protected successfully, false otherwise.
     */
    public boolean protectStudent() {
        System.out.println("TVSZ protects student | TVSZ: protectStudent()");
        if(isFake){
            return false;
        } else {
            decreaseSavesLeft();
            return true;
        }
    }

    @Override
    public boolean getisActive() {
        return false;
    }

    public int getSavesLeft() { return savesLeft; }

    /**
     * This method is used to decrease the number of saves left for the TVSZ.
     */
    private void decreaseSavesLeft() {
        savesLeft--;
        System.out.println("TVSZ saves left: " + savesLeft + " | TVSZ: decreaseSavesLeft()");
        if (savesLeft == 0) {
            System.out.println("TVSZ will be destroyed | TVSZ: decreaseSavesLeft()");
            owner.removeItem(this);
            removeOwner();
        }
        controller.notifyModelChanged();
    }

}

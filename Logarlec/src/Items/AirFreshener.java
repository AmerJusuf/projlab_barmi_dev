package Items;

public class AirFreshener extends Item {
    /**
     * This constructor is used to create an AirFreshener object.
     */
    public AirFreshener(boolean isFake){super(isFake);}

    @Override
    public boolean getisActive() {
        return false;
    }

    /**
     * This method is used to clean the current room from poison.
     */
    public void unToxicateRoom(){
        System.out.println("AirFreshener used | AirFreshener: unToxicateRoom()");
        owner.getRoom().unToxicate();
    }
}

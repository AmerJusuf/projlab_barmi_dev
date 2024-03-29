package Items;

public class AirFreshener extends Item {
    /**
     * This constructor is used to create an AirFreshener object.
     */
    public AirFreshener(){super();}

    public void unToxicateRoom(){
        System.out.println("AirFreshener used | AirFreshener: unToxicateRoom()");
        throw new UnsupportedOperationException();
    }
}

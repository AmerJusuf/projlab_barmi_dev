package Items;

public class FFP2 extends TemporaryItem {
    public FFP2(boolean isFake) {
        super(isFake);
    }

    @Override
    public boolean protectFromPoison() {
        System.out.println("FFP2 protects from poison | FFP2: protectFromPoison()");
        if(isFake){
            System.out.println("FFP2 is fake - no effect!");
            return false;
        }
        else {
            setIsActive(true);
            return true;
        }
    }
}

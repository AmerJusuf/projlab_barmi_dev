package Items;

public class FFP2 extends TemporaryItem {
    public FFP2() {
        super();
    }

    @Override
    public boolean protectFromPoison() {
        System.out.println("FFP2 protects from poison | FFP2: protectFromPoison()");
        setIsActive(true);
        return true;
    }
}

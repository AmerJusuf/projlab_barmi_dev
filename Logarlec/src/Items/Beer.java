package Items;

public class Beer extends TemporaryItem {
    public Beer() {
        super();
    }

    @Override
    public boolean protectStudent() {
        System.out.println("Beer protects student | Beer: protectStudent()");
        setIsActive(true);
        dropItemAt(1);
        return true;
    }
}

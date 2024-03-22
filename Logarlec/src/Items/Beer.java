package Items;

public class Beer extends TemporaryItem {
    public Beer() {
        super();
    }

    public boolean protectStudent() {
        System.out.println("Beer protects student | Beer: protectStudent()");
        return true;
    }
}

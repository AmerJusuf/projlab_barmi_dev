package Items;

public class Rag extends TemporaryItem {
    public Rag() {
        super();
    }

    public boolean protectStudent() {
        System.out.println("Rag protects student | Rag: protectStudent()");
        return true;
    }

    public boolean stunInstructor() {
        System.out.println("Rag stuns instructor | Rag: stunInstructor()");
        return true;
    }
}

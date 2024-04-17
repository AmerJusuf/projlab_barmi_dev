package Items;

public class FFP2 extends TemporaryItem {
    public FFP2(boolean fake) {
        super();
        isFake = fake;
    }

    @Override
    public boolean protectFromPoison() {
        System.out.println("FFP2 protects from poison | FFP2: protectFromPoison()");
        if(isFake){
            System.out.println("FFP2 is fake - no effect!");
            for(int i = 0; i < owner.getItems().size(); i++){
                //tárgyak inaktiválása
                owner.getItems().get(i).setIsActive(false);
                //tárgyak tulajdonosának kivétele
                owner.removeItem(owner.getItems().get(i));
                //tulajdonos tárgyainak kivétele
                owner.getItems().get(i).removeOwner();
                //szobába leteszi a tárgyait a tulajdonos
                owner.getRoom().addItem(owner.getItems().get(i));
            }
            //megmérgezzük a tulajdonost
            owner.setPoisoned(true);
            return false;
        }
        else {
            setIsActive(true);
            return true;
        }
    }
}

import java.util.ArrayList;

public class Carpenter extends Job {

    public Carpenter(){
        super();

        needs = new ItemType[]{ItemType.WOOD, ItemType.KNIFE, ItemType.AXE, ItemType.HAMMER};
        produces = new ItemType[]{ItemType.CART, ItemType.BOW, ItemType.CUTTINGBOARD, ItemType.ARROW};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Arrow", 0));
        inven.add(1, ItemBuilder.newItem("Wood", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

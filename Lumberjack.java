import java.util.ArrayList;

public class Lumberjack extends Job {

    public Lumberjack(){
        super();

        needs = new ItemType[]{ItemType.AXE, ItemType.CART};
        produces = new ItemType[]{ItemType.WOOD};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Wood", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

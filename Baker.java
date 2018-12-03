import java.util.ArrayList;

public class Baker extends Job {

    public Baker(){
        super();

        needs = new ItemType[]{ItemType.WHEAT, ItemType.KNIFE, ItemType.CUTTINGBOARD};
        produces = new ItemType[]{ItemType.BREAD};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Wheat", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

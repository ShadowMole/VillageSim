import java.util.ArrayList;

public class Butcher extends Job {

    public Butcher(){
        super();

        needs = new ItemType[]{ItemType.RAWMEAT, ItemType.KNIFE, ItemType.CUTTINGBOARD};
        produces = new ItemType[]{ItemType.MEAT};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Raw Meat", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

import java.util.ArrayList;

public class Hunter extends Job {

    public Hunter(){
        super();

        needs = new ItemType[]{ItemType.BOW, ItemType.ARROW, ItemType.PACK, ItemType.KNIFE};
        produces = new ItemType[]{ItemType.RAWMEAT, ItemType.SKIN};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Arrow", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

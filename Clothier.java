import java.util.ArrayList;

public class Clothier extends Job {

    public Clothier(){
        super();

        needs = new ItemType[]{ItemType.WOOL, ItemType.SKIN, ItemType.KNIFE};
        produces = new ItemType[]{ItemType.CLOTHES, ItemType.STRING, ItemType.PACK};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){

    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("String", 0));
        inven.add(1, ItemBuilder.newItem("Wool", 0));
        inven.add(2, ItemBuilder.newItem("Skin", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

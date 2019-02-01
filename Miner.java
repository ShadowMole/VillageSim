import java.util.ArrayList;

public class Miner extends Job {

    public Miner(){
        super();

        needs = new ItemType[]{ItemType.PICKAXE, ItemType.CART};
        produces = new ItemType[]{ItemType.METAL};
    }

    @Override
    public int work(ArrayList<Item> inven, int intel, int food){
        return food;
    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Metal", 0));
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }
}

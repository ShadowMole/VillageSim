import java.util.ArrayList;

public class Blacksmith extends Job {

    public Blacksmith(){
        super();

        needs = new ItemType[]{ItemType.HAMMER, ItemType.METAL};
        produces = new ItemType[]{ItemType.BUCKET, ItemType.AXE, ItemType.PICKAXE, ItemType.SHOVEL, ItemType.SHEARS, ItemType.KNIFE, ItemType.PLOW, ItemType.HAMMER};
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

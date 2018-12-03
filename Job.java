import java.util.ArrayList;
public abstract class Job {
    protected ItemType[] produces;
    protected ItemType[] needs;
    
    public Job() {

    }
    public void work(ArrayList<Item> inven, int intel){ }

    public ItemType[] getProduces(){
        return produces;
    }

    public ItemType[] getNeeds() {
        return needs;
    }

    public void initialInventory(ArrayList<Item> inven){

    }

    public void firstInventory(ArrayList<Item> inven){

    }
}

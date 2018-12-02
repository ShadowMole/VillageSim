import java.util.ArrayList;
public abstract class Job {
    protected String name;
    protected ItemType[] produces;
    protected ItemType[] needs;
    protected ArrayList<Item> inventory;
    protected int totalFood;
    
    public Job(){
        inventory = new ArrayList<>();
    }

    public void initialInventory(){}

    public void initialFirstInventory(){};

    public String getName(){
        return name;
    }

    public void work(){ }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public ItemType[] getProduces(){
        return produces;
    }

    public ItemType[] getNeeds() {
        return needs;
    }

    public void firstFood(){

    }
}

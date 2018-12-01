public abstract class Job {
    private String name;
    private ArrayList<ItemType> produces;
    private ArrayList<ItemType> needs;
    private ArrayList<Item> inventory;

    public String getName(){
        return name;
    }

    public void work(){ }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public ArrayList<ItemType> getProduces(){
        return produces;
    }

    public ArrayList<ItemType> getNeeds() {
        return needs;
    }
}

public abstract class Item {
    protected String name;
    protected int price;

    public Item(String s, int p){
        name = s;
        price = p;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}

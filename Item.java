public class Item {
    private String name;
    private int number;
    private int price;

    public Item(String s, int n){
        name = s;
        number = n;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){
        return number;
    }

    public int getPrice(){
        return price;
    }
}

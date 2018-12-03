public class Tool extends Item {

    private int durability;

    public Tool(String s, int p, int d){
        super(s, p);

        durability = d;
    }

    public int getDurability(){
        return durability;
    }

    public void use(){
        durability--;
    }
}

public class Consumable extends Item {

    private int number;

    public Consumable(String s, int p, int n){
        super(s, p);

        number = n;
    }

    public int getNumber(){
        return number;
    }
}

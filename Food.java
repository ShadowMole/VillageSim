/**
 * Created by Mole on 12/2/2018.
 */
public class Food extends Item {

    private int food;

    public Food(String s, int p, int f){
        super(s, p);

        food = f;
    }

    public int getFood(){
        return food;
    }

    public void eat(){
        food--;
    }
}

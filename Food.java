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

    public int eat(int eat){
        int check = eat;
        for(int i = 0; i < eat; i++){
            food--;
            check--;
            if(food == 0){
                return check;
            }
        }
        return 0;
    }
}

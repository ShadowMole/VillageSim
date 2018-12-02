import java.util.Random;
public class Randomizer {
    public static Random rgen = new Random();

    public static int getRandom(int i){
        return rgen.nextInt(i);
    }
}

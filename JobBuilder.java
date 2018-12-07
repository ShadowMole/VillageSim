import java.util.ArrayList;

public class JobBuilder {

    public static Job newWorker(ArrayList<Item> inven){
        int job = Randomizer.getRandom(2);
        Job j;
        switch (job){
            case 0:
                j = new Farmer();
                break;
            default:
                j = new Hunter();
                break;
        }
        j.initialInventory(inven);
        return j;
    }

    public static Job firstWorker(ArrayList<Item> inven){
        int job = Randomizer.getRandom(2);
        Job j;
        switch (job){
            case 0:
                j = new Farmer();
                break;
            default:
                j = new Hunter();
                break;
        }
        j.initialInventory(inven);
        return j;
    }
}

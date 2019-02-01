import java.util.ArrayList;

public class JobBuilder {

    public static Job newWorker(ArrayList<Item> inven){
        int job = Randomizer.getRandom(4);
        Job j;
        switch (job){
            case 0:
                j = new Farmer();
                Driver.updateJobs("Farmer");
                break;
            case 1:
                j = new Hunter();
                Driver.updateJobs("Hunter");
                break;
            case 2:
                j = new Butcher();
                Driver.updateJobs("Butcher");
                break;
            default:
                    j = new Baker();
                    Driver.updateJobs("Baker");
                    break;
        }
        //Job j = new Farmer();
        j.initialInventory(inven);
        return j;
    }

    public static Job firstWorker(ArrayList<Item> inven){
        int job = Randomizer.getRandom(3);
        Job j;
        switch (job){
            case 0:
                j = new Farmer();
                break;
            case 1:
                j = new Hunter();
                break;
            default:
                j = new Butcher();
                break;
        }
        //Job j = new Farmer();
        j.initialInventory(inven);
        return j;
    }
}

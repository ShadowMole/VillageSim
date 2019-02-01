import java.util.ArrayList;
import java.util.Iterator;

public class Baker extends Job {

    private boolean hasWheat;
    private int wheat;

    private boolean hasKnife;
    private int knives;

    private boolean hasBoard;
    private int boards;

    public Baker(){
        super();

        needs = new ItemType[]{ItemType.WHEAT, ItemType.KNIFE, ItemType.CUTTINGBOARD};
        produces = new ItemType[]{ItemType.BREAD};
    }

    @Override
    public int work(ArrayList<Item> inven, int intel, int food){
        wheat = ((Consumable) inven.get(0)).getNumber();
        if(wheat < 10){
            hasWheat = false;
        }else{
            hasWheat = true;
        }
        for(int j = 0; j < intel; j++){
            if(hasBoard && hasKnife && hasWheat){
                inven.add(ItemBuilder.newItem("Bread", 0));
                bread++;
                food += 10;
                ((Consumable) inven.get(0)).use(10);
                wheat = ((Consumable) inven.get(0)).getNumber();
                if(wheat < 10){
                    hasWheat = false;
                }else{
                    hasWheat = true;
                }
                boolean found1 = false;
                boolean found2 = false;
                boolean found = false;
                Iterator<Item> it = inven.iterator();
                while(!found && it.hasNext()){
                    Item i = it.next();
                    if(!found1 && i instanceof Tool && i.getName().equals("Knife")){
                        ((Tool) i).use();
                        found1 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            knives--;
                            if(knives > 0){
                                hasKnife = true;
                            }else{
                                hasKnife = false;
                            }
                        }
                    }else if(!found2 && i instanceof Tool && i.getName().equals("Cutting Board")){
                        ((Tool) i).use();
                        found2 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            boards--;
                            if(boards > 0){
                                hasBoard = true;
                            }else{
                                hasBoard = false;
                            }
                        }
                    }
                    if(found1 && found2){
                        found = true;
                    }
                }
            }
        }
        return food;
    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Wheat", 0));
        inven.add(ItemBuilder.newItem("Knife", 0));
        inven.add(ItemBuilder.newItem("Knife", 0));
        inven.add(ItemBuilder.newItem("Knife", 0));
        knives += 3;
        hasKnife = true;
        inven.add(ItemBuilder.newItem("Cutting Board", 0));
        inven.add(ItemBuilder.newItem("Cutting Board", 0));
        inven.add(ItemBuilder.newItem("Cutting Board", 0));
        boards += 3;
        hasBoard = true;
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }

    public String toString(){
        String s = "Job: Baker\n";
        s += "Knives: " + knives + ", Cutting Boards: " + boards;
        s += "\nMilk: " + milk + ", Carrots: " + carrot + ", Potatoes: " + potato + ", Meat: " + meat + ", Bread: " + bread;
        s += "\nWheat: " + wheat;
        return s;
    }
}

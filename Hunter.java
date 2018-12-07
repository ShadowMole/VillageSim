import java.util.ArrayList;
import java.util.Iterator;

public class Hunter extends Job {

    private boolean hasPack;
    private int packs;

    private boolean hasBow;
    private int bows;

    private boolean hasKnife;
    private int knives;

    private boolean hasRawMeat;
    private int rawMeat;

    private boolean hasSkins;
    private int skins;

    private boolean hasArrows;
    private int arrows;

    public Hunter(){
        super();

        needs = new ItemType[]{ItemType.BOW, ItemType.ARROW, ItemType.PACK, ItemType.KNIFE};
        produces = new ItemType[]{ItemType.RAWMEAT, ItemType.SKIN};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){
        for(int i = 0; i < intel; i++) {
            int work = Randomizer.getRandom(30);
            if (work == 0) {
                huntDeer(inven);
            } else {
                huntRabbits(inven);
            }
        }
    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Arrow", 100));
        arrows = 100;
        hasArrows = true;
        inven.add(1, ItemBuilder.newItem("Raw Meat", 0));
        inven.add(2, ItemBuilder.newItem("Skin", 0));
        inven.add(ItemBuilder.newItem("Bow", 0));
        inven.add(ItemBuilder.newItem("Bow", 0));
        bows += 2;
        hasBow = true;
        inven.add(ItemBuilder.newItem("Knife", 0));
        inven.add(ItemBuilder.newItem("Knife", 0));
        knives += 2;
        hasKnife = true;
        inven.add(ItemBuilder.newItem("Pack", 0));
        packs++;
        hasPack = true;
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }

    public void huntDeer(ArrayList<Item> inven){
        if(hasPack && hasBow && hasArrows){
            if(Randomizer.getRandom(5) == 0){
                ((Consumable) inven.get(1)).addMore(60);
                ((Consumable) inven.get(0)).use(1);
                if(((Consumable) inven.get(0)).getNumber() == 0){
                    hasArrows = false;
                }
                boolean found1 = false;
                boolean found2 = false;
                boolean found = false;
                Iterator<Item> it = inven.iterator();
                while(!found && it.hasNext()){
                    Item i = it.next();
                    if(!found1 && i instanceof Tool && i.getName().equals("Pack")){
                        ((Tool) i).use();
                        found1 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            packs--;
                            if(packs> 0){
                                hasPack = true;
                            }else{
                                hasPack = false;
                            }
                        }
                    }else if(!found2 && i instanceof Tool && i.getName().equals("Bow")){
                        ((Tool) i).use();
                        found2 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            bows--;
                            if(bows > 0){
                                hasBow = true;
                            }else{
                                hasBow = false;
                            }
                        }
                    }
                    if(found1 && found2){
                        found = true;
                    }
                }
                if(hasKnife){
                    ((Consumable) inven.get(2)).addMore(20);
                    skins = ((Consumable) inven.get(2)).getNumber();
                    boolean found3 = false;
                    Iterator<Item> it1 = inven.iterator();
                    while(!found && it1.hasNext()){
                        Item i = it1.next();
                        if(i instanceof Tool && i.getName().equals("Knife")){
                            ((Tool) i).use();
                            found3 = true;
                            if(((Tool) i).getDurability() == 0){
                                it.remove();
                                knives--;
                                if(knives > 0){
                                    hasKnife = true;
                                }else{
                                    hasKnife = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void huntRabbits(ArrayList<Item> inven){
        if(hasPack && hasBow && hasArrows){
            if(Randomizer.getRandom(5) == 0){
                ((Consumable) inven.get(1)).addMore(1);
                rawMeat = ((Consumable) inven.get(1)).getNumber();
                ((Consumable) inven.get(0)).use(1);
                arrows = ((Consumable) inven.get(0)).getNumber();
                if(((Consumable) inven.get(0)).getNumber() == 0){
                    hasArrows = false;
                }
                boolean found1 = false;
                boolean found2 = false;
                boolean found = false;
                Iterator<Item> it = inven.iterator();
                while(!found && it.hasNext()){
                    Item i = it.next();
                    if(!found1 && i instanceof Tool && i.getName().equals("Pack")){
                        ((Tool) i).use();
                        found1 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            packs--;
                            if(packs> 0){
                                hasPack = true;
                            }else{
                                hasPack = false;
                            }
                        }
                    }else if(!found2 && i instanceof Tool && i.getName().equals("Bow")){
                        ((Tool) i).use();
                        found2 = true;
                        if(((Tool) i).getDurability() == 0){
                            it.remove();
                            bows--;
                            if(bows > 0){
                                hasBow = true;
                            }else{
                                hasBow = false;
                            }
                        }
                    }
                    if(found1 && found2){
                        found = true;
                    }
                }
                if(hasKnife){
                    ((Consumable) inven.get(2)).addMore(1);
                    skins = ((Consumable) inven.get(2)).getNumber();
                    boolean found3 = false;
                    Iterator<Item> it1 = inven.iterator();
                    while(!found && it1.hasNext()){
                        Item i = it1.next();
                        if(i instanceof Tool && i.getName().equals("Knife")){
                            ((Tool) i).use();
                            found3 = true;
                            if(((Tool) i).getDurability() == 0){
                                it.remove();
                                knives--;
                                if(knives > 0){
                                    hasKnife = true;
                                }else{
                                    hasKnife = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String toString(){
        String s = "Job: Hunter\n";
        s += "Bows: " + bows + ", Knives: " + knives + ", Packs: " + packs + ", Arrows: " + arrows;
        s += "\nMilk: " + milk + ", Carrots: " + carrot + ", Potatoes: " + potato + ", Meat: " + meat;
        s += "\nRaw Meat: " + rawMeat + ", Skins: " + skins;
        return s;
    }
}

import java.util.ArrayList;
import java.util.Iterator;

public class Farmer extends Job {

    private boolean hasShovel;
    private boolean hasPlow;
    private boolean hasBucket;
    private boolean hasShears;
    private boolean hasCart;

    private boolean hasSheep;
    private boolean hasCows;


    private boolean hasWheat;
    private boolean hasRawMeat;

    private boolean hasWool;


    private int cows;
    private int sheep;
    private int pigs;

    private int shears;
    private int buckets;
    private int rawMeat;
    private int wool;
    private int wheat;
    private int plow;
    private int shovel;

    private int cart;

    public Farmer(){
        super();

        needs = new ItemType[]{ItemType.SHOVEL, ItemType.PLOW, ItemType.BUCKET, ItemType.SHEARS, ItemType.CART};
        produces = new ItemType[]{ItemType.CARROT, ItemType.POTATO, ItemType.WHEAT, ItemType.RAWMEAT, ItemType.WOOL, ItemType.MILK};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){
        for(int i = 0; i < inven.size(); i++){
            Item j = inven.get(i);
            if(j instanceof Animal){
                ((Animal) j).live();
                if(((Animal) j).getLife() == 0 || Randomizer.getRandom(10000) == 0){
                    ((Consumable) inven.get(0)).addMore(((Animal) j).getFood());
                    rawMeat = ((Consumable) inven.get(0)).getNumber();
                    inven.remove(j);
                    i--;
                    switch (j.getName()){
                        case "Cow":
                            cows--;
                            break;
                        case "Sheep":
                            sheep--;
                            break;
                        case "Pig":
                            pigs--;
                            break;
                    }
                }else if(((Animal) j).getAge() > 360 && ((Animal) j).getIsPregnant() && ((Animal) j).getPregnant() == 0){
                    ((Animal) j).notPregnant();
                    for(int k = 0; k < ((Animal) j).getBirths(); k++){
                        inven.add(ItemBuilder.newItem(j.getName(), 0));
                        switch (j.getName()){
                            case "Cow":
                                cows++;
                                break;
                            case "Sheep":
                                sheep++;
                                break;
                            case "Pig":
                                pigs++;
                                break;
                        }
                    }
                }else if(((Animal) j).getAge() > 360 && !((Animal) j).getIsPregnant() && Randomizer.getRandom(((Animal) j).getBirthRate()) == 0){
                    ((Animal) j).setPregnant();
                }
            }
        }
        if(cows > 0){
            hasCows = true;
        }else{
            hasCows = false;
        }

        if(sheep > 0){
            hasSheep = true;
        }else{
            hasSheep = false;
        }

        for(int j = 0; j < intel; j++){
            int work = Randomizer.getRandom(5);
            switch (work){
                case 0:
                    milkCow(inven);
                    break;
                case 1:
                    shearSheep(inven);
                    break;
                case 2:
                    growCarrots(inven);
                    break;
                case 3:
                    growPotatoes(inven);
                    break;
                case 4:
                    growWheat(inven);
                    break;
            }
        }
    }

    @Override
    public void initialInventory(ArrayList<Item> inven){
        inven.add(0, ItemBuilder.newItem("Raw Meat", 0));
        inven.add(1, ItemBuilder.newItem("Wheat", 0));
        inven.add(2, ItemBuilder.newItem("Wool", 0));
        inven.add(ItemBuilder.newItem("Cow", 0));
        cows++;
        inven.add(ItemBuilder.newItem("Sheep", 0));
        sheep++;
        //inven.add(ItemBuilder.newItem("Pig", 0));
        inven.add(ItemBuilder.newItem("Shears", 0));
        inven.add(ItemBuilder.newItem("Shears", 0));
        shears += 2;
        inven.add(ItemBuilder.newItem("Bucket", 0));
        inven.add(ItemBuilder.newItem("Bucket", 0));
        buckets += 2;
        hasBucket = true;
        hasShears = true;
        inven.add(ItemBuilder.newItem("Plow", 0));
        inven.add(ItemBuilder.newItem("Shovel", 0));
        inven.add(ItemBuilder.newItem("Shovel", 0));
        inven.add(ItemBuilder.newItem("Plow", 0));
        shovel += 2;
        plow += 2;
        hasPlow = true;
        hasShovel = true;
        inven.add(ItemBuilder.newItem("Cart", 0));
        cart += 1;
        hasCart = true;
    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }

    public boolean milkCow(ArrayList<Item> inven){
        if(hasCows && hasBucket && Randomizer.getRandom(7) == 0){
            for(int i = 0; i < cows; i++){
                inven.add(ItemBuilder.newItem("Milk", 0));
                milk++;
            }
            boolean found = false;
            Iterator<Item> it = inven.iterator();
            while(!found && it.hasNext()){
                Item i = it.next();
                if(i instanceof Tool && i.getName().equals("Bucket")){
                    ((Tool) i).use();
                    found = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        buckets--;
                        if(buckets > 0){
                           hasBucket = true;
                        }else{
                           hasBucket = false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean shearSheep(ArrayList<Item> inven){
        if(hasSheep && hasShears && Randomizer.getRandom(30) == 0){
            ((Consumable) inven.get(2)).addMore(sheep);
            wool = ((Consumable) inven.get(2)).getNumber();
            boolean found = false;
            Iterator<Item> it = inven.iterator();
            while(!found && it.hasNext()){
                Item i = it.next();
                if(i instanceof Tool && i.getName().equals("Shears")){
                    ((Tool) i).use();
                    found = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        shears--;
                        if(shears > 0){
                            hasShears = true;
                        }else{
                            hasShears = false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void growCarrots(ArrayList<Item> inven){
        if(hasPlow && hasCart && hasShovel && Randomizer.getRandom(60) == 0){
            for(int i = 0; i < 40; i++){
                inven.add(ItemBuilder.newItem("Carrot", 0));
                carrot++;
            }
            boolean found1 = false;
            boolean found2 = false;
            boolean found3 = false;
            boolean found = false;
            Iterator<Item> it = inven.iterator();
            while(!found && it.hasNext()){
                Item i = it.next();
                if(!found1 && i instanceof Tool && i.getName().equals("Cart")){
                    ((Tool) i).use();
                    found1 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        cart--;
                        if(cart> 0){
                            hasCart = true;
                        }else{
                            hasCart = false;
                        }
                    }
                }else if(!found2 && i instanceof Tool && i.getName().equals("Plow")){
                    ((Tool) i).use();
                    found2 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        plow--;
                        if(plow > 0){
                            hasPlow = true;
                        }else{
                            hasPlow = false;
                        }
                    }
                }else if(!found3 && i instanceof Tool && i.getName().equals("Shovel")){
                    ((Tool) i).use();
                    found3 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        shovel--;
                        if(shovel > 0){
                            hasShovel = true;
                        }else{
                            hasShovel = false;
                        }
                    }
                }
                if(found1 && found2 && found3){
                    found = true;
                }
            }
        }
    }

    public void growPotatoes(ArrayList<Item> inven){
        if(hasPlow && hasCart && hasShovel && Randomizer.getRandom(90) == 0){
            for(int i = 0; i < 20; i++){
                inven.add(ItemBuilder.newItem("Potato", 0));
                potato++;
            }
            boolean found1 = false;
            boolean found2 = false;
            boolean found3 = false;
            boolean found = false;
            Iterator<Item> it = inven.iterator();
            while(!found && it.hasNext()){
                Item i = it.next();
                if(!found1 && i instanceof Tool && i.getName().equals("Cart")){
                    ((Tool) i).use();
                    found1 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        cart--;
                        if(cart> 0){
                            hasCart = true;
                        }else{
                            hasCart = false;
                        }
                    }
                }else if(!found2 && i instanceof Tool && i.getName().equals("Plow")){
                    ((Tool) i).use();
                    found2 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        plow--;
                        if(plow > 0){
                            hasPlow = true;
                        }else{
                            hasPlow = false;
                        }
                    }
                }else if(!found3 && i instanceof Tool && i.getName().equals("Shovel")){
                    ((Tool) i).use();
                    found3 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        shovel--;
                        if(shovel > 0){
                            hasShovel = true;
                        }else{
                            hasShovel = false;
                        }
                    }
                }
                if(found1 && found2 && found3){
                    found = true;
                }
            }
        }
    }

    public void growWheat(ArrayList<Item> inven){
        if(hasPlow && hasCart && Randomizer.getRandom(180) == 0){
            ((Consumable) inven.get(1)).addMore(60);
            wheat = ((Consumable) inven.get(1)).getNumber();
            boolean found1 = false;
            boolean found2 = false;
            boolean found = false;
            Iterator<Item> it = inven.iterator();
            while(!found && it.hasNext()){
                Item i = it.next();
                if(!found1 && i instanceof Tool && i.getName().equals("Cart")){
                    ((Tool) i).use();
                    found1 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        cart--;
                        if(cart> 0){
                            hasCart = true;
                        }else{
                            hasCart = false;
                        }
                    }
                }else if(!found2 && i instanceof Tool && i.getName().equals("Plow")){
                    ((Tool) i).use();
                    found2 = true;
                    if(((Tool) i).getDurability() == 0){
                        it.remove();
                        plow--;
                        if(plow > 0){
                            hasPlow = true;
                        }else{
                            hasPlow = false;
                        }
                    }
                }
                if(found1 && found2){
                    found = true;
                }
            }
        }
    }

    @Override
    public String toString(){
        String s = "Job: Farmer\n";
        s += "Cows: " + cows + ", Sheep: " + sheep + ", Pigs: " + pigs;
        s += "\nShears: " + shears + ", Buckets: " + buckets + ", Plows: " + plow + ", Shovels: " + shovel + ", Carts: " + cart;
        s += "\nMilk: " + milk + ", Carrots: " + carrot + ", Potatoes: " + potato + ", Meat: " + meat;
        s += "\nRaw Meat: " + rawMeat + ", Wool: " + wool + ", Wheat: " + wheat;
        return s;
    }

    public boolean isHasShovel() {
        return hasShovel;
    }

    public void setHasShovel(boolean hasShovel) {
        this.hasShovel = hasShovel;
    }

    public boolean isHasPlow() {
        return hasPlow;
    }

    public void setHasPlow(boolean hasPlow) {
        this.hasPlow = hasPlow;
    }

    public boolean isHasBucket() {
        return hasBucket;
    }

    public void setHasBucket(boolean hasBucket) {
        this.hasBucket = hasBucket;
    }

    public boolean isHasShears() {
        return hasShears;
    }

    public void setHasShears(boolean hasShears) {
        this.hasShears = hasShears;
    }

    public boolean isHasCart() {
        return hasCart;
    }

    public void setHasCart(boolean hasCart) {
        this.hasCart = hasCart;
    }

    public boolean isHasSheep() {
        return hasSheep;
    }

    public void setHasSheep(boolean hasSheep) {
        this.hasSheep = hasSheep;
    }

    public boolean isHasCows() {
        return hasCows;
    }

    public void setHasCows(boolean hasCows) {
        this.hasCows = hasCows;
    }

    public boolean isHasWheat() {
        return hasWheat;
    }

    public void setHasWheat(boolean hasWheat) {
        this.hasWheat = hasWheat;
    }

    public boolean isHasWool() {
        return hasWool;
    }

    public void setHasWool(boolean hasWool) {
        this.hasWool = hasWool;
    }

    public boolean isHasRawMeat() {
        return hasRawMeat;
    }

    public void setHasRawMeat(boolean hasRawMeat) {
        this.hasRawMeat = hasRawMeat;
    }
}
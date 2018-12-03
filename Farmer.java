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

    private boolean hasCarrots;
    private boolean hasPotatoes;
    private boolean hasWheat;
    private boolean hasMilk;
    private boolean hasWool;
    private boolean hasMeat;

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

    public boolean isHasCarrots() {
        return hasCarrots;
    }

    public void setHasCarrots(boolean hasCarrots) {
        this.hasCarrots = hasCarrots;
    }

    public boolean isHasPotatoes() {
        return hasPotatoes;
    }

    public void setHasPotatoes(boolean hasPotatoes) {
        this.hasPotatoes = hasPotatoes;
    }

    public boolean isHasWheat() {
        return hasWheat;
    }

    public void setHasWheat(boolean hasWheat) {
        this.hasWheat = hasWheat;
    }

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public boolean isHasWool() {
        return hasWool;
    }

    public void setHasWool(boolean hasWool) {
        this.hasWool = hasWool;
    }

    public boolean isHasMeat() {
        return hasMeat;
    }

    public void setHasMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

    public Farmer(){
        super();

        needs = new ItemType[]{ItemType.SHOVEL, ItemType.PLOW, ItemType.BUCKET, ItemType.SHEARS, ItemType.CART};
        produces = new ItemType[]{ItemType.CARROT, ItemType.POTATO, ItemType.WHEAT, ItemType.RAWMEAT, ItemType.WOOL, ItemType.MILK};
    }

    @Override
    public void work(ArrayList<Item> inven, int intel){
        Iterator<Item> i = inven.iterator();
        while(i.hasNext()){
            Item j = i.next();
            if(j instanceof Animal){
                ((Animal) j).live();
                if(((Animal) j).getLife() == 0){
                    boolean found = false;
                    for(int k = 0; !found && k < inven.size(); k++){
                        if(inven.get(k) instanceof Consumable && inven.get(k).getName().equals("Raw Meat")) {
                            ((Consumable) inven.get(k)).addMore(((Animal) j).getFood());
                            found = true;
                        }
                    }
                    i.remove();;
                }else if(Randomizer.getRandom(((Animal) j).getBirthRate()) == 0){
                    for(int k = 0; k < ((Animal) j).getBirths(); k++){
                        inven.add(ItemBuilder.newItem(j.getName(), 0));
                    }
                }
            }
        }
        int cows = 0;
        int sheep = 0;
        for(int j = 0; j < inven.size(); j++){
            if(inven.get(j) instanceof Animal){
                if(inven.get(j).getName().equals("Cow")){
                    cows++;
                }else if(inven.get(j).getName().equals("Sheep)")){
                    sheep++;
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

    }

    @Override
    public void firstInventory(ArrayList<Item> inven){

    }

    public void milkCow(ArrayList<Item> inven){
        if(hasCows && hasBucket && Randomizer.getRandom(7) == 0){
            for(int i = 0; i < inven.size(); i++){
                
            }
        }
    }

    public void shearSheep(ArrayList<Item> inven){

    }

    public void growCarrots(ArrayList<Item> inven){

    }

    public void growPotatoes(ArrayList<Item> inven){

    }

    public void growWheat(ArrayList<Item> inven){

    }
}

import java.util.ArrayList;
public abstract class Job {
    protected ItemType[] produces;
    protected ItemType[] needs;

    protected int milk;
    protected int carrot;
    protected int potato;
    protected int meat;

    protected boolean hasCarrots;
    protected boolean hasPotatoes;
    protected boolean hasMilk;
    protected boolean hasMeat;
    
    public Job() {

    }
    public void work(ArrayList<Item> inven, int intel){ }

    public ItemType[] getProduces(){
        return produces;
    }

    public ItemType[] getNeeds() {
        return needs;
    }

    public void initialInventory(ArrayList<Item> inven){

    }

    public void firstInventory(ArrayList<Item> inven){

    }

    public void eatMilk(){
        milk--;
    }

    public void eatCarrot(){
        carrot--;
    }

    public void eatPotato(){
        potato--;
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

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public boolean isHasMeat() {
        return hasMeat;
    }

    public void setHasMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

    public String toString(){
        return "";
    }
}

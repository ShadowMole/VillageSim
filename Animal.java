public class Animal extends Item{

    private int life;
    private int births;
    private int birthRate;
    private int food;
    private int age;
    private boolean isPregnant;
    private int pregnant;

    public Animal(String s, int p, int l, int b, int br, int f){
        super(s, p);
        life = l;
        births = b;
        birthRate = br;
        food = f;

    }

    public int getBirthRate() {
        return birthRate;
    }

    public int getBirths() {
        return births;
    }

    public int getLife() {
        return life;
    }

    public int getFood() {
        return food;
    }

    public void live(){
        life--;
        age++;
        if(isPregnant){
            pregnant--;
        }
    }

    public int getAge(){
        return age;
    }

    public int getPregnant(){
        return pregnant;
    }

    public boolean getIsPregnant(){
        return isPregnant;
    }

    public void setPregnant(){
        pregnant = birthRate / 4;
        isPregnant = true;
    }

    public void notPregnant(){
        isPregnant = false;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Person {

    private String firstName;
    private String lastName;

    private Gender gender;

    private int age;
    private int wealth;

    private Person mother;
    private Person father;

    private Person spouse;
    private ArrayList<Person> children;
    private ArrayList<Person> siblings;

    private HashMap<Person, Integer> relationships;

    private boolean alive;

    private boolean isPregnant;
    private int pregnancy;
    private boolean married;
    private int birthday;

    private String skin;
    private String hairC;
    private String hairS;
    private String eye;

    private SkinColor[] skinAlleles;
    private HairColor[] hairColorAlleles;
    private HairStyle[] hairStyleAlleles;
    private EyeColor[] eyeAlleles;

    public Person(String fN, String lN, Gender g, int a, int w, Person m, Person f, ArrayList<Person> s, int b, SkinColor[] sc, HairStyle[] hs, HairColor[] hc, EyeColor[] ec) {
        firstName = fN;
        lastName = lN;
        gender = g;
        age = a;
        wealth = w;
        mother = m;
        father = f;
        siblings = s;
        children = new ArrayList<>();
        relationships = new HashMap<>();
        alive = true;
        married = false;
        birthday = b;
        skinAlleles = sc;
        hairColorAlleles = hc;
        hairStyleAlleles = hs;
        eyeAlleles = ec;
        if ((skinAlleles[0] == SkinColor.WHITE && skinAlleles[1] == SkinColor.WHITE)
                || (skinAlleles[0] == SkinColor.WHITE && skinAlleles[1] == SkinColor.TAN)
                || (skinAlleles[0] == SkinColor.TAN && skinAlleles[1] == SkinColor.WHITE)) {
            skin = "White";
        } else if ((skinAlleles[0] == SkinColor.BLACK && skinAlleles[1] == SkinColor.WHITE)
                || (skinAlleles[0] == SkinColor.WHITE && skinAlleles[1] == SkinColor.BLACK)
                || (skinAlleles[0] == SkinColor.TAN && skinAlleles[1] == SkinColor.BLACK)
                || (skinAlleles[0] == SkinColor.BLACK && skinAlleles[1] == SkinColor.TAN)
                || (skinAlleles[0] == SkinColor.BLACK && skinAlleles[1] == SkinColor.BLACK)) {
            skin = "Black";
        } else {
            skin = "Tan";
        }

        if((hairColorAlleles[0] == HairColor.BLACK && hairColorAlleles[1] == HairColor.BLACK)
                || (hairColorAlleles[0] == HairColor.BLACK && hairColorAlleles[1] == HairColor.RED)
                || (hairColorAlleles[0] == HairColor.RED && hairColorAlleles[1] == HairColor.BLACK)){
            hairC = "Black";
        }else if((hairColorAlleles[0] == HairColor.RED && hairColorAlleles[1] == HairColor.RED)) {
            hairC = "Red";
        }else if((hairColorAlleles[0] == HairColor.RED && hairColorAlleles[1] == HairColor.BROWN)
                || (hairColorAlleles[0] == HairColor.BROWN && hairColorAlleles[1] == HairColor.RED)){
            hairC = "Ginger";
        }else if((hairColorAlleles[0] == HairColor.BROWN && hairColorAlleles[1] == HairColor.BROWN)
                || (hairColorAlleles[0] == HairColor.BLACK && hairColorAlleles[1] == HairColor.BROWN)
                || (hairColorAlleles[0] == HairColor.BROWN && hairColorAlleles[1] == HairColor.BLACK)) {
            hairC = "Brown";
        }else if((hairColorAlleles[0] == HairColor.BROWN && hairColorAlleles[1] == HairColor.BLOND)
                || (hairColorAlleles[0] == HairColor.BLOND && hairColorAlleles[1] == HairColor.BROWN)) {
            hairC = "Light Brown";
        }else if((hairColorAlleles[0] == HairColor.BLACK && hairColorAlleles[1] == HairColor.BLOND)
                || (hairColorAlleles[0] == HairColor.BLOND && hairColorAlleles[1] == HairColor.BLACK)) {
            hairC = "Dirty Blond";
        }else{
            hairC = "Blond";
        }

        if(hairStyleAlleles[0] == HairStyle.STRAIGHT && hairStyleAlleles[1] == HairStyle.STRAIGHT){;
            hairS = "Straight";
        }else if((hairStyleAlleles[0] == HairStyle.CURLY && hairStyleAlleles[1] == HairStyle.STRAIGHT)
                || (hairStyleAlleles[0] == HairStyle.STRAIGHT && hairStyleAlleles[1] == HairStyle.CURLY)
                || (hairStyleAlleles[0] == HairStyle.CURLY && hairStyleAlleles[1] == HairStyle.CURLY)){
            hairS = "Curly";
        }else{
            hairS = "Wavy";
        }

        if(eyeAlleles[0] == EyeColor.GREEN && eyeAlleles[1] == EyeColor.GREEN){
            eye = "Green";
        }else if((eyeAlleles[0] == EyeColor.BLUE && eyeAlleles[1] == EyeColor.BLUE)
                || (eyeAlleles[0] == EyeColor.BLUE && eyeAlleles[1] == EyeColor.GREEN)
                || (eyeAlleles[0] == EyeColor.GREEN && eyeAlleles[1] == EyeColor.BLUE)){
            eye = "Blue";
        }else if((eyeAlleles[0] == EyeColor.HAZEL && eyeAlleles[1] == EyeColor.HAZEL)
                || (eyeAlleles[0] == EyeColor.HAZEL && eyeAlleles[1] == EyeColor.GREEN)
                || (eyeAlleles[0] == EyeColor.GREEN && eyeAlleles[1] == EyeColor.HAZEL)
                || (eyeAlleles[0] == EyeColor.HAZEL && eyeAlleles[1] == EyeColor.BLUE)
                || (eyeAlleles[0] == EyeColor.BLUE && eyeAlleles[1] == EyeColor.HAZEL)){
            eye = "Hazel";
        }else{
            eye = "Brown";
        }
        String genderS;
        if(gender == Gender.FEMALE){
            genderS = "Female";
        }else{
            genderS = "Male";
        }
        Driver.updateStatistics(genderS, skin, hairC, hairS, eye);
    }

    public void act(ArrayList<Person> others, int counter){
        if((counter - birthday) % 360 == 0){
            age++;
        }

        Random rng = new Random();
        if(gender == Gender.FEMALE){
            if(spouse != null && spouse.isAlive()){
                if(!isPregnant){
                    if((age < 50 || spouse.getAge() < 50) && rng.nextInt(1500) < 1){
                        pregnancy = 270;
                        isPregnant = true;
                    }
                }
            }
            if(isPregnant){
                if(pregnancy == 0){
                    others.add(PersonBuilder.newPerson(this, spouse, counter));
                    if(rng.nextInt(88) < 1){
                        others.add(PersonBuilder.newPerson(this, spouse, counter));
                    }
                    isPregnant = false;
                }else{
                    pregnancy--;
                }
            }
        }
        if(age >= 16){
            int interactWith = rng.nextInt(20) + 5;
            for(int i = 0; i < interactWith; i++){
                Person interact = others.get(rng.nextInt(others.size()));
                if(!interact.equals(this)){
                    int change;
                    if(isFamily(interact)){
                        int value = rng.nextInt(10);
                        if(value < 3){
                            change = 10;
                        }else if(value < 8){
                            change = 5;
                        }else{
                            change = -5;
                        }
                    }else{
                        if(interact.getAge() < 16){
                            int value = rng.nextInt(10);
                            if(value < 2){
                                change = -10;
                            }else if(value < 5){
                                change = -5;
                            }else if(value < 8){
                                change = 5;
                            }else{
                                change = 10;
                            }
                        }
                        if(otherGender(interact)){
                            int value = rng.nextInt(20);
                            if(value < 5){
                                change = 20;
                            }else if(value < 10){
                                change = 5;
                            }else if(value < 13){
                                change = 10;
                            }else if(value < 17){
                                change = -5;
                            }else{
                                change = -25;
                            }
                        }else{
                            int value = rng.nextInt(10);
                            if(value < 2){
                                change = -5;
                            }else if(value < 4){
                                change = -10;
                            }else if(value < 7){
                                change = 5;
                            }else{
                                change = 10;
                            }
                        }
                    }
                    if(relationships.containsKey(interact)){
                        relationships.replace(interact, relationships.get(interact), relationships.get(interact) + change);
                    }else{
                        relationships.put(interact, 500 + change);
                    }
                    interact.setRelationship(this, change);
                    if(!married && !(interact.isMarried()) && otherGender(interact) && interact.getAge() >= 16 && relationships.get(interact) > 950 && !(isFamily(interact)) && rng.nextInt(1000) < relationships.get(interact)){
                        married = true;
                        spouse = interact;
                        interact.setMarried(this);
                    }
                    if(relationships.get(interact) < 50 && rng.nextInt(5000) < relationships.get(interact)){
                        if(rng.nextInt(3) == 0){
                            setDead();
                            for(Person p : relationships.keySet()){
                                if(relationships.get(p) > 700){
                                    interact.setRelationship(p, -300);
                                }
                            }
                        }else{
                            interact.setDead();
                            for(Person p : interact.getRelationships().keySet()){
                                if(interact.getRelationships().get(p) > 700){
                                    setRelationship(p, -300);
                                }
                            }
                        }
                    }
                }
            }
        }else if(age > 5){
            int interactWith = rng.nextInt(10) + 3;
            for(int i = 0; i < interactWith; i++){
                Person p = others.get(rng.nextInt(others.size()));
            }
        }else{

        }
        if(rng.nextInt(500000) < age){
            alive = false;
        }
    }

    public boolean isFamily(Person p){
        if(married && p.equals(spouse)){
            return true;
        }
        if(mother != null) {
            if (p.equals(mother)) {
                return true;
            }
            for (int i = 0; i < mother.getSiblings().size(); i++) {
                if (p.equals(mother.getSiblings().get(i))) {
                    return true;
                }
                if (mother.getSiblings().get(i).getSpouse() != null && p.equals(mother.getSiblings().get(i).getSpouse())) {
                    return true;
                }
                for (int j = 0; j < mother.getSiblings().get(i).getChildren().size(); j++) {
                    if (p.equals(mother.getSiblings().get(i).getChildren().get(j))) {
                        return true;
                    }
                }
                if (mother.getMother() != null && p.equals(mother.getMother())) {
                    return true;
                }
                if (mother.getFather() != null && p.equals(mother.getFather())) {
                    return true;
                }
            }
        }
        if(father != null) {
            if (p.equals(father)) {
                return true;
            }
            for (int i = 0; i < father.getSiblings().size(); i++) {
                if (p.equals(father.getSiblings().get(i))) {
                    return true;
                }
                if (father.getSiblings().get(i).getSpouse() != null && p.equals(father.getSiblings().get(i).getSpouse())) {
                    return true;
                }
                for (int j = 0; j < father.getSiblings().get(i).getChildren().size(); j++) {
                    if (p.equals(father.getSiblings().get(i).getChildren().get(j))) {
                        return true;
                    }
                }
                if (father.getMother() != null && p.equals(father.getMother())) {
                    return true;
                }
                if (father.getFather() != null && p.equals(father.getFather())) {
                    return true;
                }
            }
        }
        for(int i = 0; i < children.size(); i++){
            if(p.equals(children.get(i))){
                return true;
            }
            for(int j = 0; j < children.get(i).getChildren().size(); j++){
                if(p.equals(children.get(i).getChildren().get(j))){
                    return true;
                }
            }
        }
        for(int i = 0; i < siblings.size(); i++){
            if(p.equals(siblings.get(i))){
                return true;
            }
        }
        return false;
    }

    public void setRelationship(Person p, int change){
        if(relationships.containsKey(p)){
            relationships.replace(p, relationships.get(p), relationships.get(p) + change);
        }else{
            relationships.put(p, 500 + change);
        }
    }

    public void setMarried(Person p){
        married = true;
        spouse = p;
    }

    public boolean isMarried() {
        return married;
    }

    public int getBirthday(){
        return birthday;
    }

    public void setPregnant(){
        isPregnant = true;
        pregnancy = 270;
    }

    public boolean otherGender(Person p){
        return gender != p.getGender();
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public boolean isAlive() {
        return alive;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getWealth() {
        return wealth;
    }

    public Person getMother() {
        return mother;
    }

    public Person getFather() {
        return father;
    }

    public Person getSpouse() {
        return spouse;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public ArrayList<Person> getSiblings() {
        return siblings;
    }

    public HashMap<Person, Integer> getRelationships() {
        return relationships;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void addSibling(Person p){
        siblings.add(p);
    }

    public void addChild(Person p){
        children.add(p);
    }

    public void setDead(){
        alive = false;
    }

    public HairStyle getRandomHairStyles(){
        Random rng = new Random();
        return hairStyleAlleles[rng.nextInt(2)];
    }

    public HairColor getRandomHairColors(){
        Random rng = new Random();
        return hairColorAlleles[rng.nextInt(2)];
    }

    public EyeColor getRandomEyeColors(){
        Random rng = new Random();
        return eyeAlleles[rng.nextInt(2)];
    }

    public SkinColor getRandomSkinColors(){
        Random rng = new Random();
        return skinAlleles[rng.nextInt(2)];
    }

    public String toString(){
        String s = "";
        s += firstName +  " " + lastName + ", Age: " + age + ", Gender: ";
        if(gender == Gender.MALE){
            s += "Male";
        }else{
            s += "Female";
        }
        s += "\n";
        s += "Hair: " + hairS + " " + hairC + ", Eyes: " + eye + ", Skin: " + skin;
        s += "\n";
        if(married){
            s += "Married to " + spouse.getFirstName() + " " + spouse.getLastName();
            if(spouse.isAlive()){
                s += "\n";
            }else{
                s += "(D)\n";
            }
        }
        if(mother != null){
            if(gender == Gender.FEMALE){
                s += "Daughter of ";
            }else{
                s += "Son of ";
            }
            s += mother.getFirstName() + " " + mother.getLastName();
            if(!mother.isAlive())   {
                s += "(D)";
            }
            s += " and " + father.getFirstName() + " " + father.getLastName();
            if(!father.isAlive()){
                s += "(D)";
            }
            s += "\n";
        }
        if(siblings.size() > 0){
            if(gender == Gender.FEMALE){
                s += "Sister of ";
            }else{
                s += "Brother of ";
            }
            for(int i = 0; i < siblings.size(); i++){
                s += siblings.get(i).getFirstName() + " " + siblings.get(i).getLastName();
                if(!siblings.get(i).isAlive()){
                    s += "(D)";
                }
                if(i != siblings.size() - 1){
                    s += ", ";
                }
            }
            s += "\n";
        }
        if(children.size() > 0){
            if(gender == Gender.FEMALE){
                s += "Mother of ";
            }else{
                s += "Father of ";
            }
            for(int i = 0; i < children.size(); i++){
                s += children.get(i).getFirstName() + " " + children.get(i).getLastName();
                if(!children.get(i).isAlive()){
                    s += "(D)";
                }
                if(i != children.size() - 1){
                    s += ", ";
                }
            }
            s += "\n";
        }
        s += "Wealth: " + wealth;
        return s;
    }

    public boolean equals(Person p){
        if(firstName.equals(p.getFirstName()) && lastName.equals(p.getLastName()) && age == p.getAge() && wealth == p.getWealth() && gender == p.getGender() && alive == p.isAlive() && married == p.isMarried()){
            return true;
        }
        return false;
    }
}
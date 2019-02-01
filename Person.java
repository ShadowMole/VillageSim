import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    private ArrayList<Item> inventory;

    protected int totalFood;

    private Job job;

    private int health;

    private int competence;
    private int paranoid;

    public Person(String fN, String lN, Gender g, int a, int w, Person m, Person f, ArrayList<Person> s, int b, SkinColor[] sc, HairStyle[] hs, HairColor[] hc, EyeColor[] ec, int hlth, int intel, int para) {
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

        inventory = new ArrayList<>();
        totalFood = 0;

        health = hlth;

        competence = intel;
        paranoid = para;

        Driver.updateStatistics(genderS, skin, hairC, hairS, eye);
    }

    public void act(ArrayList<Person> others, int counter, ArrayList<String> events){
        if((counter - birthday) % 360 == 0){
            age++;
            if(age == 16){
                pickJob();
            }
        }
        if(age >= 16){
            if(counter % 7 == 0 && wealth > children.size() + 50){
                for(int i = 0; i < children.size(); i++){
                    if(children.get(i).isAlive() && children.get(i).getAge() < 16){
                        children.get(i).sellItem(1);
                        buyItem(1);
                    }
                }
            }
            if(counter % 1800 == 0){
                sellItem(100);
            }
            totalFood = job.work(inventory, competence, totalFood);
            eat();

            if(gender == Gender.FEMALE){
                if(spouse != null && spouse.isAlive()){
                    if(!isPregnant){
                        if((age < 50 || spouse.getAge() < 50) && Randomizer.getRandom(1500) < 1){
                            pregnancy = 270;
                            isPregnant = true;
                        }
                    }
                }
                if(isPregnant){
                    if(pregnancy == 0){
                        others.add(PersonBuilder.newPerson(this, spouse, counter, events));
                        if(Randomizer.getRandom(88) < 1){
                            others.add(PersonBuilder.newPerson(this, spouse, counter, events));
                        }
                        isPregnant = false;
                    }else{
                        pregnancy--;
                    }
                }
            }
            int interactWith = Randomizer.getRandom(20) + 5;
            for(int i = 0; i < interactWith; i++){
                Person interact = others.get(Randomizer.getRandom(others.size()));
                if(!interact.equals(this)){
                    int change = 0;
                    if(isFamily(interact)){
                        int value = Randomizer.getRandom(10);
                        if(value < 3){
                            change += 10;
                        }else if(value < 8){
                            change += 5;
                        }else{
                            change += -5;
                        }
                    }else{
                        if(interact.getAge() < 16){
                            int value = Randomizer.getRandom(10);
                            if(value < 2){
                                change += -10;
                            }else if(value < 5){
                                change += -5;
                            }else if(value < 8){
                                change += 5;
                            }else{
                                change += 10;
                            }
                        }else{
                            if(trade(interact)){
                                change += 50;
                                //events.add(firstName + " " + lastName + " has traded with " + interact.getFirstName() + " " + interact.getLastName() + ".");
                            }
                        }
                        if(otherGender(interact)){
                            int value = Randomizer.getRandom(20);
                            if(value < 5){
                                change += 20;
                            }else if(value < 10){
                                change += 5;
                            }else if(value < 13){
                                change += 10;
                            }else if(value < 17){
                                change += -5;
                            }else{
                                change += -25;
                            }
                        }else{
                            int value = Randomizer.getRandom(10);
                            if(value < 2){
                                change += -5;
                            }else if(value < 4){
                                change += -10;
                            }else if(value < 7){
                                change += 5;
                            }else{
                                change += 10;
                            }
                        }
                    }
                    if(relationships.containsKey(interact)){
                        relationships.replace(interact, relationships.get(interact), relationships.get(interact) + change);
                    }else{
                        relationships.put(interact, 500 + change);
                    }
                    interact.setRelationship(this, change);
                    if(!married && !(interact.isMarried()) && otherGender(interact) && interact.getAge() >= 16 && relationships.get(interact) > 950 && !(isFamily(interact)) && Randomizer.getRandom(2000) < relationships.get(interact)){
                        married = true;
                        spouse = interact;
                        interact.setMarried(this);
                        events.add(firstName + " " + lastName + " has married " + interact.getFirstName() + " " + interact.getLastName() + ".");
                    }
                    if(relationships.get(interact) < 100 && Randomizer.getRandom(1500) < (100 - relationships.get(interact))){
                        if(Randomizer.getRandom(3) == 0){
                            setDead();
                            for(Person p : relationships.keySet()){
                                if(relationships.get(p) > 800){
                                    interact.setRelationship(p, - 500);
                                }
                            }
                            events.add(interact.getFirstName() + " " + interact.getLastName() + " has killed " + firstName + " " + lastName + " in self defense.");
                            Driver.updateMurders();
                            int numChild = 0;
                            for(int j = 0; j < children.size(); j++){
                                if(children.get(j).isAlive()){
                                    numChild++;
                                }
                            }
                            for(int j = 0; j < children.size(); j++){
                                if(children.get(j).isAlive()){
                                    children.get(j).sellItem(wealth / numChild);
                                }
                            }
                            return;
                        }else{
                            interact.setDead();
                            for(Person p : interact.getRelationships().keySet()){
                                if(interact.getRelationships().get(p) > 800){
                                    setRelationship(p, - 500);
                                }
                            }
                            events.add(firstName + " " + lastName + " has murdered " + interact.getFirstName() + " " + interact.getLastName() + ".");
                            int numChild = 0;
                            for(int j = 0; j < interact.getChildren().size(); j++){
                                if(interact.getChildren().get(j).isAlive()){
                                    numChild++;
                                }
                            }
                            for(int j = 0; j < interact.getChildren().size(); j++){
                                if(interact.getChildren().get(j).isAlive()){
                                    interact.getChildren().get(j).sellItem(interact.getWealth() / numChild);
                                }
                            }
                        }
                        Driver.updateMurders();
                    }
                }
            }
        }else{
            parentEat();
            int interactWith = Randomizer.getRandom(10) + 3;
            for(int i = 0; i < interactWith; i++){
                Person p = others.get(Randomizer.getRandom(others.size()));
            }
        }
        if(health <= 0 || Randomizer.getRandom(500000) < age){
            alive = false;
            if(health <= 0){
                events.add(firstName + " " + lastName + " has died due to starvation.");
            }else{
                events.add(firstName + " " + lastName + " has died due to disease.");
            }
            int numChild = 0;
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).isAlive()){
                    numChild++;
                }
            }
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).isAlive()){
                    children.get(i).sellItem(wealth / numChild);
                }
            }
        }
    }

    public void eat(){
        int eat = 6;
        Iterator<Item> it = inventory.iterator();
        while (eat > 0 && it.hasNext()) {
            Item j = it.next();
            if(j instanceof Food){
               eat = ((Food) j).eat(eat);
               if(eat > 0){
                   it.remove();
                   if(j.getName().equals("Milk")){
                       job.eatMilk();
                   }else if(j.getName().equals("Carrot")){
                       job.eatCarrot();
                   }else if(j.getName().equals("Potato")){
                       job.eatPotato();
                   }else if(j.getName().equals("Meat")){
                       job.eatMeat();
                   }else{
                       job.eatBread();
                   }
               }
            }
        }
        if(eat == 0){
            health += 1;
            totalFood -= 6;
        }else{
            health -= eat * 2;
            totalFood -= eat;
        }
    }

    public int childEat(){
        int eat = 3;
        Iterator<Item> it = inventory.iterator();
        while (eat > 0 && it.hasNext()) {
            Item j = it.next();
            if(j instanceof Food){
                eat = ((Food) j).eat(eat);
                if(eat > 0){
                    it.remove();
                    if(j.getName().equals("Milk")){
                        job.eatMilk();
                    }else if(j.getName().equals("Carrot")){
                        job.eatCarrot();
                    }else if(j.getName().equals("Potato")){
                        job.eatPotato();
                    }else if(j.getName().equals("Meat")){
                        job.eatMeat();
                    }else{
                        job.eatBread();
                    }
                }
            }
        }
        if(eat == 0){
            totalFood -= 3;
        }else{
            totalFood -= eat;
        }
        return eat;
    }

    public void parentEat(){
        int eat;
        if(father.isAlive() && mother.isAlive()) {
            if (Randomizer.getRandom(2) == 0) {
                eat = mother.childEat();
            } else {
                eat = father.childEat();
            }
        }else if(father.isAlive()){
            eat = father.childEat();
        }else if(mother.isAlive()){
            eat = mother.childEat();
        }else{
            eat = 3;
        }
        if(eat == 0){
            health += 1;
        }else{
            health -= eat * 2;
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
    
    public void interact(ArrayList<Person> others, ArrayList<String> events){
        if(age >= 16){
            int interactWith = Randomizer.getRandom(20) + 5;
            for(int i = 0; i < interactWith; i++) {
                Person interact = others.get(Randomizer.getRandom(others.size()));
                if (!interact.equals(this)) {
                    int change = 0;
                    if (isFamily(interact)) {
                        int value = Randomizer.getRandom(10);
                        if (value < 3) {
                            change += 10;
                        } else if (value < 8) {
                            change += 5;
                        } else {
                            change += -5;
                        }
                    } else {
                        if (interact.getAge() < 16) {
                            int value = Randomizer.getRandom(10);
                            if (value < 2) {
                                change += -10;
                            } else if (value < 5) {
                                change += -5;
                            } else if (value < 8) {
                                change += 5;
                            } else {
                                change += 10;
                            }
                        } else {
                            if (otherGender(interact)) {
                                int value = Randomizer.getRandom(20);
                                if (value < 5) {
                                    change += 20;
                                } else if (value < 10) {
                                    change += 5;
                                } else if (value < 13) {
                                    change += 10;
                                } else if (value < 17) {
                                    change += -5;
                                } else {
                                    change += -25;
                                }
                            } else {
                                int value = Randomizer.getRandom(10);
                                if (value < 2) {
                                    change += -5;
                                } else if (value < 4) {
                                    change += -10;
                                } else if (value < 7) {
                                    change += 5;
                                } else {
                                    change += 10;
                                }
                            }
                        }
                        trade(interact);
                    }
                    setRelationship(interact, change);
                    interact.setRelationship(this, change);
                    if (!married && !(interact.isMarried()) && otherGender(interact) && interact.getAge() >= 16 && relationships.get(interact) > 950 && !(isFamily(interact)) && Randomizer.getRandom(2000) < relationships.get(interact)) {
                        married = true;
                        spouse = interact;
                        interact.setMarried(this);
                        for(Person p : interact.getRelationships().keySet()){
                            if(!(p.equals(this)) && !(interact.isFamily(p)) && interact.getGender() != p.getGender() && !(p.isMarried()) && p.getAge() > 12){
                                p.setRelationship(this, -300);
                            }
                        }
                    }
                    if (relationships.get(interact) < 100 && Randomizer.getRandom(1500) < relationships.get(interact)) {
                        if (Randomizer.getRandom(3) == 0) {
                            setDead();
                            for (Person p : relationships.keySet()) {
                                if (relationships.get(p) > 800) {
                                    p.setRelationship(interact, -500);
                                }
                            }
                            events.add(interact.getFirstName() + " " + interact.getLastName() + " has killed " + firstName + " " + lastName + " in self defense.");

                        } else {
                            interact.setDead();
                            for (Person p : interact.getRelationships().keySet()) {
                                if (interact.getRelationships().get(p) > 800) {
                                    p.setRelationship(this, -500);
                                }
                            }
                            events.add(firstName + " " + lastName + " has murdered " + interact.getFirstName() + " " + interact.getLastName() + ".");
                        }
                    }
                }
            }
        }else{
            
        }
    }

    public boolean trade(Person p) {
        boolean trade = false;
        int goldSpent = 0;
        int goldEarned = 0;
        if (job instanceof Farmer) {
            if (p.getJob() instanceof Farmer) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    p.getJob().eatMilk();
                                    job.newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    p.getJob().eatCarrot();
                                    job.newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    p.getJob().eatPotato();
                                    job.newPotato();
                                }
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } else if (p.getJob() instanceof Butcher) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && j.getName().equals("Meat")) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                p.getJob().eatMeat();
                                job.newMeat();
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
                int raw = ((Consumable) inventory.get(0)).getNumber();
                if(raw > 0){
                    int willing = (p.getWealth() / 2) / (inventory.get(0).getPrice() * raw);
                    if(willing > 0) {
                        if (willing >= raw && (raw * inventory.get(0).getPrice()) < p.getWealth()) {
                            ((Consumable) inventory.get(0)).use(raw);
                            ((Consumable) p.getInventory().get(0)).addMore(raw);
                            goldEarned += inventory.get(0).getPrice() * raw;
                            sellItem(inventory.get(0).getPrice() * raw);
                            p.buyItem(inventory.get(0).getPrice() * raw);
                            trade = true;
                        } else if((willing * inventory.get(0).getPrice()) < p.getWealth()) {
                            ((Consumable) inventory.get(0)).use(willing);
                            ((Consumable) p.getInventory().get(0)).addMore(willing);
                            goldEarned += inventory.get(0).getPrice() * willing;
                            sellItem(inventory.get(0).getPrice() * willing);
                            p.buyItem(inventory.get(0).getPrice() * willing);
                            trade = true;
                        }
                    }
                }
            } else if (p.getJob() instanceof Baker) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && j.getName().equals("Bread")) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                p.getJob().eatBread();
                                job.newBread();
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
                int raw = ((Consumable) inventory.get(1)).getNumber();
                if(raw > 0){
                    int willing = (p.getWealth() / 2) / (inventory.get(1).getPrice() * raw);
                    if(willing > 0) {
                        if (willing >= raw && (raw * inventory.get(1).getPrice()) < p.getWealth()) {
                            ((Consumable) inventory.get(1)).use(raw);
                            ((Consumable) p.getInventory().get(0)).addMore(raw);
                            goldEarned += inventory.get(1).getPrice() * raw;
                            sellItem(inventory.get(1).getPrice() * raw);
                            p.buyItem(inventory.get(1).getPrice() * raw);
                            trade = true;
                        } else if((willing * inventory.get(1).getPrice()) < p.getWealth()) {
                            ((Consumable) inventory.get(1)).use(willing);
                            ((Consumable) p.getInventory().get(0)).addMore(willing);
                            goldEarned += inventory.get(1).getPrice() * willing;
                            sellItem(inventory.get(1).getPrice() * willing);
                            p.buyItem(inventory.get(1).getPrice() * willing);
                            trade = true;
                        }
                    }
                }
            } else if (p.getJob() instanceof Blacksmith) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } else if (p.getJob() instanceof Carpenter) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } else if (p.getJob() instanceof Clothier) {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } else {
                if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                    Iterator<Item> it = inventory.iterator();
                    while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < p.getWealth()) {
                                p.getInventory().add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    job.eatMilk();
                                    p.getJob().newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    job.eatCarrot();
                                    p.getJob().newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    job.eatPotato();
                                    p.getJob().newPotato();
                                }
                                p.addFood(((Food) j).getFood());
                                totalFood -= ((Food) j).getFood();
                                goldEarned += j.getPrice();
                                sellItem(j.getPrice());
                                p.buyItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            }
        } else if (job instanceof Hunter) {
            if (p.getJob() instanceof Farmer) {
                if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                if (j.getName().equals("Milk")) {
                                    p.getJob().eatMilk();
                                    job.newMilk();
                                } else if (j.getName().equals("Carrot")) {
                                    p.getJob().eatCarrot();
                                    job.newCarrot();
                                } else if (j.getName().equals("Potato")) {
                                    p.getJob().eatPotato();
                                    job.newPotato();
                                }
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } else if (p.getJob() instanceof Butcher) {
                if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && j.getName().equals("Meat")) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                p.getJob().eatMeat();
                                job.newMeat();
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
                int raw = ((Consumable) inventory.get(1)).getNumber();
                if(raw > 0){
                    int willing = (p.getWealth() / 2) / (inventory.get(1).getPrice() * raw);
                    if(willing > 0) {
                        if (willing >= raw && (raw * inventory.get(1).getPrice()) < p.getWealth()) {
                            ((Consumable) inventory.get(1)).use(raw);
                            ((Consumable) p.getInventory().get(0)).addMore(raw);
                            goldEarned += inventory.get(1).getPrice() * raw;
                            sellItem(inventory.get(1).getPrice() * raw);
                            p.buyItem(inventory.get(1).getPrice() * raw);
                            trade = true;
                        } else if((willing * inventory.get(1).getPrice()) < p.getWealth()){
                            ((Consumable) inventory.get(1)).use(willing);
                            ((Consumable) p.getInventory().get(0)).addMore(willing);
                            goldEarned += inventory.get(1).getPrice() * willing;
                            sellItem(inventory.get(1).getPrice() * willing);
                            p.buyItem(inventory.get(1).getPrice() * willing);
                            trade = true;
                        }
                    }
                }
            }else if(p.getJob() instanceof Baker){
                if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                    Iterator<Item> it = p.getInventory().iterator();
                    while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                        Item j = it.next();
                        if (j instanceof Food && j.getName().equals("Bread")) {
                            if (j.getPrice() < wealth) {
                                inventory.add(j);
                                it.remove();
                                p.getJob().eatBread();
                                job.newBread();
                                totalFood += ((Food) j).getFood();
                                p.loseFood(((Food) j).getFood());
                                goldSpent += j.getPrice();
                                buyItem(j.getPrice());
                                p.sellItem(j.getPrice());
                                trade = true;
                            }
                        }
                    }
                }
            } if (p.getJob() instanceof Clothier) {

            } else if (p.getJob() instanceof Blacksmith) {

            } else if (p.getJob() instanceof Carpenter) {

            } else if (job instanceof Butcher) {
                if (p.getJob() instanceof Farmer) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatMeat();
                                    p.getJob().newMeat();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                    int raw = ((Consumable) p.getInventory().get(0)).getNumber();
                    if(raw > 0) {
                        int willing = (wealth / 2) / (p.getInventory().get(0).getPrice() * raw);
                        if(willing > 0) {
                            if (willing >= raw && (raw * inventory.get(0).getPrice()) < wealth) {
                                ((Consumable) p.getInventory().get(0)).use(raw);
                                ((Consumable) inventory.get(0)).addMore(raw);
                                goldSpent += p.getInventory().get(0).getPrice() * raw;
                                buyItem(p.getInventory().get(0).getPrice() * raw);
                                p.sellItem(p.getInventory().get(0).getPrice() * raw);
                                trade = true;
                            } else if((willing * inventory.get(0).getPrice()) < wealth){
                                ((Consumable) p.getInventory().get(0)).use(willing);
                                ((Consumable) inventory.get(0)).addMore(willing);
                                goldSpent += p.getInventory().get(0).getPrice() * willing;
                                buyItem(p.getInventory().get(0).getPrice() * willing);
                                p.sellItem(p.getInventory().get(0).getPrice() * willing);
                                trade = true;
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatMeat();
                                    p.getJob().newMeat();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if(p.getJob() instanceof Baker){
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatMeat();
                                    p.getJob().newMeat();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } if (p.getJob() instanceof Hunter) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatMeat();
                                    p.getJob().newMeat();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                    int raw = ((Consumable) p.getInventory().get(1)).getNumber();
                    if(raw > 0) {
                        int willing = (wealth / 2) / (p.getInventory().get(1).getPrice() * raw);
                        if(willing > 0) {
                            if (willing >= raw && (raw * p.getInventory().get(1).getPrice()) < wealth) {
                                ((Consumable) p.getInventory().get(1)).use(raw);
                                ((Consumable) inventory.get(0)).addMore(raw);
                                goldSpent += p.getInventory().get(1).getPrice() * raw;
                                buyItem(p.getInventory().get(1).getPrice() * raw);
                                p.sellItem(p.getInventory().get(1).getPrice() * raw);
                                trade = true;
                            } else if((willing * p.getInventory().get(1).getPrice()) < wealth){
                                ((Consumable) p.getInventory().get(1)).use(willing);
                                ((Consumable) inventory.get(0)).addMore(willing);
                                goldSpent += p.getInventory().get(1).getPrice() * willing;
                                buyItem(p.getInventory().get(1).getPrice() * willing);
                                p.sellItem(p.getInventory().get(1).getPrice() * willing);
                                trade = true;
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                } else if (p.getJob() instanceof Blacksmith) {

                } else if (p.getJob() instanceof Carpenter) {

                } else {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatMeat();
                                    p.getJob().newMeat();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }
            } else if (job instanceof Baker) {
                if (p.getJob() instanceof Farmer) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                    int raw = ((Consumable) p.getInventory().get(1)).getNumber();
                    if(raw > 0) {
                        int willing = (wealth / 2) / (p.getInventory().get(1).getPrice() * raw);
                        if(willing > 0) {
                            if (willing >= raw && (raw * inventory.get(0).getPrice()) < wealth) {
                                ((Consumable) p.getInventory().get(1)).use(raw);
                                ((Consumable) inventory.get(0)).addMore(raw);
                                goldSpent += p.getInventory().get(1).getPrice() * raw;
                                buyItem(p.getInventory().get(0).getPrice() * raw);
                                p.sellItem(p.getInventory().get(1).getPrice() * raw);
                                trade = true;
                            } else if((willing * inventory.get(0).getPrice()) < wealth){
                                ((Consumable) p.getInventory().get(1)).use(willing);
                                ((Consumable) inventory.get(0)).addMore(willing);
                                goldSpent += p.getInventory().get(1).getPrice() * willing;
                                buyItem(p.getInventory().get(0).getPrice() * willing);
                                p.sellItem(p.getInventory().get(1).getPrice() * willing);
                                trade = true;
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Baker){
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    } else if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Blacksmith) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Carpenter) {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else {
                    if (p.foodNeeded() > 0 && foodNeeded() == 0) {
                        Iterator<Item> it = inventory.iterator();
                        while (p.foodNeeded() > 0 && foodNeeded() == 0 && goldEarned < (p.getWealth() / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < p.getWealth()) {
                                    p.getInventory().add(j);
                                    it.remove();
                                    job.eatBread();
                                    p.getJob().newBread();
                                    p.addFood(((Food) j).getFood());
                                    totalFood -= ((Food) j).getFood();
                                    goldEarned += j.getPrice();
                                    sellItem(j.getPrice());
                                    p.buyItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }
            } else if (job instanceof Clothier) {
                if (p.getJob() instanceof Farmer) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }else if(p.getJob() instanceof Baker){
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                } else if (p.getJob() instanceof Blacksmith) {

                } else if (p.getJob() instanceof Hunter) {

                } else {

                }
            } else if (job instanceof Miner) {
                if (p.getJob() instanceof Farmer) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }else if(p.getJob() instanceof Baker){
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                } else if (p.getJob() instanceof Blacksmith) {

                } else if (p.getJob() instanceof Carpenter) {

                }
            } else if (job instanceof Blacksmith) {
                if (p.getJob() instanceof Farmer) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }else if(p.getJob() instanceof Baker){
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                } else if (p.getJob() instanceof Blacksmith) {

                } else if (p.getJob() instanceof Carpenter) {

                }
            } else if (job instanceof Lumberjack) {
                if (p.getJob() instanceof Farmer) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }else if(p.getJob() instanceof Baker){
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                }
            } else {
                if (p.getJob() instanceof Farmer) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && !(j.getName().equals("Meat")) && !(j.getName().equals("Bread"))) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    if (j.getName().equals("Milk")) {
                                        p.getJob().eatMilk();
                                        job.newMilk();
                                    } else if (j.getName().equals("Carrot")) {
                                        p.getJob().eatCarrot();
                                        job.newCarrot();
                                    } else if (j.getName().equals("Potato")) {
                                        p.getJob().eatPotato();
                                        job.newPotato();
                                    }
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Butcher) {
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Meat")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatMeat();
                                    job.newMeat();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                }else if(p.getJob() instanceof Baker){
                    if (foodNeeded() > 0 && p.foodNeeded() == 0) {
                        Iterator<Item> it = p.getInventory().iterator();
                        while (foodNeeded() > 0 && p.foodNeeded() == 0 && goldSpent < (wealth / 10) && it.hasNext()) {
                            Item j = it.next();
                            if (j instanceof Food && j.getName().equals("Bread")) {
                                if (j.getPrice() < wealth) {
                                    inventory.add(j);
                                    it.remove();
                                    p.getJob().eatBread();
                                    job.newBread();
                                    totalFood += ((Food) j).getFood();
                                    p.loseFood(((Food) j).getFood());
                                    goldSpent += j.getPrice();
                                    buyItem(j.getPrice());
                                    p.sellItem(j.getPrice());
                                    trade = true;
                                }
                            }
                        }
                    }
                } else if (p.getJob() instanceof Clothier) {

                }
            }
        }
        return trade;
    }

    public int foodNeeded(){
        int need = 6 * paranoid - (health / 2);
        if(isMarried()){
            for(int i = 0; i < children.size(); i++){
                if(children.get(i).isAlive() && children.get(i).getAge() < 16) {
                    need += 3 * paranoid - (children.get(i).getHealth() / 2);
                }
            }
            if(spouse.isAlive()){
                need /= 2;
            }
        }
        need -= totalFood;
        if(need < 0){
            need = 0;
        }
        return need;
    }

    public void addFood(int food){
        totalFood += food;
    }
    
    public void loseFood(int food){
        totalFood -= food;
    }
    
    public void buyItem(int w){
        wealth -= w;
    }

    public void sellItem(int w){
        wealth += w;
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

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public int getHealth(){
        return health;
    }

    public HairStyle getRandomHairStyles(){
        Random rng = new Random();
        return hairStyleAlleles[Randomizer.getRandom(2)];
    }

    public HairColor getRandomHairColors(){
        Random rng = new Random();
        return hairColorAlleles[Randomizer.getRandom(2)];
    }

    public EyeColor getRandomEyeColors(){
        Random rng = new Random();
        return eyeAlleles[Randomizer.getRandom(2)];
    }

    public SkinColor getRandomSkinColors(){
        Random rng = new Random();
        return skinAlleles[Randomizer.getRandom(2)];
    }

    public Job getJob(){
        return job;
    }

    public int getCompetence(){
        return competence;
    }

    public int getParanoid() {
        return paranoid;
    }

    public void pickJob(){
        job = JobBuilder.newWorker(inventory);
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
        s += "Health: " + health + "\n";
        s += "Wealth: " + wealth;
        s += "\n" + "Competence: " + competence + ", Paranoia: " + paranoid;
        if(age >= 16){
            s += "\n" + job;
        }
        return s;
    }

    public boolean equals(Person p){
        if(firstName.equals(p.getFirstName()) && lastName.equals(p.getLastName()) && age == p.getAge() && wealth == p.getWealth() && gender == p.getGender() && alive == p.isAlive() && married == p.isMarried()){
            return true;
        }
        return false;
    }
}
import java.util.Random;
import java.util.ArrayList;
public class PersonBuilder {

    private static Random rng = new Random();
    private static String[] maleFirstNames = {"Jackson", "Aiden", "Lucas", "Liam", "Noah", "Ethan", "Mason", "Caden", "Oliver", "Elijah", "Grayson", "Jacob", "Michael", "Benjamin", "Carter", "James", "Jayden", "Logan", "Alexander", "Caleb", "Ryan", "Luke", "Daniel", "Jack", "William", "Owen", "Gabriel", "Matthew", "Connor", "Jayce", "Isaac", "Sebastian", "Henry", "Muhammad", "Cameron", "Wyatt", "Dylan", "Nathan", "Nicholas", "Julian", "Eli", "Levi", "Isaiah", "Landon", "David", "Christian", "Andrew", "Brayden", "John", "Lincoln", "Samuel", "Joseph", "Hunter", "Joshua", "Mateo", "Dominic", "Adam", "Leo", "Ian", "Kennedy", "Josiah", "Anthony", "Colton", "Max", "Thomas", "Evan", "Nolan", "Aaron", "Carson", "Christopher", "Hudson", "Cooper", "Adrian", "Jonathan", "Jason", "Charlie", "Miles", "Jeremiah", "Gavin", "Asher", "Austin", "Ezra", "Chase", "Alex", "Xavier", "Jordan", "Tristan", "Easton", "Zachary", "Parker", "Bryson", "Tyler", "Camden", "Damian", "Declan", "Elliot", "Elias", "Cole", "Harrison", "Zane", "Kai"};
    private static String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"};
    private static String[] femaleFirstNames = {"Sophia", "Emma", "Olivia", "Ava", "Mia", "Isabella", "Riley", "Aria", "Zoe", "Charlotte", "Lily", "Layla", "Amelia", "Emily", "Madelyn", "Aubrey", "Adalyn", "Madison", "Chloe", "Harper", "Abigail", "Aaliyah", "Avery", "Evelyn", "Kaylee", "Ella", "Ellie", "Scarlett", "Arianna", "Hailey", "Nora", "Addison", "Brooklyn", "Hannah", "Mila", "Leah", "Elizabeth", "Sarah", "Eliana", "Mackenzie", "Peyton", "Maria", "Grace", "Adeline", "Elena", "Anna", "Victoria", "Camilla", "Lillian", "Natalie", "Isabelle", "Skyler", "Maya", "Lucy", "Lila", "Audrey", "Makayla", "Penelope", "Claire", "Paisley", "Savannah", "Alaina", "Gabriella", "Violet", "Kylie", "Charlie", "Stella", "Allison", "Liliana", "Eva", "Callie", "Kinsley", "Reagan", "Sophie", "Alyssa", "Alice", "Caroline", "Aurora", "Eleanor", "Juliana", "Annabelle", "Emilia", "Sadie", "Bella", "Julia", "Keira", "Bailey", "Hazel", "Jocelyn", "London", "Samantha", "Vivian", "Gianna", "Alexandra", "Cora", "Melanie", "Everly", "Jordyn", "Luna"};

    public static Person newPerson(Person mother, Person father, int counter){
        Gender g = RandomEnum.getRandomGender();
        String first;
        if(g == Gender.FEMALE){
            first = femaleFirstNames[rng.nextInt(femaleFirstNames.length)];
        }else{
            first = maleFirstNames[rng.nextInt(maleFirstNames.length)];
        }
        String last = father.getLastName();

        SkinColor[] sc = {mother.getRandomSkinColors(), father.getRandomSkinColors()};
        EyeColor[] ec = {mother.getRandomEyeColors(), father.getRandomEyeColors()};
        HairStyle[] hs = {mother.getRandomHairStyles(), father.getRandomHairStyles()};
        HairColor[] hc = {mother.getRandomHairColors(), father.getRandomHairColors()};

        Person p = new Person(first, last, g, 0, 0, mother, father, new ArrayList<>(), counter, sc, hs, hc, ec);
        for(int i = 0; i < p.getMother().getChildren().size(); i++){
            p.addSibling(p.getMother().getChildren().get(i));
        }
        for(int i = 0; i < p.getSiblings().size(); i++){
            p.getSiblings().get(i).addSibling(p);
        }
        mother.addChild(p);
        father.addChild(p);

        return p;
    }

    public static Person createPerson(){
        Gender g = RandomEnum.getRandomGender();
        String first;
        if(g == Gender.FEMALE){
            first = femaleFirstNames[rng.nextInt(femaleFirstNames.length)];
        }else{
            first = maleFirstNames[rng.nextInt(maleFirstNames.length)];
        }
        String last = lastNames[rng.nextInt(lastNames.length)];
        int age = rng.nextInt(10) + 16;
        int wealth = rng.nextInt(451) + 50;

        SkinColor[] sc = {RandomEnum.getRandomSkinColors(), RandomEnum.getRandomSkinColors()};
        EyeColor[] ec = {RandomEnum.getRandomEyeColors(), RandomEnum.getRandomEyeColors()};
        HairStyle[] hs = {RandomEnum.getRandomHairStyles(), RandomEnum.getRandomHairStyles()};
        HairColor[] hc = {RandomEnum.getRandomHairColors(), RandomEnum.getRandomHairColors()};

        Person p = new Person(first, last, g, age, wealth, null, null, new ArrayList<Person>(), 0, sc, hs, hc, ec);

        return p;
    }
}
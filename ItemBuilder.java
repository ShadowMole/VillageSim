public class ItemBuilder {

    public static Item newItem(String s, int n){
        Item i;
        switch(s){
            case "Carrot":
                i = new Food(s, 3, 1);
                break;
            case "Potato":
                i = new Food(s, 5, 1);
                break;
            case "Bread":
                i = new Food(s, 15, 10);
                break;
            case "Meat":
                i = new Food(s, 20, 4);
                break;
            case "Milk":
                i = new Food(s, 8, 8);
                break;
            case "Wheat":
                i = new Consumable(s, 1, n);
                break;
            case "Skin":
                i = new Consumable(s, 2, n);
                break;
            case "Wool":
                i = new Consumable(s, 2, n);
                break;
            case "Arrow":
                i = new Consumable(s, 1, n);
                break;
            case "Raw Meat":
                i = new Consumable(s, 10, n);
                break;
            case "Metal":
                i = new Consumable(s, 5, n);
                break;
            case "Wood":
                i = new Consumable(s, 2, n);
                break;
            case "String":
                i = new Consumable(s, 10, n);
                break;
            case "Bow":
                i = new Tool(s,200, 260 + Randomizer.getRandom(201));
                break;
            case "Knife":
                i = new Tool(s, 50, 130 + Randomizer.getRandom(101));
                break;
            case "Axe":
                i = new Tool(s, 100, 260 + Randomizer.getRandom(201));
                break;
            case "Pickaxe":
                i = new Tool(s, 70, 520 + Randomizer.getRandom(401));
                break;
            case "Bucket":
                i = new Tool(s, 10, 1300 + Randomizer.getRandom(1001));
                break;
            case "Cart":
                i = new Tool(s, 500, 2600 + Randomizer.getRandom(2001));
                break;
            case "Plow":
                i = new Tool(s, 200, 2600 + Randomizer.getRandom(2001));
                break;
            case "Shovel":
                i = new Tool(s, 10, 520 + Randomizer.getRandom(401));
                break;
            case "Clothes":
                i = new Tool(s, 50, 130 + Randomizer.getRandom(101));
                break;
            case "Pack":
                i = new Tool(s, 30, 1800 + Randomizer.getRandom(1001));
                break;
            case "Cutting Board":
                i = new Tool(s, 20, 520 + Randomizer.getRandom(401));
                break;
            case "Shears":
                i = new Tool(s, 15, 260 + Randomizer.getRandom(201));
                break;
            case "Hammer":
                i = new Tool(s, 20, 260 + Randomizer.getRandom(201));
                break;
            case "Cow":
                i = new Animal(s, 2000, 5200 + Randomizer.getRandom(4001), 1, 180, 200);
                break;
            case "Pig":
                i = new Animal(s, 200, 1800 + Randomizer.getRandom(1001), 2 + Randomizer.getRandom(9), 90, 50);
                break;
            case "Sheep":
                i = new Animal(s, 500, 2600 + Randomizer.getRandom(2001), 1 + Randomizer.getRandom(5), 360, 30);
                break;
            default:
                i = null;
                break;
        }
        return i;
    }
}

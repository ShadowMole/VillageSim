public class Miner extends Job {

    public Miner(){
        super();

        needs = new ItemType[]{ItemType.PICKAXE, ItemType.CART};
        produces = new ItemType[]{ItemType.METAL};
    }

    @Override
    public void initialFirstInventory(){
        inventory.add(new Tool("PickAxe",27000, 520 + Randomizer.getRandom(401)));
        inventory.add(new Tool("Cart", 500, 0 + Randomizer.getRandom(101)));
    }
}

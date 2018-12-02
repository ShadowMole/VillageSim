public class Hunter extends Job {

    public Hunter(){
        super();

        needs = new ItemType[]{ItemType.BOW, ItemType.ARROW, ItemType.PACK, ItemType.KNIFE};
        produces = new ItemType[]{ItemType.RAWMEAT, ItemType.SKIN};
    }

    @Override
    public void initialFirstInventory(){
        inventory.add(ItemBuilder.newItem("Knife", 0));
        inventory.add(ItemBuilder.newItem("Arrow", 20));
        inventory.add(ItemBuilder.newItem("Bow", 0));
    }
}

public class Clothier extends Job {

    public Clothier(){
        super();

        needs = new ItemType[]{ItemType.WOOL, ItemType.SKIN, ItemType.KNIFE};
        produces = new ItemType[]{ItemType.CLOTHES, ItemType.STRING, ItemType.PACK};
    }

    @Override
    public void initialFirstInventory(){
        inventory.add(ItemBuilder.newItem("Knife", 0));
    }
}

package minecraft.item;

public class ItemSword extends ItemTool {
    ItemSword(String name, Material material) {
        super(name, EquipmentType.SWORD, material);
    }

    @Override
    public double getModifier() {
        return 1;
    }
}

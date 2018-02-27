package minecraft.item;

public class ItemPickaxe extends ItemHarvestTool {
    private int harvestLevel;

    ItemPickaxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier, int harvestLevel) {
        super(name, EquipmentType.PICKAXE, maxDurability, durability, attackDamage, minModifier, maxModifier);
        this.harvestLevel = harvestLevel;
    }

    ItemPickaxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier, int harvestLevel) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier, harvestLevel);
    }

    public int getMiningLevel() {
        return harvestLevel;
    }
}

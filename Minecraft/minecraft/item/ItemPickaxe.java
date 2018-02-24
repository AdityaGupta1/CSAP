package minecraft.item;

public class ItemPickaxe extends ItemHarvestTool {
    ItemPickaxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, EquipmentType.PICKAXE, maxDurability, durability, attackDamage, minModifier, maxModifier);
    }

    ItemPickaxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }
}

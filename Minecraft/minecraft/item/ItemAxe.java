package minecraft.item;

public class ItemAxe extends ItemHarvestTool {
    ItemAxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, EquipmentType.AXE, maxDurability, durability, attackDamage, minModifier, maxModifier);
    }

    ItemAxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }
}

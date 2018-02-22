package minecraft.item;

public class ItemSword extends ItemWithDurability implements ItemEquipment {
    private final int attackDamage;

    ItemSword(String name, int maxDurability, int durability, int attackDamage) {
        super(name, maxDurability, durability);
        this.attackDamage = attackDamage;
    }

    ItemSword(String name, int maxDurability, int attackDamage) {
        this(name, maxDurability, maxDurability, attackDamage);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return 1;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.SWORD;
    }
}

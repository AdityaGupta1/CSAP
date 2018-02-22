package minecraft.item;

public class ItemSword extends ItemWithDurability implements ItemEquipment {
    private int attackDamage;

    ItemSword(String name, int maxDurability, int attackDamage) {
        super(name, maxDurability);
        this.attackDamage = attackDamage;
    }

    ItemSword(String name, int maxDurability, int durability, int attackDamage) {
        super(name, maxDurability, durability);
        this.attackDamage = attackDamage;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.SWORD;
    }
}

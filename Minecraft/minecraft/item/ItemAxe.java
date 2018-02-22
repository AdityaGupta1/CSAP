package minecraft.item;

import minecraft.game.Game;

public class ItemAxe extends ItemWithDurability implements ItemEquipment {
    private final int attackDamage;

    private final double minModifier;
    private final double maxModifier;

    ItemAxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, maxDurability, durability);
        this.attackDamage = attackDamage;
        this.minModifier = minModifier;
        this.maxModifier = maxModifier;
    }

    ItemAxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return (Game.random.nextDouble() * (maxModifier - minModifier)) + minModifier;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.AXE;
    }
}

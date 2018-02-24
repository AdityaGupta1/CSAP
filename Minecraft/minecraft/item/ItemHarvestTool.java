package minecraft.item;

import minecraft.game.Game;

class ItemHarvestTool extends ItemWithDurability implements ItemEquipment {
    private final EquipmentType type;

    private final int attackDamage;

    private final double minModifier;
    private final double maxModifier;

    ItemHarvestTool(String name, EquipmentType type, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, maxDurability, durability);
        this.type = type;
        this.attackDamage = attackDamage;
        this.minModifier = minModifier;
        this.maxModifier = maxModifier;
    }

    ItemHarvestTool(String name, EquipmentType type,  int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, type, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return (Game.random.nextDouble() * (maxModifier - minModifier)) + minModifier;
    }

    @Override
    public EquipmentType getType() {
        return type;
    }
}

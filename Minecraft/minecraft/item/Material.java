package minecraft.item;

import minecraft.game.Game;

public enum Material {
    WOOD(64, 0, 1.5, 2, 4),
    STONE(256, 1, 2, 2.5, 5),
    IRON(512, 2, 3, 4, 6),
    DIAMOND(2048, 3, 5, 6, 7);

    final int durability;
    private int harvestLevel;
    final double minModifier;
    final double maxModifier;
    final int attackDamage;
    Material(int durability, int harvestLevel, double minModifier, double maxModifier, int attackDamage) {
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.minModifier = minModifier;
        this.maxModifier = maxModifier;
        this.attackDamage = attackDamage;
    }

    public int getDurability() {
        return durability;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public double getModifier() {
        return (Game.random.nextDouble() * (maxModifier - minModifier)) + minModifier;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}

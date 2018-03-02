package minecraft.item;

public class ItemTool extends ItemEquipment {
    private final EquipmentType type;
    private final Material material;
    private final int attackDamage;

    ItemTool(String name, EquipmentType type, Material material) {
        super(name, material);
        this.type = type;
        this.material = material;
        this.attackDamage = material.getAttackDamage();
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return material.getModifier();
    }

    public int getHarvestLevel() {
        return material.getHarvestLevel();
    }

    @Override
    public EquipmentType getType() {
        return type;
    }
}

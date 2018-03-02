package minecraft.item;

public abstract class ItemEquipment extends ItemWithDurability {
    private final Material material;

    ItemEquipment(String name, Material material) {
        super(name, material.getDurability());
        this.material = material;
    }

    public double getModifier() {
        return material.getModifier();
    }

    public abstract EquipmentType getType();
}

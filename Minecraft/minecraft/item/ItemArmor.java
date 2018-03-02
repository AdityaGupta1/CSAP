package minecraft.item;

public class ItemArmor extends ItemEquipment {
    private final EquipmentType type;

    ItemArmor(String name, EquipmentType type, Material material) {
        super(name, material);
        this.type = type;
    }

    @Override
    public EquipmentType getType() {
        return null;
    }
}

package minecraft.item;

public interface ItemEquipment {
    EquipmentType getType();
    int getAttackDamage();
    double getModifier();
}

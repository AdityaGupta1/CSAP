package minecraft.entity.player;

import minecraft.game.Game;
import minecraft.game.PrintUtils;
import minecraft.item.*;

public class PlayerEquipment {
    private ItemEquipment sword;
    private ItemEquipment axe;
    private ItemEquipment pickaxe;
    private ItemEquipment shovel;
    private ItemEquipment helmet;
    private ItemEquipment chestplate;
    private ItemEquipment leggings;
    private ItemEquipment boots;

    PlayerEquipment() {
        // package-private
    }

    public ItemTool getAttackTool() {
        ItemEquipment[] attackTools = {sword, axe, pickaxe};

        for (ItemEquipment attackTool : attackTools) {
            if (attackTool != null) {
                return (ItemTool) attackTool;
            }
        }

        return null;
    }

    public int getAttackDamage() {
        ItemTool attackTool = getAttackTool();

        if (attackTool != null) {
            return attackTool.getAttackDamage();
        }

        return 1;
    }

    public ItemEquipment get(EquipmentType type) {
        ItemEquipment item;

        switch(type) {
            case SWORD:
                item = sword;
                break;
            case AXE:
                item = axe;
                break;
            case PICKAXE:
                item = pickaxe;
                break;
            case SHOVEL:
                item = shovel;
                break;
            case HELMET:
                item = helmet;
                break;
            case CHESTPLATE:
                item = chestplate;
                break;
            case LEGGINGS:
                item = leggings;
                break;
            case BOOTS:
                item = boots;
                break;
            default:
                return null;
        }

        if (item == null) {
            return null;
        }

        if (((ItemWithDurability) item).isBroken()) {
            set(type, null);
            return null;
        }

        return item;
    }

    public double getModifier(EquipmentType type) {
        if (type == null || get(type) == null) {
            return 1;
        }

        return get(type).getModifier();
    }

    public int getMiningLevel() {
        if (this.get(EquipmentType.PICKAXE) == null) {
            return -1;
        }

        return ((ItemPickaxe) this.get(EquipmentType.PICKAXE)).getHarvestLevel();
    }

    private void set(EquipmentType type, ItemEquipment item) {
        switch(type) {
            case SWORD:
                sword = item;
                break;
            case AXE:
                axe = item;
                break;
            case PICKAXE:
                pickaxe = item;
                break;
            case SHOVEL:
                shovel = item;
                break;
            case HELMET:
                helmet = item;
                break;
            case CHESTPLATE:
                chestplate = item;
                break;
            case LEGGINGS:
                leggings = item;
                break;
            case BOOTS:
                boots = item;
                break;
            default:
                break;
        }
    }

    public ItemEquipment swap(ItemEquipment item) {
        ItemEquipment previous = get(item.getType());

        set(item.getType(), item);

        if (previous != null) {
            Game.player.getInventory().add(new ItemStack((Item) previous, 1));
        }

        System.out.println(Game.player + " equipped " + item + (previous != null ? ", replacing " + previous : ""));

        Game.player.getInventory().subtract(new ItemStack((Item) item, 1));

        return previous;
    }

    @Override
    public String toString() {
        String string = PrintUtils.wrap("current equipment: ", false, true);

        for (EquipmentType type : EquipmentType.values()) {
            ItemEquipment item = this.get(type);
            string += type.toString().toLowerCase() + ": " + (item == null ? "none" : item)+ ",\n";
        }

        return string.substring(0, string.length() - 2);
    }
}

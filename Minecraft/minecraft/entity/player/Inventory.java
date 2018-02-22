package minecraft.entity.player;

import minecraft.item.EquipmentType;
import minecraft.item.Item;
import minecraft.item.ItemEquipment;
import minecraft.item.ItemStack;

import java.util.*;

public class Inventory {
    private List<ItemStack> itemStacks = new ArrayList<>();

    public Inventory(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(itemStacks);
    }

    public Inventory() {
    }

    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public void addItemStacks(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(itemStacks);
    }

    private void update() {
        List<ItemStack> newStacks = new ArrayList<>();

        for (ItemStack itemStack : itemStacks) {
            if (!itemStack.isEmpty()) {
                newStacks.add(itemStack);
            }
        }

        itemStacks = newStacks;
    }

    public List<ItemStack> getConsolidated() {
        update();

        HashMap<Item, Integer> items = new HashMap<>();
        List<ItemStack> singletons = new ArrayList<>();

        for (ItemStack itemstack : itemStacks) {
            Item item = itemstack.getItem();

            if (item.isSingleton()) {
                singletons.add(itemstack);
                continue;
            }

            if (!items.keySet().contains(item)) {
                items.put(item, itemstack.getAmount());
            } else {
                items.put(item, items.get(item) + itemstack.getAmount());
            }
        }

        List<ItemStack> consolidated = new ArrayList<>();

        for (Item item : items.keySet()) {
            consolidated.add(new ItemStack(item, items.get(item)));
        }

        consolidated.addAll(singletons);

        return consolidated;
    }

    public Map<EquipmentType, List<ItemEquipment>> getEquipment() {
        update();

        Map<EquipmentType, List<ItemEquipment>> equipment = new HashMap<>();
        for (EquipmentType type : EquipmentType.values()) {
            equipment.put(type, new ArrayList<>());
        }

        boolean hasEquipment = false;

        for (ItemStack itemStack : itemStacks) {
            if (itemStack.getItem() instanceof ItemEquipment) {
                ItemEquipment item = (ItemEquipment) itemStack.getItem();
                equipment.get(item.getType()).add(item);
                hasEquipment = true;
            }
        }

        return hasEquipment ? equipment : null;
    }

    public boolean has(List<ItemStack> itemStacks) {
        update();

        List<ItemStack> consolidated = getConsolidated();

        for (ItemStack otherStack : itemStacks) {
            boolean flag = false;

            for (ItemStack itemstack : consolidated) {
                if (itemstack.has(otherStack)) {
                    flag = true;
                }
            }

            if (!flag) {
                return false;
            }
        }

        return true;
    }
    
    public boolean has(Item item) {
        return has(Collections.singletonList(new ItemStack(item, 1)));
    }

    public boolean subtract(List<ItemStack> itemStacks) {
        update();

        if (!has(itemStacks)) {
            return false;
        }

        for (ItemStack otherStack : itemStacks) {
            for (ItemStack itemstack : this.itemStacks) {
                itemstack.subtract(otherStack);
            }
        }

        return true;
    }

    public boolean subtract(ItemStack... itemStacks) {
        return subtract(Arrays.asList(itemStacks));
    }

    public void add(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(itemStacks);
    }

    public void add(ItemStack... itemStacks) {
        add(Arrays.asList(itemStacks));
    }

    public boolean isEmpty() {
        update();

        return itemStacks.size() == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "none";
        }

        String inventoryString = "";

        for (ItemStack itemstack : getConsolidated()) {
            if (itemstack.getAmount() <= 0) {
                continue;
            }

            inventoryString += itemstack + ",\n";
        }

        return inventoryString.substring(0, inventoryString.length() - 2);
    }
}

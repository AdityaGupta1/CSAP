package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Creeper extends Monster {
    private boolean isCharged = false;

    public Creeper(String name) {
        super(name);
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.gunpowder, 2));
        return dropItems;
    }
}

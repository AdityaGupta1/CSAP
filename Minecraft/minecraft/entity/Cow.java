package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Cow extends Creature {
    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_beef, 2));
        dropItems.add(new ItemStack(Items.leather, 1));
        return dropItems;
    }

    @Override
    public Entity copy() {
        return new Cow();
    }
}

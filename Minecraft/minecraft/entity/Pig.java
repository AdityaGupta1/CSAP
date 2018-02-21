package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Pig extends Creature {
    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_pork, 1, 3));
        return dropItems;
    }

    @Override
    public Entity copy() {
        return new Pig();
    }
}

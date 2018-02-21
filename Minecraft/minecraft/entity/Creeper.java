package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Creeper extends Monster {
    private boolean isCharged = false;

    public Creeper() {
        super();
    }

    private Creeper(boolean isCharged) {
        this.isCharged = isCharged;
    }

    @Override
    public List<ItemStack> die() {
        System.out.println(this + " almost explodes");
        return super.die();
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.gunpowder, 1, 3));
        return dropItems;
    }

    @Override
    public Creeper copy() {
        return new Creeper(isCharged);
    }
}

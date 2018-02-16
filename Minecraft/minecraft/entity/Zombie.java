package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Zombie extends Monster {
    private boolean isVillager = false;

    public Zombie(boolean isVillager) {
        this.isVillager = isVillager;
    }

    public Zombie convertVillager(Villager villager) {
        villager.die();
        return new Zombie(true);
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.rotten_flesh, 2));
        return dropItems;
    }

    @Override
    public Entity copy() {
        return new Zombie(isVillager);
    }

    @Override
    public String toString() {
        return "zombie " + (isVillager ? "villager " : "");
    }
}

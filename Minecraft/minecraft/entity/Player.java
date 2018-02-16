package minecraft.entity;

import minecraft.item.Inventory;
import minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Player extends Entity {
    private Inventory inventory = new Inventory();

    public Player(String name) {
        super(name);
    }

    public void pickUp(List<ItemStack> itemstacks) {
        inventory.addItemStacks(itemstacks);
    }

    public void kill(Entity other) {
        List<ItemStack> drops = other.die();
        pickUp(drops);
        System.out.println(this + " killed " + other + " and received " + drops);
    }

    public void kill(List<Entity> others) {
        for (Entity other : others) {
            kill(other);
        }
    }

    public void kill(Entity... others) {
        kill(Arrays.asList(others));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String fullDescription() {
        return "player named " + name + " with items:\n" + inventory;
    }

    @Override
    public List<ItemStack> getDropItems() {
        return inventory.getItemStacks();
    }
}

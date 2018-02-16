package minecraft.entity;

import minecraft.item.Inventory;
import minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Player extends Entity {
    private String name;

    private Inventory inventory = new Inventory();

    public Player(String name) {
        this.name = name;
    }

    private Player(String name, Inventory inventory) {
        this(name);
        this.inventory = inventory;
    }

    public void pickUp(List<ItemStack> itemStacks) {
        inventory.addItemStacks(itemStacks);
        System.out.println(this + " picked up " + itemStacks);
    }

    public void kill(Entity other) {
        List<ItemStack> drops = other.die();
        System.out.println(this + " killed " + other + ", which dropped " + drops);
        pickUp(drops);
    }

    public void kill(List<Entity> others) {
        for (Entity other : others) {
            if (other.isDead()) {
                return;
            }

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

    public void displayFull() {
        System.out.println(fullDescription());
    }

    public String getName() {
        return name;
    }

    @Override
    public List<ItemStack> getDropItems() {
        return inventory.getItemStacks();
    }

    @Override
    public Entity copy() {
        return new Player(name, inventory);
    }

    @Override
    public String toString() {
        return "player " + name;
    }
}

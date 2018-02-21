package minecraft.entity;

import minecraft.biome.Biome;
import minecraft.game.Game;
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

    public void enterBiome(Biome biome) {
        System.out.println(this + " entered " + aOrAn(biome.getName()) + " biome");
        Game.currentBiome = biome;
    }

    private String aOrAn(String word) {
        return (("AEIOUaeiou".indexOf(word.charAt(0)) == -1) ? "a" : "an") + " " + word;
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

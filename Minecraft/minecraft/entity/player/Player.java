package minecraft.entity.player;

import minecraft.biome.Biome;
import minecraft.entity.Entity;
import minecraft.entity.EntityStatus;
import minecraft.game.Game;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.ItemWithDurability;

import java.util.Arrays;
import java.util.List;

public class Player extends Entity {
    private EntityStatus status = new EntityStatus(20);

    private String name;

    private Inventory inventory = new Inventory();
    private PlayerEquipment equipment = new PlayerEquipment();

    public Player(String name) {
        this.name = name;
    }

    public void pickUp(List<ItemStack> itemStacks) {
        inventory.addItemStacks(itemStacks);
        System.out.println(this + " picked up " + itemStacks);
    }

    public void damage(Entity other) {
        List<ItemStack> drops = other.damage(getAttackDamage());

        if (drops == null) {
            System.out.println(this + " hit " + other + " for " + getAttackDamage() + " damage");
        } else {
            System.out.println(this + " killed " + other + ", which dropped " + drops);
            pickUp(drops);
        }

        ((ItemWithDurability) equipment.getAttackTool()).damage();
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

    public PlayerEquipment getEquipment() {
        return equipment;
    }

    public int getAttackDamage() {
        return equipment.getAttackDamage();
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return null;
    }

    @Override
    public List<ItemStack> getDropItems() {
        return inventory.getItemStacks();
    }

    @Override
    public Entity copy() {
        return null;
    }

    @Override
    public String toString() {
        return "player " + name;
    }
}

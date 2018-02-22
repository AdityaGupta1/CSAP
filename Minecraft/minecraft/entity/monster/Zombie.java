package minecraft.entity.monster;

import minecraft.entity.Entity;
import minecraft.entity.creature.Villager;
import minecraft.game.Response;
import minecraft.game.ResponseType;
import minecraft.game.event.Event;
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
    public Event create() {
        return new Event("you keep a safe distance from the zombie",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.rotten_flesh, 1, 3));
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

package minecraft.entity.monster;

import minecraft.entity.Entity;
import minecraft.entity.EntityStatus;
import minecraft.entity.creature.Villager;
import minecraft.game.Game;
import minecraft.game.event.Response;
import minecraft.game.event.ResponseType;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Zombie extends Monster {
    private EntityStatus status = new EntityStatus(20);

    private boolean isVillager = false;

    public Zombie(boolean isVillager) {
        this.isVillager = isVillager;
    }

    public Zombie(EntityStatus status) {
        this.status = status.clone();
    }

    public Zombie(EntityStatus status, boolean isVillager) {
        this(status);
        this.isVillager = isVillager;
    }

    public Zombie convertVillager(Villager villager) {
        villager.setDead();
        return new Zombie(status, true);
    }

    @Override
    protected int getMinDamage() {
        return 2;
    }

    @Override
    protected int getMaxDamage() {
        return 4;
    }

    @Override
    protected double getFightChance() {
        return 0.1;
    }

    @Override
    protected String getHitMessage() {
        return this + " hit " + Game.player + "!";
    }

    @Override
    protected String getMissMessage() {
        return this + " tried to hit " + Game.player + " but missed";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return new Event("you keep a safe distance from the zombie",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("keep moving", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.rotten_flesh, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Zombie(status, isVillager);
    }

    @Override
    public String toString() {
        return "zombie" + (isVillager ? " villager" : "");
    }
}

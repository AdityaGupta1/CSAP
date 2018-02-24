package minecraft.entity.monster;

import minecraft.entity.EntityStatus;
import minecraft.game.Game;
import minecraft.game.event.EventGenerator;
import minecraft.game.event.Response;
import minecraft.game.event.ResponseType;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Creeper extends Monster {
    private EntityStatus status = new EntityStatus(20);

    private boolean isCharged = false;

    public Creeper() {

    }

    public Creeper(EntityStatus status) {
        this.status = status.clone();
    }

    public Creeper(EntityStatus status, boolean isCharged) {
        this.status = status;
        this.isCharged = isCharged;
    }

    @Override
    protected int getMinDamage() {
        return 3;
    }

    @Override
    protected int getMaxDamage() {
        return 5;
    }

    @Override
    protected double getFightChance() {
        return 0.2;
    }

    @Override
    protected String getHitMessage() {
        return this + " exploded!";
    }

    @Override
    protected String getMissMessage() {
        return this + " almost exploded, stopping just in time";
    }

    @Override
    public void fight() {
        if (EventGenerator.random(getFightChance())) {
            System.out.println(getHitMessage());
            Game.player.damage(getAttackDamage());
            this.setDead();
        } else {
            System.out.println(getMissMessage());
        }
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return new Event("you keep a safe distance from the creeper",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("keep moving", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.gunpowder, 1, 3));
        return dropItems;
    }

    @Override
    public Creeper clone() {
        return new Creeper(status, isCharged);
    }
}

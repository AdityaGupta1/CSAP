package minecraft.entity;

import minecraft.game.Response;
import minecraft.game.ResponseType;
import minecraft.game.event.Event;
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
    public Event create() {
        return new Event("you keep a safe distance from the creeper",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
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

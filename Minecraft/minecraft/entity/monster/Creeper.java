package minecraft.entity.monster;

import minecraft.entity.EntityStatus;
import minecraft.game.Response;
import minecraft.game.ResponseType;
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
        this.status = status;
    }

    public Creeper(EntityStatus status, boolean isCharged) {
        this.status = status;
        this.isCharged = isCharged;
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return new Event("you keep a safe distance from the creeper",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.gunpowder, 1, 3));
        return dropItems;
    }

    @Override
    public Creeper copy() {
        return new Creeper(status, isCharged);
    }
}

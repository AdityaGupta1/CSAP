package minecraft.entity.creature;

import minecraft.entity.Entity;
import minecraft.entity.EntityStatus;
import minecraft.game.event.Response;
import minecraft.game.event.ResponseType;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Pig extends Creature {
    private EntityStatus status = new EntityStatus(8);

    public Pig() {

    }

    public Pig(EntityStatus status) {
        this.status = status.clone();
    }

    @Override
    protected String getNormalMessage() {
        return "you look at the pig grazing";
    }

    @Override
    protected String getDamagedMessage() {
        return "you chase after the pig as it runs around";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_pork, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Pig(status);
    }
}

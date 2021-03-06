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

public class Squid extends Creature {
    private EntityStatus status = new EntityStatus(8);

    public Squid() {

    }

    public Squid(EntityStatus status) {
        this.status = status.clone();
    }

    @Override
    protected String getNormalMessage() {
        return "you look at the squid swimming lazily";
    }

    @Override
    protected String getDamagedMessage() {
        return "you chase after the squid as it swims away";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.ink_sac, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Squid(status);
    }
}

package minecraft.entity.creature;

import minecraft.entity.Entity;
import minecraft.entity.EntityStatus;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Villager extends Creature {
    private EntityStatus status = new EntityStatus(20);

    public Villager(EntityStatus status) {
        this.status = status.clone();
    }

    private List<VillagerTrade> trades = new ArrayList<>();

    public void addTrade(VillagerTrade trade) {
        trades.add(trade.setVillager(this));
    }

    public List<VillagerTrade> getTrades() {
        return trades;
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
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.emerald, 1));
        return dropItems;
    }

    @Override
    public Entity clone() {
        Villager villager = new Villager(status);

        for (VillagerTrade trade : trades) {
            villager.addTrade(trade);
        }

        return villager;
    }
}

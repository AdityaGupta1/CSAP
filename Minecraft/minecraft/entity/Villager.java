package minecraft.entity;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Villager extends Creature {
    private List<VillagerTrade> trades = new ArrayList<>();

    public Villager(String name) {
        super(name);
    }

    public void addTrade(VillagerTrade trade) {
        trades.add(trade.setVillager(this));
    }

    public List<VillagerTrade> getTrades() {
        return trades;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.emerald, 1));
        return dropItems;
    }
}

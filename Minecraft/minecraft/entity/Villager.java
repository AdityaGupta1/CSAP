package minecraft.entity;

import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Villager extends Creature {
    private String name;

    private List<VillagerTrade> trades = new ArrayList<>();

    public Villager(String name) {
        this.name = name;
    }

    public void addTrade(VillagerTrade trade) {
        trades.add(trade.setVillager(this));
    }

    public List<VillagerTrade> getTrades() {
        return trades;
    }

    public String getName() {
        return name;
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
    public Entity copy() {
        Villager villager = new Villager(name);

        for (VillagerTrade trade : trades) {
            villager.addTrade(trade);
        }

        return villager;
    }

    @Override
    public String toString() {
        return "villager named " + name;
    }
}

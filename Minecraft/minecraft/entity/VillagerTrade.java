package minecraft.entity;

import minecraft.item.Inventory;
import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VillagerTrade {
    private List<ItemStack> buy;
    private List<ItemStack> sell;

    private Villager villager;

    public VillagerTrade(List<ItemStack> buy, List<ItemStack> sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public VillagerTrade(ItemStack[] buy, ItemStack[] sell) {
        this(Arrays.asList(buy), Arrays.asList(sell));
    }

    public VillagerTrade setVillager(Villager villager) {
        this.villager = villager;
        return this;
    }

    public boolean tradeWith(Player player) {
        Inventory inventory = player.getInventory();

        if (!inventory.has(buy)) {
            System.out.println(player + " tried to trade with " + villager + " but lacked the items to do so");
            return false;
        }

        System.out.println(player + " traded " + villager + " " + buy + " in exchange for " + sell);

        inventory.subtract(buy);
        inventory.add(sell);

        return true;
    }
}

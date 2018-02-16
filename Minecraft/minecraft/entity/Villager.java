package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Villager extends Creature {
   ArrayList<VillagerTrade> trades = new ArrayList<>();

   public Villager(String name, List<VillagerTrade> trades) {
      super(name);
      this.trades.addAll(trades);
   }

   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>();
      dropItems.add(new ItemStack("emerald", 1));
      return dropItems;
   }
}

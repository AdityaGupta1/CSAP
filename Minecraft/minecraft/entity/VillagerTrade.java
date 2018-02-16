package minecraft.entity;

import minecraft.item.Inventory;
import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class VillagerTrade {
   private List<ItemStack> buy = new ArrayList<>();
   private List<ItemStack> sell = new ArrayList<>();;

   public VillagerTrade (List<ItemStack> buy, List<ItemStack> sell) {
      this.buy.addAll(buy);
      this.sell.addAll(sell);
   }

   public List<ItemStack> trade(Inventory inventory) {
      // TODO
      return null;
   }
}

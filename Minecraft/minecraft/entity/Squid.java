package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Squid extends Creature {
   public Squid(String name) {
      super(name);
   }

   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>();
      dropItems.add(new ItemStack("ink sac", 2));
      return dropItems;
   }
}

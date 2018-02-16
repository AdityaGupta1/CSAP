package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pig extends Creature {
   public Pig(String name) {
      super(name);
   }

   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>();
      dropItems.add(new ItemStack("raw porkchop", 2));
      return dropItems;
   }
}

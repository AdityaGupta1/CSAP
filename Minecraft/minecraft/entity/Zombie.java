package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Zombie extends Monster {
   private boolean isVillager = false;

   public Zombie(String name) {
      super(name);
   }

   public Zombie(String name, boolean isVillager) {
      this(name);
      this.isVillager = isVillager;
   }

   public Zombie convertVillager(Villager villager) {
      villager.die();
      return new Zombie(villager.getName(), true);
   }

   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>();
      dropItems.add(new ItemStack("rotten flesh", 2));
      return dropItems;
   }

   @Override
   public String toString() {
      return "zombie " + (isVillager ? "villager " : "") + "named " + name;
   }
}

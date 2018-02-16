import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HierarchyDemo_4MaGupta {
   public static void main(String[] args) {
      Player player = new Player("SDOAJ");
   
      Zombie zombie = new Zombie("jgnibo");
      Pig pig = new Pig("Mr. Piggy");
      
      player.kill(zombie);
      player.kill(pig);
            
      System.out.println("\n" + player.fullDescription());
      
      List<ItemStack> subtract = Arrays.asList(new ItemStack[]{new ItemStack(new Item("rotten flesh"), 1)});
      player.getInventory().subtract(subtract, true);
      
      System.out.println("\n" + subtract);
      System.out.println(player.fullDescription());
   }
}

class Item {
   private String name;
   
   public Item(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public boolean equals(Object other) {
      return (other instanceof Item) && (((Item) other).name == this.name);
   }
   
   @Override
   public String toString() {
      return name;
   }
}

class ItemStack {
   private Item item;
   private int amount;
   
   public ItemStack(Item item, int amount) {
      this.item = item;
      this.amount = amount;
   }
   
   public ItemStack(String name, int amount) {
      this(new Item(name), amount);
   }
   
   public Item getItem() {
      return item;
   }
   
   public int getAmount() {
      return amount;
   }
   
   @Override
   public boolean equals(Object other) {
      if (!(other instanceof ItemStack)) {
         return false;
      }
      
      ItemStack otherItemStack = (ItemStack) other;
   
      return (otherItemStack.item.equals(this.item)) && (otherItemStack.amount == this.amount);
   }
   
   public boolean has(ItemStack other) {
      if (!other.item.equals(this.item)) {
         return false;
      }
      
      return this.amount >= other.amount;
   }
   
   public boolean subtract(ItemStack other, boolean check) {
      if (check && !has(other)) {
         return false;
      }
      
      if (has(other)) {
         this.amount -= other.amount;
         other.amount = 0;
      } else {
         this.amount = 0;
         other.amount -= this.amount;
      }
      
      return true;
   }
   
   public boolean subtract(ItemStack other) {
      return subtract(other, true);
   }
   
   @Override
   public String toString() {
      return amount + "x " + item;
   }
}

class Inventory {
   private List<ItemStack> itemstacks = new ArrayList<>();
   
   public Inventory(List<ItemStack> itemstacks) {
      this.itemstacks.addAll(itemstacks);
   }
   
   public Inventory() {}
   
   public List<ItemStack> getItemStacks() {
      return itemstacks;
   }
   
   public void addItemStacks(List<ItemStack> itemstacks) {
      this.itemstacks.addAll(itemstacks);
   }
   
   public List<ItemStack> getConsolidated() {
      HashMap<Item, Integer> items = new HashMap<>();
      
      for (ItemStack itemstack : itemstacks) {
         Item item = itemstack.getItem();
         
         if (!items.keySet().contains(item)) {
            items.put(item, itemstack.getAmount());
         } else {
            items.put(item, items.get(item) + itemstack.getAmount());
         }
      }
      
      List<ItemStack> consolidated = new ArrayList<>();
      
      for (Item item : items.keySet()) {
         consolidated.add(new ItemStack(item, items.get(item)));
      }
      
      return consolidated;
   }
   
   public boolean has(List<ItemStack> itemstacks) {
      List<ItemStack> consolidated = getConsolidated();
      
      for (ItemStack otherStack : itemstacks) {
         boolean flag = false;
         
         for (ItemStack itemstack : consolidated) {
            if (itemstack.has(otherStack)) {
               flag = true;
            }
         }
         
         if (!flag) {
            return false;
         }
      }
      
      return true;
   }
   
   public boolean subtract(List<ItemStack> itemstacks, boolean check) {
      if (check && !has(itemstacks)) {
         return false;
      }
      
      for (ItemStack otherStack : itemstacks) {
         for (ItemStack itemstack : this.itemstacks) {
            itemstack.subtract(otherStack);
         }
      }

      return true;
   }
   
   public boolean subtract(List<ItemStack> itemstacks) {
      return subtract(itemstacks, true);
   }
   
   @Override
   public String toString() {
      String inventoryString = "";
      
      for (ItemStack itemstack : itemstacks) {
         if (itemstack.getAmount() <= 0) {
            continue;
         }
      
         inventoryString += itemstack + ",\n";
      }
      
      return inventoryString.substring(0, inventoryString.length() - 2);
   }
}

abstract class Entity {
   protected String name;
   protected boolean dead;
   
   public Entity(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
   
   public List<ItemStack> die() {
      if (this.dead) {
         return null;
      }
      
      this.dead = true;
      return getDropItems();
   }
   
   public abstract List<ItemStack> getDropItems();
   
   @Override
   public String toString() {
      return this.getClass().getName().toLowerCase() + " named " + name;
   } 
}

class Player extends Entity {
   private Inventory inventory = new Inventory();

   public Player(String name) {
      super(name);
   }
   
   public void pickUp(List<ItemStack> itemstacks) {
      inventory.addItemStacks(itemstacks);
   }
   
   public void kill(Entity other) {
      List<ItemStack> drops = other.die();
      pickUp(drops);
      System.out.println(this + " killed " + other + " and received " + drops);
   }
   
   public Inventory getInventory() {
      return inventory;
   }
   
   public String fullDescription() {
      return "player named " + name + " with items:\n" + inventory;
   } 
   
   @Override
   public List<ItemStack> getDropItems() {
      return inventory.getItemStacks();
   }
}

abstract class Creature extends Entity {
   public Creature(String name) {
      super(name);
   }
}

class Pig extends Creature { 
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

class Cow extends Creature {
   public Cow(String name) {
      super(name);
   }
   
   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>(); 
      dropItems.add(new ItemStack("raw beef", 2));
      dropItems.add(new ItemStack("leather", 1));
      return dropItems;
   }
}

class Squid extends Creature {
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

class Villager extends Creature {
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

class VillagerTrade {
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


abstract class Monster extends Entity {
   public Monster(String name) {
      super(name);
   }
}

class Creeper extends Monster {
   private boolean isCharged = false;

   public Creeper(String name) {
      super(name);
   }
   
   @Override
   public List<ItemStack> getDropItems() {
      ArrayList<ItemStack> dropItems = new ArrayList<>(); 
      dropItems.add(new ItemStack("gunpowder", 2));
      return dropItems;
   }
}

class Zombie extends Monster {
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
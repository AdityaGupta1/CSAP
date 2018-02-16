import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class HierarchyDemo_4MaGupta {
   public static void main(String[] args) {
      Player player = new Player("SDOAJ");
   
      Zombie zombie = new Zombie("jgnibo");
      Pig pig = new Pig("Mr. Piggy");
      
      player.kill(zombie);
      player.kill(pig);
      
      System.out.println();
      
      System.out.println(player.fullDescription());
   }
}

class Item {
   private String name;
   
   public Item(String name) {
      this.name = name;
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
   
   public Inventory(ItemStack... itemstacks) {
      this(Arrays.asList(itemstacks));
   }
   
   public Inventory() {}
   
   public List<ItemStack> getItemStacks() {
      return itemstacks;
   }
   
   public void addItemStacks(List<ItemStack> itemstacks) {
      this.itemstacks.addAll(itemstacks);
   }
   
   public void addItemStacks(ItemStack... itemstacks) {
      addItemStacks(Arrays.asList(itemstacks));
   }
   
   @Override
   public String toString() {
      String inventoryString = "";
      
      for (ItemStack itemstack : itemstacks) {
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
   
   public void pickUp(ItemStack... itemstacks) {
      inventory.addItemStacks(itemstacks);
   }
   
   public void kill(Entity other) {
      List<ItemStack> drops = other.die();
      pickUp(drops);
      System.out.println(this + " killed " + other + " and received " + drops);
   }
   
   @Override
   public List<ItemStack> getDropItems() {
      return inventory.getItemStacks();
   }
   
   public String fullDescription() {
      return "player named " + name + " with items:\n" + inventory;
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
   
   public Villager(String name, VillagerTrade... trades) {
      this(name, Arrays.asList(trades));
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
   
   public List<ItemStack> trade(ArrayList<ItemStack> items) {
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
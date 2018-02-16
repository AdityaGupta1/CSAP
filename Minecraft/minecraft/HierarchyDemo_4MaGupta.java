package minecraft;

import minecraft.entity.*;
import minecraft.item.ItemStack;
import minecraft.item.Items;

public class HierarchyDemo_4MaGupta {
    public static void main(String[] args) {
        Player player = new Player("SDOAJ");

        Zombie zombie = new Zombie("jgnibo");
        Pig pig = new Pig("Mr. Piggy");
        Cow cow = new Cow("Thomas the Tank Engine");

        Villager villager = new Villager("dank");
        villager.addTrade(new VillagerTrade(
                new ItemStack[]{new ItemStack(Items.raw_pork, 2)},
                new ItemStack[]{new ItemStack(Items.emerald, 3)}));
        villager.addTrade(new VillagerTrade(
                new ItemStack[]{new ItemStack(Items.rotten_flesh, 2), new ItemStack(Items.leather, 1)},
                new ItemStack[]{new ItemStack(Items.cooked_beef, 2)}));

        player.kill(zombie, pig, cow);

        print();
        villager.getTrades().get(0).tradeWith(player);
        villager.getTrades().get(1).tradeWith(player);
        print(player.fullDescription());
    }

    private static void print(Object message, boolean newline) {
        System.out.println((newline ? "\n" : "") + message);
    }

    private static void print(Object message) {
        print(message, false);
    }

    private static void print() {
        print("");
    }
}



package minecraft.game.crafting;

import minecraft.game.Game;
import minecraft.item.Inventory;
import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftingRecipe {
    private List<ItemStack> ingredients;
    private ItemStack result;

    CraftingRecipe(ItemStack result, ItemStack... ingredients) {
        this.result = result;
        this.ingredients = Arrays.asList(ingredients);
    }

    public boolean canCraft() {
        return Game.player.getInventory().has(ingredients);
    }

    public void craft() {
        Inventory inventory = Game.player.getInventory();

        if (!canCraft()) {
            return;
        }

        System.out.println(Game.player + " crafted " + result + " from " + ingredients);

        inventory.subtract(ItemStack.copyStacks(ingredients));
        inventory.add(result);
    }

    @Override
    public String toString() {
        return ingredients + " ---> [" + result + "]";
    }
}

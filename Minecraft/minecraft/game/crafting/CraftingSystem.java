package minecraft.game.crafting;

import minecraft.game.Util;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftingSystem {
    private CraftingRecipe[] craftingRecipes = {
            new CraftingRecipe(new ItemStack(Items.planks, 4), new ItemStack(Items.log, 1)),
            new CraftingRecipe(new ItemStack(Items.stick, 4), new ItemStack(Items.planks, 2)),
            new CraftingRecipe(new ItemStack(Items.boat, 1), new ItemStack(Items.planks, 5)),

            new CraftingRecipe(new ItemStack(Items.wooden_sword, 1), new ItemStack(Items.stick, 1),
                    new ItemStack(Items.planks, 2)),
            new CraftingRecipe(new ItemStack(Items.test_sword, 1), new ItemStack(Items.log, 1)),

            new CraftingRecipe(new ItemStack(Items.sandstone, 1), new ItemStack(Items.sand, 4))
    };

    public List<CraftingRecipe> getAvailableRecipes() {
        List<CraftingRecipe> availableRecipes = new ArrayList<>();

        for (CraftingRecipe recipe : Arrays.asList(craftingRecipes)) {
            if (recipe.canCraft()) {
                availableRecipes.add(recipe);
            }
        }

        return availableRecipes;
    }

    public String getAvailableRecipesString() {
        List<CraftingRecipe> availableRecipes = getAvailableRecipes();
        String recipesString = "";

        if (availableRecipes.size() == 0) {
            recipesString = "none";
        } else {
            for (int i = 0; i < availableRecipes.size(); i++) {
                recipesString += (i + 1) + ") " + availableRecipes.get(i) + "\n";
            }
        }

        return "available recipes:" + Util.wrap(recipesString.substring(0, recipesString.length() - 1), true);
    }
}

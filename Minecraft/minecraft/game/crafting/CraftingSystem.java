package minecraft.game.crafting;

import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftingSystem {
    private CraftingRecipe[] craftingRecipes = {
            new CraftingRecipe(new ItemStack(Items.planks, 4), new ItemStack(Items.log, 1)),
            new CraftingRecipe(new ItemStack(Items.stick, 4), new ItemStack(Items.planks, 2))
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
        String line = "--------------------------------------------------------------\n";

        List<CraftingRecipe> availableRecipes = getAvailableRecipes();
        String recipesString = "available recipes:\n" + line;

        if (availableRecipes.size() == 0) {
            return recipesString + "none\n" + line;
        }

        for (int i = 0; i < availableRecipes.size(); i++) {
            recipesString += (i + 1) + ") " + availableRecipes.get(i) + "\n";
        }

        recipesString += line;

        return recipesString;
    }
}

package minecraft.game;

import minecraft.game.crafting.CraftingRecipe;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserInterface implements Runnable {
    private Scanner scanner = new Scanner(System.in);

    private HashMap<String, Boolean> shouldPrintLines = new HashMap<>();

    UserInterface() {
        shouldPrintLines.put("h", true);
        shouldPrintLines.put("r", false);
        shouldPrintLines.put("i", true);
        shouldPrintLines.put("c", true);
        shouldPrintLines.put("e", true);
        shouldPrintLines.put("s", true);
    }

    void showEvent() {
        Event event = EventGenerator.generate();

        System.out.println(event.getMessage());

        String input;
        do {
            System.out.println(event.getChoices());
            input = scanner.nextLine();

            if (shouldPrintLines.containsKey(input)) {
                String wrapper = shouldPrintLines.get(input) ? "\n" : "";
                System.out.print(wrapper);
                runCommand(input);
                System.out.print(wrapper);
                System.out.println(event.getMessage());
            }
        } while (event.getResponseFromInput(input) == null);

        event.getResponseFromInput(input).doConsequence();
        System.out.println();
    }

    private void runCommand(String input) {
        command:
        switch (input.toLowerCase()) {
            case "g":
                System.out.println(Game.controlsMessage);
                break;
            case "r":
                break;
            case "i":
                Game.player.displayFull();
                break;
            case "c":
                System.out.print(Game.craftingSystem.getAvailableRecipesString());
                List<CraftingRecipe> availableRecipes = Game.craftingSystem.getAvailableRecipes();

                int recipe = 0;
                do {
                    System.out.println("type a recipe's number to craft it or \"e\" to exit");
                    String stringInput = scanner.nextLine();

                    if (stringInput.equals("e")) {
                        break command;
                    }

                    try {
                        recipe = Integer.parseInt(stringInput);
                    } catch (Exception e) {}

                    if (recipe >= availableRecipes.size() + 1) {
                        recipe = 0;
                    }
                } while(recipe == 0);

                availableRecipes.get(recipe - 1).craft();
                break;
            case "e":
                System.out.println("equipment not implemented yet");
                break;
            case "s":
                System.out.println("status not implemented yet");
                break;
            default:
                System.out.println("unknown command");
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            showEvent();
        }
    }
}

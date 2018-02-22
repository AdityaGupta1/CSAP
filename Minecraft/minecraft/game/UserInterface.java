package minecraft.game;

import minecraft.entity.player.PlayerEquipment;
import minecraft.game.crafting.CraftingRecipe;
import minecraft.game.event.Event;
import minecraft.game.event.EventGenerator;
import minecraft.item.EquipmentType;
import minecraft.item.ItemEquipment;

import java.util.*;

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
        shouldPrintLines.put("q", true);
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
            case "h":
                System.out.println(Game.controlsMessage);
                break;
            case "r":
                break;
            case "i":
                Game.player.displayFull();
                break;
            case "c":
                System.out.println(Game.craftingSystem.getAvailableRecipesString());
                List<CraftingRecipe> availableRecipes = Game.craftingSystem.getAvailableRecipes();
                if (availableRecipes.size() == 0) {
                    break;
                }

                int recipe = 0;
                do {
                    System.out.println("type a recipe's number to craft it or \"e\" to exit");
                    String stringInput = scanner.nextLine();

                    if (stringInput.equals("e")) {
                        break command;
                    }

                    try {
                        recipe = Integer.parseInt(stringInput);
                    } catch (Exception e) {
                        // recipe = 0;
                    }

                    if (recipe > availableRecipes.size()) {
                        recipe = 0;
                    }
                } while(recipe == 0);

                availableRecipes.get(recipe - 1).craft();
                break;
            case "e":
                PlayerEquipment playerEquipment = Game.player.getEquipment();
                System.out.println(playerEquipment);
                Map<EquipmentType, List<ItemEquipment>> equipmentMap = Game.player.getInventory().getEquipment();
                if (equipmentMap == null) {
                    break;
                }

                List<ItemEquipment> equipment = new ArrayList<>();
                for (EquipmentType type : equipmentMap.keySet()) {
                    List<ItemEquipment> equipmentOfType = equipmentMap.get(type);

                    if (equipmentOfType.size() == 0) {
                        continue;
                    }

                    System.out.println(Util.wrap(type, true, false));

                    for (ItemEquipment singleEquipment : equipmentOfType) {
                        equipment.add(singleEquipment);
                        System.out.println(equipment.size() + ") " + singleEquipment);
                    }
                }

                System.out.println();

                int equipmentChoice = 0;
                do {
                    System.out.println("type an item's number to equip it or \"e\" to exit");
                    String stringInput = scanner.nextLine();

                    if (stringInput.equals("e")) {
                        break command;
                    }

                    try {
                        equipmentChoice = Integer.parseInt(stringInput);
                    } catch (Exception e) {
                        // recipe = 0;
                    }

                    if (equipmentChoice > equipment.size()) {
                        equipmentChoice = 0;
                    }
                } while(equipmentChoice == 0);

                playerEquipment.swap(equipment.get(equipmentChoice - 1));
                break;
            case "s":
                System.out.println("status not implemented yet");
                break;
            case "q":
                System.out.println(Util.wrap("thanks for playing!"));
                System.exit(0);
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

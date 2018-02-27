package minecraft.game;

import minecraft.entity.player.PlayerEquipment;
import minecraft.game.crafting.CraftingRecipe;
import minecraft.game.event.Event;
import minecraft.game.event.EventGenerator;
import minecraft.game.mining.Mine;
import minecraft.item.EquipmentType;
import minecraft.item.ItemEquipment;

import java.util.*;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    static final String controlsMessage = "commands: H = help, R = replay event, I = inventory, " +
            "C = crafting, E = equipment, S = status, M = mining, O = return to overworld from mining, " +
            "Q = quit game";
    private List<String> validCommands = Arrays.asList("h", "?", "help", "r", "i", "c", "e", "s", "m", "o", "q");

    private boolean reset = false;

    private boolean mining = false;

    void showEvent() {
        Event event = EventGenerator.generate();

        String input;
        do {
            if (reset) {
                reset = false;
                showEvent();
                return;
            }

            System.out.println(event.getMessage());
            System.out.println(event.getChoices());
            input = scanner.nextLine();

            if (validCommands.contains(input)) {
                System.out.println();
                runCommand(input);
                System.out.println();
            }
        } while (event.getResponseFromInput(input) == null);

        event.getResponseFromInput(input).doConsequence();
        System.out.println();
    }

    public void reset() {
        reset = true;
    }

    private void runCommand(String input) {
        command:
        switch (input.toLowerCase()) {
            case "h":
            case "?":
            case "help":
                System.out.println(controlsMessage);
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
                    System.out.println("type a recipe's number to craft it or \"x\" to exit");
                    String stringInput = scanner.nextLine();

                    if (stringInput.equals("x")) {
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

                    System.out.println(PrintUtils.wrap(type.toString().toLowerCase(), true, false));

                    for (ItemEquipment singleEquipment : equipmentOfType) {
                        equipment.add(singleEquipment);
                        System.out.println(equipment.size() + ") " + singleEquipment);
                    }
                }

                System.out.println();

                int equipmentChoice = 0;
                do {
                    System.out.println("type an item's number to equip it or \"x\" to exit");
                    String stringInput = scanner.nextLine();

                    if (stringInput.equals("x")) {
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
                System.out.println(Game.player);
                break;
            case "m":
                if (Game.player.getEquipment().get(EquipmentType.PICKAXE) == null) {
                    System.out.println("you need a pickaxe equipped to go mining");
                    System.out.println("type \"e\" to open the equipment menu");
                    break;
                }

                EventGenerator.changeEventCreator(new Mine());
                mining = true;
                System.out.println("entered mine");
                System.out.println("type \"o\" to return to the overworld");
                break;
            case "o":
                if (mining) {
                    EventGenerator.resetEventCreatorToBiome();
                    mining = false;
                    System.out.println("returned to overworld");
                } else {
                    System.out.println("already in overworld");
                }

                break;
            case "q":
                System.out.println(PrintUtils.wrap("thanks for playing!"));
                System.exit(0);
                break;
            default:
                System.out.println("unknown command \"" + input + "\"");
                break;
        }
    }
}

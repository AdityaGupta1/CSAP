// Inherit-A2: Demo Hierarchy
// Aditya Gupta and Marcus Ma

import java.util.*;

public class HierarchyDemo_4MaGupta {
    public static void main(String[] args) {
        try {
            (new Game()).startLoop();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}

class Game {
    private boolean run = true;
    private long ticks = 0;

    public static final String controlsMessage = "commands: H = help, R = replay event, I = inventory, C = crafting, E = equipment, S = status, Q = quit";

    public static final Player player = new Player("SDOAJ");
    public static Biome currentBiome = new BiomeForest();

    private static final UserInterface ui = new UserInterface();
    public static final CraftingSystem craftingSystem = new CraftingSystem();

    private static final Sky sky = new Sky();

    public static final Random random = new Random();

    public void startLoop() throws InterruptedException {
        System.out.println(controlsMessage + "\n");

        (new Thread(ui)).start();

        while(run) {
            // 1/20 second real time --> 3 seconds minecraft time
            if (ticks % 20 == 0) {
                sky.incrementTime(1);

                if (getTime() % 60 == 0) {
                    System.out.println("( " + Game.getTimeString() + " )");
                }
            }

            Thread.sleep(50);
            ticks++;
        }
    }

    public void stopLoop() {
        run = false;
    }

    public static int getTime() {
        return sky.getTime();
    }

    public static String getTimeString() {
        return sky.toString();
    }

    public static boolean isNight() {
        return sky.isNight();
    }
}

class UserInterface implements Runnable {
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

                    System.out.println(Util.wrap(type.toString().toLowerCase(), true, false));

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

class Util {
    private static final String line = "--------------------------------------------------------------";

    public static String line(boolean newlineBefore, boolean newlineAfter) {
        return (newlineBefore ? "\n" : "") + line + (newlineAfter ? "\n" : "");
    }

    public static String line(boolean newlines) {
        return line(newlines, newlines);
    }

    public static String line() {
        return line(false);
    }

    public static String wrap(Object object, boolean newlineBefore, boolean newlineAfter) {
        return line(newlineBefore, true) + object + line(true, newlineAfter);
    }

    public static String wrap(Object object, boolean newlines) {
        return wrap(object, newlines, newlines);
    }

    public static String wrap(Object object) {
        return wrap(object, false);
    }
}

class ChanceRange {
    private double min;
    private double max;

    public ChanceRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(double chance) {
        return chance > min && chance <= max;
    }

    @Override
    public String toString() {
        return min + " to " + max;
    }
}

class Event {
    public static final Event TREES = new Event("you see trees",
            new Response("chop them down", ResponseType.GET_ITEM, EquipmentType.AXE, new ItemStack(Items.log, 4, 6)),
            new Response("ignore them", ResponseType.IGNORE));
    public static final Event SAND = new Event("you see sand",
            new Response("dig some sand", ResponseType.GET_ITEM, EquipmentType.SHOVEL, new ItemStack(Items.sand, 15, 25)),
            new Response("ignore it", ResponseType.IGNORE));
    public static final Event CACTUS = new Event("you see a cactus",
            new Response("chop it down", ResponseType.GET_ITEM, null, new ItemStack(Items.cactus, 1, 4)),
            new Response("ignore it", ResponseType.IGNORE));

    public static final Event PIG = new Event("you see a pig",
            new Response("fight it", ResponseType.CHANGE, new Pig(), "you engage the pig"),
            new Response("ignore it", ResponseType.IGNORE));
    public static final Event COW  = new Event("you see a cow",
            new Response("fight it", ResponseType.CHANGE, new Cow(), "you engage the cow"),
            new Response("ignore it", ResponseType.IGNORE));
    public static final Event SQUID = new Event("you see a squid",
            new Response("fight it", ResponseType.CHANGE, new Squid(), "you engage the squid"),
            new Response("ignore it", ResponseType.IGNORE));

    public static final Event ZOMBIE = new Event("you see a zombie",
            new Response("fight it", ResponseType.CHANGE, new Zombie(false), "you engage the zombie"),
            new Response("run away", ResponseType.IGNORE));
    public static final Event CREEPER = new Event("you see a creeper",
            new Response("fight it", ResponseType.CHANGE, new Creeper(), "you engage the creeper"),
            new Response("run away", ResponseType.IGNORE));

    private final String message;
    private final Response[] responses;
    public Event(String message, Response... responses) {
        this.message = "[ " + message + " ]";
        this.responses = responses;
    }

    public String getMessage() {
        return message;
    }

    public Response[] getResponses() {
        return responses;
    }

    public String getChoices() {
        String choicesString = "";

        for (int i = 0; i < responses.length; i++) {
            choicesString += (i + 1) + ") " + responses[i].getInput() + "\n";
        }

        return choicesString.substring(0, choicesString.length() - 1);
    }

    public Response getResponseFromInput(String input) {
        int inputNumber;

        try {
            inputNumber = Integer.parseInt(input) - 1;
        } catch(NumberFormatException exception) {
            return null;
        }

        if (inputNumber >= responses.length || inputNumber < 0) {
            return null;
        }

        return responses[inputNumber];
    }
}

class EventChance {
    private Event event;
    private double chance;

    public EventChance(Event event, double chance) {
        this.event = event;
        this.chance = chance;
    }

    public static EventChance[] generateChances(Event[] events, double chance) {
        EventChance[] eventChances = new EventChance[events.length];
        double singleChance = chance / events.length;

        for (int i = 0; i < events.length; i++) {
            eventChances[i] = new EventChance(events[i], singleChance);
        }

        return eventChances;
    }

    public Event getEvent() {
        return event;
    }

    public double getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return event + " with " + Math.round(100 * chance) + "% chance";
    }
}

interface EventCreator {
    Event create();
    EventCreator clone();
}

class EventGenerator {
    public static final Event[] CREATURES = {Event.PIG, Event.COW};
    public static final Event[] MONSTERS = {Event.ZOMBIE, Event.CREEPER};
    public static final Event[] OCEAN = {Event.SQUID};

    private static final Biome[] BIOMES = {new BiomeForest(), new BiomeDesert(), new BiomeOcean()};

    public static EventCreator eventCreator = Game.currentBiome;

    public static Event generate() {
        return eventCreator.create();
    }

    public static void changeEventCreator(EventCreator newEventCreator) {
        eventCreator = newEventCreator;
    }

    public static void resetEventCreator() {
        changeEventCreator(Game.currentBiome);
    }

    public static Biome pickRandomNewBiome() {
        Biome biome;

        do {
            biome = BIOMES[(int) (Math.random() * BIOMES.length)];
        } while (Game.currentBiome.equals(biome));

        return biome;
    }

    public static boolean random(double probability) {
        return Game.random.nextDouble() < probability;
    }
}

class Response {
    private String input;
    private ResponseType type;
    private Object[] consequences;

    public Response(String input, ResponseType type, Object... consequences) {
        this.input = input;
        this.type = type;
        this.consequences = consequences;
    }

    public String getInput() {
        return input;
    }

    public void doConsequence() {
        Player player = Game.player;

        switch(type) {
            case GET_ITEM:
                List<ItemStack> itemStacks = new ArrayList<>();

                for (int i = 1; i < consequences.length; i++) {
                    itemStacks.add(((ItemStack) consequences[i]).generate());
                }

                EquipmentType type = (EquipmentType) consequences[0];
                double modifier = player.getEquipment().getModifier(type);

                for (ItemStack itemStack : itemStacks) {
                    itemStack.multiplyAmount(modifier);

                    if (modifier != 1) {
                        ((ItemWithDurability) player.getEquipment().get(type)).damage(itemStack.getAmount());
                    }
                }

                player.pickUp(itemStacks);
                break;
            case FIGHT:
                Entity entity = ((Entity) consequences[0]);

                player.damage(entity);

                if (entity.isDead()) {
                    EventGenerator.resetEventCreator();
                    break;
                }

                if (entity instanceof Monster) {
                    ((Monster) entity).fight();
                }

                break;
            case ENTER_BIOME:
                player.enterBiome((Biome) consequences[0]);
                EventGenerator.changeEventCreator(Game.currentBiome);
                break;
            case CHANGE:
                EventGenerator.changeEventCreator(((EventCreator) consequences[0]).clone());
                System.out.println(consequences[1]);
                break;
            case IGNORE:
            default:
                break;
        }
    }
}

enum ResponseType {
    GET_ITEM,
    FIGHT,
    ENTER_BIOME,
    CHANGE,
    IGNORE
}

class WeightedEvents {
    private List<EventChance> events = new ArrayList<>();
    private Map<Event, ChanceRange> eventChances = new HashMap<>();

    public void add(EventChance... eventChances) {
        events.addAll(Arrays.asList(eventChances));
        generateChances();
    }

    private void generateChances() {
        double bound = 0;
        for (EventChance event : events) {
            eventChances.put(event.getEvent(), new ChanceRange(bound, bound += event.getChance()));
        }
    }

    public Event generateEvent() {
        double random = Game.random.nextDouble();

        for (Event event : eventChances.keySet()) {
            if (eventChances.get(event).isInRange(random)) {
                return event;
            }
        }

        return null;
    }
}

class CraftingRecipe {
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
        if (!canCraft()) {
            return;
        }

        Inventory inventory = Game.player.getInventory();

        System.out.println(Game.player + " crafted [" + result + "] from " + ingredients);

        inventory.subtract(ingredients);
        inventory.add(result);
    }

    @Override
    public String toString() {
        return ingredients + " ---> [" + result + "]";
    }
}

class CraftingSystem {
    private CraftingRecipe[] craftingRecipes = {
            new CraftingRecipe(new ItemStack(Items.planks, 4), new ItemStack(Items.log, 1)),
            new CraftingRecipe(new ItemStack(Items.stick, 4), new ItemStack(Items.planks, 2)),
            new CraftingRecipe(new ItemStack(Items.boat, 1), new ItemStack(Items.planks, 5)),

            new CraftingRecipe(new ItemStack(Items.wooden_sword, 1), new ItemStack(Items.stick, 1),
                    new ItemStack(Items.planks, 2)),
            new CraftingRecipe(new ItemStack(Items.wooden_axe, 1), new ItemStack(Items.stick, 2),
                    new ItemStack(Items.planks, 3)),
            new CraftingRecipe(new ItemStack(Items.wooden_pickaxe, 1), new ItemStack(Items.stick, 2),
                    new ItemStack(Items.planks, 3)),

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
            recipesString = "none ";
        } else {
            for (int i = 0; i < availableRecipes.size(); i++) {
                recipesString += (i + 1) + ") " + availableRecipes.get(i) + "\n";
            }
        }

        return "available recipes:" + Util.wrap(recipesString.substring(0, recipesString.length() - 1), true);
    }
}

class Sky {
    // 0000 to 1440
    // 1 second real time --> 1 minute minecraft time
    private int time = 360 - 1;

    public void incrementTime(int time) {
        this.time += time;

        if (this.time >= 1440) {
            this.time = this.time % 1440;
        }
    }

    public int getTime() {
        return time;
    }

    public boolean isNight() {
        // 19:00 to 3:00
        return time > 1140 || time < 300;
    }

    @Override
    public String toString() {
        return String.format("%02d", (time / 60)) + ":" + String.format("%02d", (time % 60));
    }
}

abstract class Biome implements EventCreator {
    public abstract String getEnterMessage();

    public double getLeaveChance() {
        return 0.05;
    }

    // example: "BiomeExtremeHills" --> "extreme hills"
    public String getName() {
        String name = "";

        for (String word : this.getClass().getSimpleName()
                .split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            if (!word.equalsIgnoreCase("biome")) {
                name += word.toLowerCase() + " ";
            }
        }
        return name.substring(0, name.length() - 1);
    }

    public Event getEnterEvent() {
        return new Event(this.getEnterMessage(),
                new Response("enter the " + this.getName(), ResponseType.ENTER_BIOME, this),
                new Response("ignore it", ResponseType.IGNORE));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Biome) && ((Biome) other).getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return getName();
    }

    public abstract EventCreator clone();
}

class BiomeDesert extends Biome {
    @Override
    public Event create() {
        if (EventGenerator.random(Game.currentBiome.getLeaveChance())) {
            return EventGenerator.pickRandomNewBiome().getEnterEvent();
        }

        WeightedEvents events = new WeightedEvents();

        if (!Game.isNight()) {
            events.add(new EventChance(Event.SAND, 0.5));
            events.add(new EventChance(Event.CACTUS, 0.5));
        } else {
            events.add(EventChance.generateChances(EventGenerator.MONSTERS, 1));
        }

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see sand and cacti, signaling a desert";
    }

    @Override
    public EventCreator clone() {
        return new BiomeDesert();
    }
}

class BiomeForest extends Biome {
    @Override
    public Event create() {
        if (EventGenerator.random(Game.currentBiome.getLeaveChance())) {
            return EventGenerator.pickRandomNewBiome().getEnterEvent();
        }

        WeightedEvents events = new WeightedEvents();

        if (!Game.isNight()) {
            events.add(new EventChance(Event.TREES, 0.5));
            events.add(EventChance.generateChances(EventGenerator.CREATURES, 0.5));
        } else {
            events.add(new EventChance(Event.TREES, 0.5));
            events.add(EventChance.generateChances(EventGenerator.MONSTERS, 0.5));
        }

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see trees and hills covering a peaceful forest";
    }

    @Override
    public EventCreator clone() {
        return new BiomeForest();
    }
}

class BiomeOcean extends Biome {
    @Override
    public Event create() {
        if (EventGenerator.random(Game.currentBiome.getLeaveChance())) {
            return EventGenerator.pickRandomNewBiome().getEnterEvent();
        }

        WeightedEvents events = new WeightedEvents();

        events.add(EventChance.generateChances(EventGenerator.OCEAN, 1));

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see an ocean, with water extending as far as the eye can see";
    }

    @Override
    public double getLeaveChance() {
        return Game.player.getInventory().has(Items.boat) ? 0.05 : 0.01;
    }

    @Override
    public EventCreator clone() {
        return new BiomeOcean();
    }
}

abstract class Entity implements EventCreator, Cloneable {
    public abstract EntityStatus getStatus();

    public List<ItemStack> damage(int damage) {
        getStatus().damage(damage);

        if (getStatus().isDead()) {
            return ItemStack.removeEmpty(getDropItems());
        }

        return null;
    }

    public boolean isDead() {
        return getStatus().isDead();
    }

    public void setDead(boolean dead) {
        getStatus().setDead(dead);
    }

    public void setDead() {
        setDead(true);
    }

    public abstract List<ItemStack> getDropItems();

    @Override
    public abstract Entity clone();

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}

class EntityStatus implements Cloneable {
    private boolean dead = false;

    private final int maxHealth;
    private int health;

    public EntityStatus(int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

    public EntityStatus(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public void damage(int damage) {
        if (dead) {
            return;
        }

        this.health -= damage;

        if (this.health <= 0) {
            this.health = 0;
            this.dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public EntityStatus clone() {
        return new EntityStatus(this.maxHealth, this.health);
    }

    @Override
    public String toString() {
        return dead ? "dead" : "health: " + health + "/" + maxHealth;
    }
}

class Inventory {
    private List<ItemStack> itemStacks = new ArrayList<>();

    public Inventory(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(itemStacks);
    }

    public Inventory() {
    }

    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public void addItemStacks(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(itemStacks);
    }

    private void update() {
        List<ItemStack> newStacks = new ArrayList<>();

        for (ItemStack itemStack : itemStacks) {
            if (!itemStack.isEmpty()) {
                newStacks.add(itemStack);
            }
        }

        itemStacks = newStacks;
    }

    public List<ItemStack> getConsolidated() {
        update();

        HashMap<Item, Integer> items = new HashMap<>();
        List<ItemStack> singletons = new ArrayList<>();

        for (ItemStack itemstack : itemStacks) {
            Item item = itemstack.getItem();

            if (item.isSingleton()) {
                singletons.add(itemstack);
                continue;
            }

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

        consolidated.addAll(singletons);

        return consolidated;
    }

    public Map<EquipmentType, List<ItemEquipment>> getEquipment() {
        update();

        Map<EquipmentType, List<ItemEquipment>> equipment = new HashMap<>();
        for (EquipmentType type : EquipmentType.values()) {
            equipment.put(type, new ArrayList<>());
        }

        boolean hasEquipment = false;

        for (ItemStack itemStack : itemStacks) {
            if (itemStack.getItem() instanceof ItemEquipment) {
                ItemEquipment item = (ItemEquipment) itemStack.getItem();
                equipment.get(item.getType()).add(item);
                hasEquipment = true;
            }
        }

        return hasEquipment ? equipment : null;
    }

    public boolean has(List<ItemStack> itemStacks) {
        update();

        List<ItemStack> consolidated = getConsolidated();

        for (ItemStack otherStack : itemStacks) {
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

    public boolean has(Item item) {
        return has(Collections.singletonList(new ItemStack(item, 1)));
    }

    public boolean subtract(List<ItemStack> itemStacks) {
        update();

        itemStacks = new ArrayList<>(itemStacks);

        if (!has(itemStacks)) {
            return false;
        }

        outside:
        for (ItemStack otherStack : itemStacks) {
            for (ItemStack itemstack : this.itemStacks) {
                if (otherStack == null) {
                    continue outside;
                }

                otherStack = itemstack.subtract(otherStack);
            }
        }

        return true;
    }

    public boolean subtract(ItemStack... itemStacks) {
        return subtract(Arrays.asList(Arrays.copyOf(itemStacks, itemStacks.length)));
    }

    public void add(List<ItemStack> itemStacks) {
        this.itemStacks.addAll(new ArrayList<>(itemStacks));
    }

    public void add(ItemStack... itemStacks) {
        for (ItemStack itemStack : itemStacks) {
            this.itemStacks.add(itemStack.copy());
        }
    }

    public boolean isEmpty() {
        update();

        return itemStacks.size() == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "none";
        }

        String inventoryString = "";

        for (ItemStack itemstack : getConsolidated()) {
            if (itemstack.getAmount() <= 0) {
                continue;
            }

            inventoryString += itemstack + ",\n";
        }

        return inventoryString.substring(0, inventoryString.length() - 2);
    }
}

class Player extends Entity {
    private EntityStatus status = new EntityStatus(20);

    private String name;

    private Inventory inventory = new Inventory();
    private PlayerEquipment equipment = new PlayerEquipment();

    public Player(String name) {
        this.name = name;
    }

    public void pickUp(List<ItemStack> itemStacks) {
        inventory.addItemStacks(itemStacks);
        System.out.println(this + " picked up " + itemStacks);
    }

    public List<ItemStack> damage(int damage) {
        System.out.println(this + " took " + damage + " damage");
        List<ItemStack> drops = super.damage(damage);
        System.out.println(this + "'s health: " + this.getStatus());
        return drops;
    }

    public void damage(Entity other) {
        List<ItemStack> drops = other.damage(getAttackDamage());

        if (drops == null) {
            System.out.println(this + " hit " + other + " for " + getAttackDamage() + " damage");
            System.out.println(other + "'s " + other.getStatus());
        } else {
            System.out.println(this + " killed " + other + ", which dropped " + drops);
            pickUp(drops);
        }

        ItemWithDurability attackTool = ((ItemWithDurability) equipment.getAttackTool());

        if (attackTool != null) {
            attackTool.damage();
        }
    }

    public void enterBiome(Biome biome) {
        System.out.println(this + " entered " + aOrAn(biome.getName()) + " biome");
        Game.currentBiome = biome;
    }

    private String aOrAn(String word) {
        return (("AEIOUaeiou".indexOf(word.charAt(0)) == -1) ? "a" : "an") + " " + word;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String fullDescription() {
        return "player named " + name + " with items:\n" + inventory;
    }

    public void displayFull() {
        System.out.println(fullDescription());
    }

    public PlayerEquipment getEquipment() {
        return equipment;
    }

    public int getAttackDamage() {
        return equipment.getAttackDamage();
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return null;
    }

    @Override
    public List<ItemStack> getDropItems() {
        return inventory.getItemStacks();
    }

    @Override
    public Entity clone() {
        return null;
    }

    @Override
    public String toString() {
        return "player " + name;
    }
}

class PlayerEquipment {
    private ItemEquipment sword;
    private ItemEquipment axe;
    private ItemEquipment pickaxe;
    private ItemEquipment shovel;
    private ItemEquipment helmet;
    private ItemEquipment chestplate;
    private ItemEquipment leggings;
    private ItemEquipment boots;

    PlayerEquipment() {
        // package-private
    }

    public ItemEquipment getAttackTool() {
        ItemEquipment[] attackTools = {sword, axe, pickaxe};

        for (ItemEquipment attackTool : attackTools) {
            if (attackTool != null) {
                return attackTool;
            }
        }

        return null;
    }

    public int getAttackDamage() {
        ItemEquipment attackTool = getAttackTool();

        if (attackTool != null) {
            return attackTool.getAttackDamage();
        }

        return 1;
    }

    public ItemEquipment get(EquipmentType type) {
        ItemEquipment item;

        switch(type) {
            case SWORD:
                item = sword;
                break;
            case AXE:
                item = axe;
                break;
            case PICKAXE:
                item = pickaxe;
                break;
            case SHOVEL:
                item = shovel;
                break;
            case HELMET:
                item = helmet;
                break;
            case CHESTPLATE:
                item = chestplate;
                break;
            case LEGGINGS:
                item = leggings;
                break;
            case BOOTS:
                item = boots;
                break;
            default:
                return null;
        }

        if (item == null) {
            return null;
        }

        if (((ItemWithDurability) item).isBroken()) {
            set(type, null);
            return null;
        }

        return item;
    }

    public double getModifier(EquipmentType type) {
        if (type == null || get(type) == null) {
            return 1;
        }

        return get(type).getModifier();
    }

    private void set(EquipmentType type, ItemEquipment item) {
        switch(type) {
            case SWORD:
                sword = item;
                break;
            case AXE:
                axe = item;
                break;
            case PICKAXE:
                pickaxe = item;
                break;
            case SHOVEL:
                shovel = item;
                break;
            case HELMET:
                helmet = item;
                break;
            case CHESTPLATE:
                chestplate = item;
                break;
            case LEGGINGS:
                leggings = item;
                break;
            case BOOTS:
                boots = item;
                break;
            default:
                break;
        }
    }

    public ItemEquipment swap(ItemEquipment item) {
        ItemEquipment previous = get(item.getType());

        set(item.getType(), item);

        if (previous != null) {
            Game.player.getInventory().add(new ItemStack((Item) previous, 1));
        }

        System.out.println(Game.player + " equipped " + item + (previous != null ? ", replacing " + previous : ""));

        Game.player.getInventory().subtract(new ItemStack((Item) item, 1));

        return previous;
    }

    @Override
    public String toString() {
        return Util.wrap("current equipment: ", false, true) +
                "sword: " + (sword == null ? "none" : sword) + ",\n" +
                "axe: " + (axe == null ? "none" : axe)  + ",\n" +
                "pickaxe: " + (pickaxe == null ? "none" : pickaxe)  + ", \n" +
                "shovel: " + (shovel == null ? "none" : shovel)  + ", \n" +
                "helmet: " + (helmet == null ? "none" : helmet)  + ", \n" +
                "chestplate: " + (chestplate == null ? "none" : chestplate)  + ", \n" +
                "leggings: " + (leggings == null ? "none" : leggings)  + ", \n" +
                "boots: " + (boots == null ? "none" : boots) ;
    }
}

abstract class Creature extends Entity {
    private boolean hasBeenDamaged = false;

    public List<ItemStack> damage(int damage) {
        hasBeenDamaged = true;
        return super.damage(damage);
    }

    protected abstract String getNormalMessage();
    protected abstract String getDamagedMessage();

    protected String getEventMessage() {
        if (hasBeenDamaged) {
            return getDamagedMessage();
        } else {
            return getNormalMessage();
        }
    }

    @Override
    public Event create() {
        return new Event(getEventMessage(),
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
    }
}

class Cow extends Creature {
    private EntityStatus status = new EntityStatus(8);

    public Cow() {

    }

    public Cow(EntityStatus status) {
        this.status = status.clone();
    }

    @Override
    protected String getNormalMessage() {
        return "you look at the cow grazing";
    }

    @Override
    protected String getDamagedMessage() {
        return "you chase after the cow as it runs around";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_beef, 1, 3));
        dropItems.add(new ItemStack(Items.leather, 0, 2));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Cow(status);
    }
}

class Pig extends Creature {
    private EntityStatus status = new EntityStatus(8);

    public Pig() {

    }

    public Pig(EntityStatus status) {
        this.status = status.clone();
    }

    @Override
    protected String getNormalMessage() {
        return "you look at the pig grazing";
    }

    @Override
    protected String getDamagedMessage() {
        return "you chase after the pig as it runs around";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_pork, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Pig(status);
    }
}

class Squid extends Creature {
    private EntityStatus status = new EntityStatus(8);

    public Squid() {

    }

    public Squid(EntityStatus status) {
        this.status = status.clone();
    }

    @Override
    protected String getNormalMessage() {
        return "you look at the squid swimming lazily";
    }

    @Override
    protected String getDamagedMessage() {
        return "you chase after the squid as it swims away";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.ink_sac, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Squid(status);
    }
}

class Villager extends Creature {
    private EntityStatus status = new EntityStatus(20);

    public Villager(EntityStatus status) {
        this.status = status.clone();
    }

    private List<VillagerTrade> trades = new ArrayList<>();

    public void addTrade(VillagerTrade trade) {
        trades.add(trade.setVillager(this));
    }

    public List<VillagerTrade> getTrades() {
        return trades;
    }

    @Override
    protected String getNormalMessage() {
        return null;
    }

    @Override
    protected String getDamagedMessage() {
        return null;
    }

    @Override
    public Event create() {
        return null;
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.emerald, 1));
        return dropItems;
    }

    @Override
    public Entity clone() {
        Villager villager = new Villager(status);

        for (VillagerTrade trade : trades) {
            villager.addTrade(trade);
        }

        return villager;
    }
}

class VillagerTrade {
    private List<ItemStack> buy;
    private List<ItemStack> sell;

    private Villager villager;

    public VillagerTrade(List<ItemStack> buy, List<ItemStack> sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public VillagerTrade(ItemStack[] buy, ItemStack[] sell) {
        this(Arrays.asList(buy), Arrays.asList(sell));
    }

    public VillagerTrade setVillager(Villager villager) {
        this.villager = villager;
        return this;
    }

    public boolean tradeWith(Player player) {
        Inventory inventory = player.getInventory();

        if (!inventory.has(buy)) {
            System.out.println(player + " tried to trade with " + villager + " but lacked the items to do so");
            return false;
        }

        System.out.println(player + " traded " + villager + " " + buy + " in exchange for " + sell);

        inventory.subtract(buy);
        inventory.add(sell);

        return true;
    }
}

abstract class Monster extends Entity {
    protected abstract int getMinDamage();
    protected abstract int getMaxDamage();
    protected int getAttackDamage() {
        return Game.random.nextInt(getMaxDamage() - getMinDamage() + 1) + getMinDamage();
    }

    protected abstract double getFightChance();

    protected abstract String getHitMessage();
    protected abstract String getMissMessage();

    public void fight() {
        if (EventGenerator.random(getFightChance())) {
            System.out.println(getHitMessage());
            Game.player.damage(getAttackDamage());
        } else {
            System.out.println(getMissMessage());
        }
    }
}

class Creeper extends Monster {
    private EntityStatus status = new EntityStatus(20);

    private boolean isCharged = false;

    public Creeper() {

    }

    public Creeper(EntityStatus status) {
        this.status = status.clone();
    }

    public Creeper(EntityStatus status, boolean isCharged) {
        this(status);
        this.isCharged = isCharged;
    }

    @Override
    protected int getMinDamage() {
        return 3;
    }

    @Override
    protected int getMaxDamage() {
        return 5;
    }

    @Override
    protected double getFightChance() {
        return 0.15;
    }

    @Override
    protected String getHitMessage() {
        return this + " exploded!";
    }

    @Override
    protected String getMissMessage() {
        return this + " almost exploded, stopping just in time";
    }

    @Override
    public void fight() {
        if (EventGenerator.random(getFightChance())) {
            System.out.println(getHitMessage());
            Game.player.damage(getAttackDamage());
            this.setDead();
        } else {
            System.out.println(getMissMessage());
        }
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return new Event("you keep a safe distance from the creeper",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("keep moving", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.gunpowder, 1, 3));
        return dropItems;
    }

    @Override
    public Creeper clone() {
        return new Creeper(status, isCharged);
    }
}

class Zombie extends Monster {
    private EntityStatus status = new EntityStatus(20);

    private boolean isVillager = false;

    public Zombie(boolean isVillager) {
        this.isVillager = isVillager;
    }

    public Zombie(EntityStatus status) {
        this.status = status.clone();
    }

    public Zombie(EntityStatus status, boolean isVillager) {
        this(status);
        this.isVillager = isVillager;
    }

    public Zombie convertVillager(Villager villager) {
        villager.setDead();
        return new Zombie(status, true);
    }

    @Override
    protected int getMinDamage() {
        return 2;
    }

    @Override
    protected int getMaxDamage() {
        return 4;
    }

    @Override
    protected double getFightChance() {
        return 0.1;
    }

    @Override
    protected String getHitMessage() {
        return this + " hit " + Game.player + "!";
    }

    @Override
    protected String getMissMessage() {
        return this + " tried to hit " + Game.player + " but missed";
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public Event create() {
        return new Event("you keep a safe distance from the zombie",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("keep moving", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.rotten_flesh, 1, 3));
        return dropItems;
    }

    @Override
    public Entity clone() {
        return new Zombie(status, isVillager);
    }

    @Override
    public String toString() {
        return "zombie" + (isVillager ? " villager" : "");
    }
}

enum EquipmentType {
    SWORD,
    AXE,
    PICKAXE,
    SHOVEL,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS
}

class Item {
    private static int id = 0;

    private String name;

    Item(String name) {
        this.name = name;
        System.out.println(this + " with id " + id++ + " constructed");
    }

    public String getName() {
        return name;
    }

    public boolean isSingleton() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Item) && other.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return name;
    }
}

class ItemAxe extends ItemHarvestTool {
    ItemAxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, EquipmentType.AXE, maxDurability, durability, attackDamage, minModifier, maxModifier);
    }

    ItemAxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }
}

interface ItemEquipment {
    EquipmentType getType();
    int getAttackDamage();
    double getModifier();
}

class ItemHarvestTool extends ItemWithDurability implements ItemEquipment {
    private final EquipmentType type;

    private final int attackDamage;

    private final double minModifier;
    private final double maxModifier;

    ItemHarvestTool(String name, EquipmentType type, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, maxDurability, durability);
        this.type = type;
        this.attackDamage = attackDamage;
        this.minModifier = minModifier;
        this.maxModifier = maxModifier;
    }

    ItemHarvestTool(String name, EquipmentType type,  int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, type, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return (Game.random.nextDouble() * (maxModifier - minModifier)) + minModifier;
    }

    @Override
    public EquipmentType getType() {
        return type;
    }
}

class ItemPickaxe extends ItemHarvestTool {
    ItemPickaxe(String name, int maxDurability, int durability, int attackDamage, double minModifier, double maxModifier) {
        super(name, EquipmentType.PICKAXE, maxDurability, durability, attackDamage, minModifier, maxModifier);
    }

    ItemPickaxe(String name, int maxDurability, int attackDamage, double minModifier, double maxModifier) {
        this(name, maxDurability, maxDurability, attackDamage, minModifier, maxModifier);
    }
}

class Items {
    public static final Item log;
    public static final Item planks;
    public static final Item stick;
    public static final Item boat;

    public static final Item wooden_sword;
    public static final Item wooden_axe;
    public static final Item wooden_pickaxe;

    public static final Item sand;
    public static final Item sandstone;
    public static final Item cactus;

    public static final Item raw_beef;
    public static final Item cooked_beef;
    public static final Item leather;
    public static final Item raw_pork;
    public static final Item cooked_pork;
    public static final Item ink_sac;

    public static final Item rotten_flesh;
    public static final Item gunpowder;

    public static final Item emerald;

    static {
        log = new Item("log");
        planks = new Item("planks");
        stick = new Item("stick");
        boat = new Item("boat");

        wooden_sword = new ItemSword("wooden sword", 48, 4);
        wooden_axe = new ItemAxe("wooden axe", 48, 3, 1.5, 2);
        wooden_pickaxe = new ItemPickaxe("wooden pickaxe", 48, 2, 1.5, 2);

        sand = new Item("sand");
        sandstone = new Item("sandstone");
        cactus = new Item("cactus");

        raw_beef = new Item("raw beef");
        cooked_beef = new Item("cooked beef");
        leather = new Item("leather");
        raw_pork = new Item("raw pork");
        cooked_pork = new Item("cooked pork");
        ink_sac = new Item("ink sac");

        rotten_flesh = new Item("rotten flesh");
        gunpowder = new Item("gunpowder");

        emerald = new Item("emerald");
    }
}

class ItemStack {
    private Item item;
    private int amount;

    private boolean isRandom = false;
    private int min;
    private int max;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemStack(Item item, int min, int max) {
        this(item, Game.random.nextInt(max + 1 - min) + min);
        isRandom = true;
        this.min = min;
        this.max = max;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public void multiplyAmount(double multiplier) {
        this.amount = (int) Math.round(this.amount * multiplier);
    }

    public boolean isEmpty() {
        return amount <= 0;
    }

    public static List<ItemStack> removeEmpty(List<ItemStack> itemStacks) {
        List<ItemStack> newItemStacks = new ArrayList<>();

        for (ItemStack itemStack : itemStacks) {
            if (!itemStack.isEmpty()) {
                newItemStacks.add(itemStack);
            }
        }

        return newItemStacks;
    }

    public boolean hasItem(ItemStack other) {
        return other.item.equals(this.item);
    }

    public boolean hasAmount(ItemStack other) {
        return this.amount >= other.amount;
    }

    public boolean has(ItemStack other) {
        return hasItem(other) && hasAmount(other);
    }

    public ItemStack subtract(ItemStack other) {
        if (!hasItem(other)) {
            return other;
        }

        if (other.amount > this.amount) {
            int previous = this.amount;
            this.amount = 0;
            return new ItemStack(other.item, other.amount - previous);
        }

        this.amount -= other.amount;

        return null;
    }

    public ItemStack copy() {
        return new ItemStack(item, amount);
    }

    public ItemStack generate() {
        if (this.isRandom) {
            return new ItemStack(this.item, this.min, this.max);
        } else {
            return this;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemStack)) {
            return false;
        }

        ItemStack otherItemStack = (ItemStack) other;

        return (otherItemStack.item.equals(this.item)) && (otherItemStack.amount == this.amount);
    }

    @Override
    public String toString() {
        return amount + "x " + item;
    }
}

class ItemSword extends ItemWithDurability implements ItemEquipment {
    private final int attackDamage;

    ItemSword(String name, int maxDurability, int durability, int attackDamage) {
        super(name, maxDurability, durability);
        this.attackDamage = attackDamage;
    }

    ItemSword(String name, int maxDurability, int attackDamage) {
        this(name, maxDurability, maxDurability, attackDamage);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getModifier() {
        return 1;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.SWORD;
    }
}

class ItemWithDurability extends Item {
    private final int maxDurability;
    private int durability;
    private boolean broken = false;

    ItemWithDurability(String name, int maxDurability) {
        super(name);
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
    }

    ItemWithDurability(String name, int maxDurability, int durability) {
        this(name, maxDurability);
        this.durability = durability;
    }

    public void damage() {
        if (broken) {
            return;
        }

        durability--;

        if (durability == 0) {
            broken = true;
        }
    }

    public void damage(int times) {
        for (int i = 0; i < times; i++) {
            damage();
        }
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public boolean isBroken() {
        return broken;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (durability: " + durability + "/" + maxDurability + ")";
    }
}

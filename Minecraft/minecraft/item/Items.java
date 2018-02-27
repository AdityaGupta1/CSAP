package minecraft.item;

public class Items {
    public static final Item log;
    public static final Item planks;
    public static final Item stick;
    public static final Item boat;

    public static final Item wooden_sword;
    public static final Item wooden_axe;
    public static final Item wooden_pickaxe;

    public static final Item stone_sword;
    public static final Item stone_axe;
    public static final Item stone_pickaxe;

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

    public static final Item cobblestone;
    public static final Item dirt;
    public static final Item coal_ore;
    public static final Item coal;
    public static final Item iron_ore;
    public static final Item iron_ingot;
    public static final Item diamond_ore;
    public static final Item diamond;

    static {
        log = new Item("log");
        planks = new Item("planks");
        stick = new Item("stick");
        boat = new Item("boat");

        wooden_sword = new ItemSword("wooden sword", 48, 4);
        wooden_axe = new ItemAxe("wooden axe", 48, 3, 1.5, 2);
        wooden_pickaxe = new ItemPickaxe("wooden pickaxe", 48, 2, 1.5, 2, 1);

        stone_sword = new ItemSword("stone sword", 192, 5);
        stone_axe = new ItemAxe("stone axe", 192, 4, 2, 2.75);
        stone_pickaxe = new ItemPickaxe("stone pickaxe", 192, 3, 2, 2.75, 2);

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

        cobblestone = new Item("cobblestone");
        dirt = new Item("dirt");
        coal_ore = new Item("coal ore");
        coal = new Item("coal");
        iron_ore = new Item("iron ore");
        iron_ingot = new Item("iron ingot");
        diamond_ore = new Item("diamond ore");
        diamond = new Item("diamond");
    }
}

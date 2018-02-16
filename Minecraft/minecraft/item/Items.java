package minecraft.item;

public class Items {
    public static final Item rotten_flesh;
    public static final Item gunpowder;
    public static final Item raw_beef;
    public static final Item cooked_beef;
    public static final Item leather;
    public static final Item raw_pork;
    public static final Item cooked_pork;
    public static final Item ink_sac;
    public static final Item emerald;

    static {
        rotten_flesh = new Item("rotten flesh");
        gunpowder = new Item("gunpowder");
        raw_beef = new Item("raw beef");
        cooked_beef = new Item("cooked beef");
        leather = new Item("leather");
        raw_pork = new Item("raw pork");
        cooked_pork = new Item("cooked pork");
        ink_sac = new Item("ink sac");
        emerald = new Item("emerald");
    }
}

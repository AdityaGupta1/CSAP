package minecraft.item;

public class ItemWithDurability extends Item {
    private final int maxDurability;
    private int durability;
    private boolean broken = false;

    ItemWithDurability(String name, int maxDurability) {
        super(name);
        this.maxDurability = maxDurability;
    }

    ItemWithDurability(String name, int maxDurability, int durability) {
        this(name, maxDurability);
        this.durability = durability;
    }

    public void damage() {
        durability--;

        if (durability == 0) {
            broken = true;
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
}

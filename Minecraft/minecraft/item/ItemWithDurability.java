package minecraft.item;

public class ItemWithDurability extends Item {
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
            System.out.println(this + " broke");
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

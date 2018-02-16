package minecraft.item;

public class ItemStack {
    private Item item;
    private int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemStack(String name, int amount) {
        this(new Item(name), amount);
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return amount <= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemStack)) {
            return false;
        }

        ItemStack otherItemStack = (ItemStack) other;

        return (otherItemStack.item.equals(this.item)) && (otherItemStack.amount == this.amount);
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

    public boolean subtract(ItemStack other, boolean check) {
        if (!hasItem(other)) {
            return false;
        }

        if (check && !has(other)) {
            return false;
        }

        if (has(other)) {
            this.amount -= other.amount;
            other.amount = 0;
        } else {
            other.amount -= this.amount;
            this.amount = 0;
        }

        return true;
    }

    public boolean subtract(ItemStack other) {
        return subtract(other, true);
    }

    @Override
    public String toString() {
        return amount + "x " + item;
    }
}
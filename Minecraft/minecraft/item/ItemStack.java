package minecraft.item;

import minecraft.game.Game;

import java.util.ArrayList;
import java.util.List;

public class ItemStack {
    private Item item;
    private int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemStack(Item item, int min, int max) {
        this.item = item;
        this.amount = Game.random.nextInt(max + 1 - min) + min;
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

    public ItemStack copy() {
        return new ItemStack(item, amount);
    }

    public static List<ItemStack> copyStacks(List<ItemStack> itemStacks) {
        List<ItemStack> copy = new ArrayList<>();

        for (ItemStack itemStack : itemStacks) {
            copy.add(itemStack.copy());
        }

        return copy;
    }

    @Override
    public String toString() {
        return amount + "x " + item;
    }
}

package minecraft.item;

import minecraft.game.Game;

import java.util.ArrayList;
import java.util.List;

public class ItemStack {
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

    public ItemStack generate() {
        if (this.isRandom) {
            return new ItemStack(this.item, this.min, this.max);
        } else {
            return this.copy();
        }
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

package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.List;

public abstract class Entity {
    private boolean dead;

    public List<ItemStack> die() {
        if (this.dead) {
            return null;
        }

        this.dead = true;
        return getDropItems();
    }

    public boolean isDead() {
        return dead;
    }

    public abstract List<ItemStack> getDropItems();

    public abstract Entity copy();

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}

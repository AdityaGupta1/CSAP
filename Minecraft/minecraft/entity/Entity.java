package minecraft.entity;

import minecraft.item.ItemStack;

import java.util.List;

public abstract class Entity {
    protected String name;
    private boolean dead;

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ItemStack> die() {
        if (this.dead) {
            return null;
        }

        this.dead = true;
        return getDropItems();
    }

    public abstract List<ItemStack> getDropItems();

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase() + " named " + name;
    }
}

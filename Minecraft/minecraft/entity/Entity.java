package minecraft.entity;

import minecraft.game.event.EventCreator;
import minecraft.item.ItemStack;

import java.util.List;

public abstract class Entity implements EventCreator {
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

    public abstract Entity copy();

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}

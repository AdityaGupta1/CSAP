package minecraft.entity.creature;

import minecraft.entity.Entity;
import minecraft.game.event.Event;
import minecraft.game.event.Response;
import minecraft.game.event.ResponseType;
import minecraft.item.ItemStack;

import java.util.List;

public abstract class Creature extends Entity {
    private boolean hasBeenDamaged = false;

    public List<ItemStack> damage(int damage) {
        hasBeenDamaged = true;
        return super.damage(damage);
    }

    protected abstract String getNormalMessage();
    protected abstract String getDamagedMessage();

    protected String getEventMessage() {
        if (hasBeenDamaged) {
            return getDamagedMessage();
        } else {
            return getNormalMessage();
        }
    }

    @Override
    public Event create() {
        return new Event(getEventMessage(),
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
    }
}

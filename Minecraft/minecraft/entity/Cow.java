package minecraft.entity;

import minecraft.game.Response;
import minecraft.game.ResponseType;
import minecraft.game.event.Event;
import minecraft.item.ItemStack;
import minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class Cow extends Creature {
    @Override
    public Event create() {
        return new Event("you look at the cow grazing",
                new Response("strike it", ResponseType.FIGHT, this),
                new Response("ignore it", ResponseType.IGNORE));
    }

    @Override
    public List<ItemStack> getDropItems() {
        ArrayList<ItemStack> dropItems = new ArrayList<>();
        dropItems.add(new ItemStack(Items.raw_beef, 1, 3));
        dropItems.add(new ItemStack(Items.leather, 0, 2));
        return dropItems;
    }

    @Override
    public Entity copy() {
        return new Cow();
    }
}

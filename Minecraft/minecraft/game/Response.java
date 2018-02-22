package minecraft.game;

import minecraft.biome.Biome;
import minecraft.entity.Entity;
import minecraft.game.event.EventCreator;
import minecraft.game.event.EventGenerator;
import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String input;
    private ResponseType type;
    private Object[] consequences;

    public Response(String input, ResponseType type, Object... consequences) {
        this.input = input;
        this.type = type;
        this.consequences = consequences;
    }

    public String getInput() {
        return input;
    }

    public void doConsequence() {
        switch(type) {
            case GET_ITEM:
                List<ItemStack> itemStacks = new ArrayList<>();

                for (Object object : consequences) {
                    itemStacks.add(((ItemStack) object).generate());
                }

                Game.player.pickUp(itemStacks);
                break;
            case FIGHT:
                Entity entity = ((Entity) consequences[0]).copy();

                // will change to striking with equipped weapon instead of auto kill
                Game.player.kill(entity);

                if (entity.isDead()) {
                    EventGenerator.resetEventCreator();
                }

                break;
            case ENTER_BIOME:
                Game.player.enterBiome((Biome) consequences[0]);
                EventGenerator.changeEventCreator(Game.currentBiome);
                break;
            case CHANGE:
                EventGenerator.changeEventCreator((EventCreator) consequences[0]);
                System.out.println(consequences[1]);
                break;
            case IGNORE:
            default:
                break;
        }
    }
}

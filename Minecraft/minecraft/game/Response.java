package minecraft.game;

import minecraft.entity.Entity;
import minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
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
                    itemStacks.add(((ItemStack) object).copy());
                }

                Game.player.pickUp(itemStacks);
                break;
            case KILL_MOB:
                List<Entity> entities = new ArrayList<>();

                for (Object object : consequences) {
                    entities.add(((Entity) object).copy());
                }

                Game.player.kill(entities);
                break;
            case IGNORE:
            default:
                break;
        }
    }
}

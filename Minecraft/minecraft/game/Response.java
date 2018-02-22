package minecraft.game;

import minecraft.biome.Biome;
import minecraft.entity.Entity;
import minecraft.entity.player.Player;
import minecraft.game.event.EventCreator;
import minecraft.game.event.EventGenerator;
import minecraft.item.EquipmentType;
import minecraft.item.ItemStack;
import minecraft.item.ItemWithDurability;

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
        Player player = Game.player;

        switch(type) {
            case GET_ITEM:
                List<ItemStack> itemStacks = new ArrayList<>();

                for (int i = 1; i < consequences.length; i++) {
                    itemStacks.add(((ItemStack) consequences[i]).generate());
                }

                EquipmentType type = (EquipmentType) consequences[0];
                double modifier = player.getEquipment().getModifier(type);

                for (ItemStack itemStack : itemStacks) {
                    itemStack.multiplyAmount(modifier);

                    if (modifier != 1) {
                        ((ItemWithDurability) player.getEquipment().get(type)).damage(itemStack.getAmount());
                    }
                }

                player.pickUp(itemStacks);
                break;
            case FIGHT:
                Entity entity = ((Entity) consequences[0]).copy();

                player.damage(entity);

                if (entity.isDead()) {
                    EventGenerator.resetEventCreator();
                }

                break;
            case ENTER_BIOME:
                player.enterBiome((Biome) consequences[0]);
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

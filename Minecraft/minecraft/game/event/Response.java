package minecraft.game.event;

import minecraft.biome.Biome;
import minecraft.entity.Entity;
import minecraft.entity.monster.Monster;
import minecraft.entity.player.Player;
import minecraft.game.Game;
import minecraft.item.EquipmentType;
import minecraft.item.ItemStack;
import minecraft.item.ItemWithDurability;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private final String input;
    private final int time;
    private final ResponseType type;
    private final Object[] consequences;

    public Response(String input, int time, ResponseType type, Object... consequences) {
        this.input = input;
        this.time = time;
        this.type = type;
        this.consequences = consequences;
    }

    public String getInput() {
        return input;
    }

    public void doConsequence() {
        Player player = Game.player;
        List<ItemStack> itemStacks = new ArrayList<>();

        switch(type) {
            case GET_ITEM:
                for (int i = 1; i < consequences.length; i++) {
                    itemStacks.add(((ItemStack) consequences[i]).generate());
                }

                EquipmentType type = (EquipmentType) consequences[0];
                double modifier = player.getEquipment().getModifier(type);

                for (ItemStack itemStack : itemStacks) {
                    if (modifier != 1) {
                        ((ItemWithDurability) player.getEquipment().get(type)).damage(itemStack.getAmount());
                    }
                }

                elapseTime(modifier);

                player.pickUp(itemStacks);

                break;
            case FIGHT:
                Entity entity = ((Entity) consequences[0]);

                elapseTime(1);

                player.damage(entity);

                if (entity.isDead()) {
                    EventGenerator.resetEventCreator();
                    break;
                }

                if (entity instanceof Monster) {
                    ((Monster) entity).fight();
                }

                break;
            case MINE:
                int miningLevel = (int) consequences[0];

                if (Game.player.getEquipment().getMiningLevel() < miningLevel) {
                    System.out.println("your mining level is not high enough!");
                    break;
                }

                for (int i = 1; i < consequences.length; i++) {
                    itemStacks.add(((ItemStack) consequences[i]).generate());
                }

                for (ItemStack itemStack : itemStacks) {
                    ((ItemWithDurability) player.getEquipment().get(EquipmentType.PICKAXE)).damage(itemStack.getAmount());
                }

                elapseTime(Game.player.getEquipment().getModifier(EquipmentType.PICKAXE));

                player.pickUp(itemStacks);

                break;
            case ENTER_BIOME:
                elapseTime(1);
                player.enterBiome((Biome) consequences[0]);
                EventGenerator.changeEventCreator(Game.currentBiome);
                break;
            case CHANGE:
                elapseTime(1);
                EventGenerator.changeEventCreator(((EventCreator) consequences[0]).clone());
                System.out.println(consequences[1]);
                break;
            case FLEE:
                EventGenerator.resetEventCreator();
                System.out.println(consequences[0]);
                elapseTime(1);
                break;
            case IGNORE:
                elapseTime(1);
                break;
            default:
                break;
        }
    }

    private void elapseTime(double modifier) {
        Game.elapseTime((int) Math.round(time / modifier));
    }
}

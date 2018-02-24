package minecraft.game.event;

import minecraft.entity.creature.Cow;
import minecraft.entity.creature.Pig;
import minecraft.entity.creature.Squid;
import minecraft.entity.monster.Creeper;
import minecraft.entity.monster.Zombie;
import minecraft.item.EquipmentType;
import minecraft.item.ItemStack;
import minecraft.item.Items;

public class Event {
    public static final Event TREES = new Event("you see trees",
            new Response("chop them down", ResponseType.GET_ITEM, EquipmentType.AXE, new ItemStack(Items.log, 4, 6)),
            new Response("ignore them", ResponseType.IGNORE));
    public static final Event SAND = new Event("you see sand",
            new Response("dig some sand", ResponseType.GET_ITEM, EquipmentType.SHOVEL, new ItemStack(Items.sand, 15, 25)),
            new Response("ignore it", ResponseType.IGNORE));
    public static final Event CACTUS = new Event("you see a cactus",
            new Response("chop it down", ResponseType.GET_ITEM, null, new ItemStack(Items.cactus, 1, 4)),
            new Response("ignore it", ResponseType.IGNORE));

    public static final Event PIG = new Event("you see a pig",
                    new Response("fight it", ResponseType.CHANGE, new Pig(), "you engage the pig"),
                    new Response("ignore it", ResponseType.IGNORE));
    public static final Event COW  = new Event("you see a cow",
                    new Response("fight it", ResponseType.CHANGE, new Cow(), "you engage the cow"),
                    new Response("ignore it", ResponseType.IGNORE));
    public static final Event SQUID = new Event("you see a squid",
                    new Response("fight it", ResponseType.CHANGE, new Squid(), "you engage the squid"),
                    new Response("ignore it", ResponseType.IGNORE));

    public static final Event ZOMBIE = new Event("you see a zombie",
                    new Response("fight it", ResponseType.CHANGE, new Zombie(false), "you engage the zombie"),
                    new Response("run away", ResponseType.IGNORE));
    public static final Event CREEPER = new Event("you see a creeper",
                    new Response("fight it", ResponseType.CHANGE, new Creeper(), "you engage the creeper"),
                    new Response("run away", ResponseType.IGNORE));

    private final String message;
    private final Response[] responses;
    public Event(String message, Response... responses) {
        this.message = "[ " + message + " ]";
        this.responses = responses;
    }

    public String getMessage() {
        return message;
    }

    public Response[] getResponses() {
        return responses;
    }

    public String getChoices() {
        String choicesString = "";

        for (int i = 0; i < responses.length; i++) {
            choicesString += (i + 1) + ") " + responses[i].getInput() + "\n";
        }

        return choicesString.substring(0, choicesString.length() - 1);
    }

    public Response getResponseFromInput(String input) {
        int inputNumber;

        try {
            inputNumber = Integer.parseInt(input) - 1;
        } catch(NumberFormatException exception) {
            return null;
        }

        if (inputNumber >= responses.length || inputNumber < 0) {
            return null;
        }

        return responses[inputNumber];
    }
}

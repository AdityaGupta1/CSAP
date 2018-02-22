package minecraft.game.event;

import minecraft.entity.*;
import minecraft.game.Response;
import minecraft.game.ResponseType;
import minecraft.item.ItemStack;
import minecraft.item.Items;

public class Event {
    public static final Event TREE = new Event("you see a tree",
                    new Response("chop it down", ResponseType.GET_ITEM, new ItemStack(Items.log, 4, 6)),
                    new Response("ignore it", ResponseType.IGNORE));
    public static final Event SAND = new Event("you see sand",
            new Response("dig some sand", ResponseType.GET_ITEM, new ItemStack(Items.sand, 15, 25)),
            new Response("ignore it", ResponseType.IGNORE));
    public static final Event CACTUS = new Event("you see a cactus",
            new Response("chop it down", ResponseType.GET_ITEM, new ItemStack(Items.cactus, 1, 4)),
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
        String choicesString = "you can: ";

        for (Response response : responses) {
            choicesString += response.getInput() + "; ";
        }

        return choicesString.substring(0, choicesString.length() - 2);
    }

    public Response getResponseFromInput(String input) {
        for (Response response : responses) {
            if (response.getInput().equalsIgnoreCase(input)) {
                return response;
            }
        }

        return null;
    }
}

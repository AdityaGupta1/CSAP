package minecraft.game;

import minecraft.entity.Cow;
import minecraft.entity.Creeper;
import minecraft.entity.Pig;
import minecraft.entity.Zombie;
import minecraft.item.ItemStack;
import minecraft.item.Items;

public enum Event {
    // generics
    TREE        ("you see a tree",
                    new Response("chop it down", ResponseType.GET_ITEM, new ItemStack(Items.log, 3)),
                    new Response("ignore it", ResponseType.IGNORE)),
    PIG         ("you see a pig",
                    new Response("kill it", ResponseType.KILL_MOB, new Pig()),
                    new Response("ignore it", ResponseType.IGNORE)),
    COW         ("you see a cow",
                    new Response("kill it", ResponseType.KILL_MOB, new Cow()),
                    new Response("ignore it", ResponseType.IGNORE)),
    ZOMBIE      ("you see a zombie",
                    new Response("kill it", ResponseType.KILL_MOB, new Zombie(false)),
                    new Response("run away", ResponseType.IGNORE)),
    CREEPER     ("you see a creeper",
                    new Response("kill it", ResponseType.KILL_MOB, new Creeper()),
                    new Response("run away", ResponseType.IGNORE));

    private final String message;
    private final Response[] responses;
    Event(String message, Response... responses) {
        this.message = "[" + message + "]";
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

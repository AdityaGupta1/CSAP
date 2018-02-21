package minecraft.biome;

import minecraft.game.Response;
import minecraft.game.ResponseType;
import minecraft.game.event.Event;

public abstract class Biome {
    public abstract Event getEvent();
    public abstract String getEnterMessage();

    public double getLeaveChance() {
        return 1;
    }

    // example: "BiomeExtremeHills" --> "extreme hills"
    public String getName() {
        String name = "";

        for (String word : this.getClass().getSimpleName()
                .split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            if (!word.equalsIgnoreCase("biome")) {
                name += word.toLowerCase() + " ";
            }
        }
        return name.substring(0, name.length() - 1);
    }

    public Event getEnterEvent() {
        return new Event(this.getEnterMessage(),
                new Response("enter the " + this.getName(), ResponseType.ENTER_BIOME, this),
                new Response("ignore it", ResponseType.IGNORE));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Biome) && ((Biome) other).getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}

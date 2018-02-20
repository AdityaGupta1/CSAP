package minecraft.biome;

import minecraft.game.event.Event;

public abstract class Biome {
    public abstract Event getEvent();
    public abstract String getEnterMessage();
}

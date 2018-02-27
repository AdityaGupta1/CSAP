package minecraft.game.mining;

import minecraft.game.event.Event;
import minecraft.game.event.EventCreator;

public class Mineshaft extends Mine {
    @Override
    public Event create() {
        return null;
    }

    @Override
    public EventCreator clone() {
        return new Mineshaft();
    }
}

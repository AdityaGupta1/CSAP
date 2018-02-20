package minecraft.biome;

import minecraft.game.event.Event;
import minecraft.game.event.EventChance;
import minecraft.game.event.EventGenerator;
import minecraft.game.event.WeightedEvents;

public class BiomeOcean extends Biome {
    @Override
    public Event getEvent() {
        WeightedEvents events = new WeightedEvents();

        events.addEventChances(EventChance.generateChances(EventGenerator.ocean, 1));

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "[ you see an ocean, with water extending as far as the eye can see ]";
    }
}

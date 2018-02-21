package minecraft.biome;

import minecraft.game.Game;
import minecraft.game.event.Event;
import minecraft.game.event.EventChance;
import minecraft.game.event.EventGenerator;
import minecraft.game.event.WeightedEvents;

public class BiomeDesert extends Biome {
    @Override
    public Event getEvent() {
        WeightedEvents events = new WeightedEvents();

        if (!Game.isNight()) {
            events.add(new EventChance(Event.SAND, 0.5));
            events.add(new EventChance(Event.CACTUS, 0.5));
        } else {
            events.add(EventChance.generateChances(EventGenerator.MONSTERS, 1));
        }

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see sand and cacti, signaling a desert";
    }
}

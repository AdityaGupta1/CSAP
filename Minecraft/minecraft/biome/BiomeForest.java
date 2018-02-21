package minecraft.biome;

import minecraft.game.Game;
import minecraft.game.event.Event;
import minecraft.game.event.EventChance;
import minecraft.game.event.EventGenerator;
import minecraft.game.event.WeightedEvents;

public class BiomeForest extends Biome {
    @Override
    public Event getEvent() {
        WeightedEvents events = new WeightedEvents();

        if (!Game.isNight()) {
            events.add(new EventChance(Event.TREE, 0.5));
            events.add(EventChance.generateChances(EventGenerator.CREATURES, 0.5));
        } else {
            events.add(new EventChance(Event.TREE, 0.5));
            events.add(EventChance.generateChances(EventGenerator.MONSTERS, 0.5));
        }

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see trees and hills covering a peaceful forest";
    }
}

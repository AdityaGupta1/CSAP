package minecraft.biome;

import minecraft.game.event.Event;

public class BiomeForest extends Biome {
    @Override
    public Event create() {
        return Event.TREES;

//        if (EventGenerator.random(Game.currentBiome.getLeaveChance())) {
//            return EventGenerator.pickRandomNewBiome().getEnterEvent();
//        }
//
//        WeightedEvents events = new WeightedEvents();
//
//        if (!Game.isNight()) {
//            events.add(new EventChance(Event.TREES, 0.5));
//            events.add(EventChance.generateChances(EventGenerator.CREATURES, 0.5));
//        } else {
//            events.add(new EventChance(Event.TREES, 0.5));
//            events.add(EventChance.generateChances(EventGenerator.MONSTERS, 0.5));
//        }
//
//        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see trees and hills covering a peaceful forest";
    }
}

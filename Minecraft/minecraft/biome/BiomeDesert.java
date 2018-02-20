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
            events.addEventChances(EventChance.generateChances(EventGenerator.creatures, 1));
        } else {
            events.addEventChances(EventChance.generateChances(EventGenerator.monsters, 1));
        }

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "[ you see sand and cacti, signs of a desert ]";
    }
}

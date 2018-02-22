package minecraft.biome;

import minecraft.game.Game;
import minecraft.game.event.Event;
import minecraft.game.event.EventChance;
import minecraft.game.event.EventGenerator;
import minecraft.game.event.WeightedEvents;
import minecraft.item.Items;

public class BiomeOcean extends Biome {
    @Override
    public Event create() {
        if (EventGenerator.random(Game.currentBiome.getLeaveChance())) {
            return EventGenerator.pickRandomNewBiome().getEnterEvent();
        }

        WeightedEvents events = new WeightedEvents();

        events.add(EventChance.generateChances(EventGenerator.OCEAN, 1));

        return events.generateEvent();
    }

    @Override
    public String getEnterMessage() {
        return "you see an ocean, with water extending as far as the eye can see";
    }

    @Override
    public double getLeaveChance() {
        return Game.player.getInventory().has(Items.boat) ? 0.05 : 0.01;
    }
}

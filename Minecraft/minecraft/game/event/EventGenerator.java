package minecraft.game.event;

import minecraft.biome.Biome;
import minecraft.biome.BiomeDesert;
import minecraft.biome.BiomeForest;
import minecraft.biome.BiomeOcean;
import minecraft.game.Game;

public class EventGenerator {
    public static final Event[] CREATURES = {Event.PIG, Event.COW};
    public static final Event[] MONSTERS = {Event.ZOMBIE, Event.CREEPER};
    public static final Event[] OCEAN = {Event.SQUID};

    private static final Biome[] BIOMES = {new BiomeForest(), new BiomeDesert(), new BiomeOcean()};

    private static EventCreator previousEventCreator = Game.currentBiome;
    public static EventCreator eventCreator = Game.currentBiome;

    public static Event generate() {
        return eventCreator.create();
    }

    public static void changeEventCreator(EventCreator newEventCreator) {
        previousEventCreator = eventCreator;
        eventCreator = newEventCreator;
        Game.ui.reset();
    }

    public static void resetEventCreator() {
        changeEventCreator(previousEventCreator);
    }

    public static void resetEventCreatorToBiome() {
        changeEventCreator(Game.currentBiome);
    }

    public static Biome pickRandomNewBiome() {
        Biome biome;

        do {
            biome = BIOMES[(int) (Math.random() * BIOMES.length)];
        } while (Game.currentBiome.equals(biome));

        return biome;
    }

    public static boolean random(double probability) {
        return Game.random.nextDouble() < probability;
    }
}

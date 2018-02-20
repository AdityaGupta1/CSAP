package minecraft.game.event;

import minecraft.biome.Biome;
import minecraft.biome.BiomeDesert;
import minecraft.biome.BiomeForest;
import minecraft.biome.BiomeOcean;
import minecraft.game.Game;

public class EventGenerator {
    public static final Event[] creatures = {Event.PIG, Event.COW};
    public static final Event[] monsters = {Event.ZOMBIE, Event.CREEPER};
    public static final Event[] ocean = {Event.SQUID};

    private static final Biome[] biomes = {new BiomeForest(), new BiomeDesert(), new BiomeOcean()};
    private static final double biomeChangeChance = 0.05;

    public static Event generate() {
        if (random(biomeChangeChance)) {
            // biome change
        }

        return Game.currentBiome.getEvent();
    }

    private static boolean random(double probability) {
        return Game.random.nextDouble() > probability;
    }

    private static Biome pickRandomBiome() {
        return biomes[(int) (Math.random() * biomes.length)];
    }
}

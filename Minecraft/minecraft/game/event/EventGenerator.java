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

    public static Event generate() {
        if (random(Game.currentBiome.getLeaveChance())) {
            return pickRandomNewBiome().getEnterEvent();
        }

        return Game.currentBiome.getEvent();
    }

    private static boolean random(double probability) {
        return Game.random.nextDouble() < probability;
    }

    private static Biome pickRandomNewBiome() {
        Biome biome;

        do {
            biome = BIOMES[(int) (Math.random() * BIOMES.length)];
        } while (Game.currentBiome.equals(biome));

        return biome;
    }
}

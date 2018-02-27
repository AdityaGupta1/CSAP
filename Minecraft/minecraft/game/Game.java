package minecraft.game;

import minecraft.biome.Biome;
import minecraft.biome.BiomeForest;
import minecraft.entity.player.Player;
import minecraft.game.crafting.CraftingSystem;
import minecraft.world.Sky;

import java.util.Random;

public class Game {
    private boolean run = true;

    public static final Player player = new Player("SDOAJ");
    public static Biome currentBiome = new BiomeForest();

    public static final UserInterface ui = new UserInterface();
    public static final CraftingSystem craftingSystem = new CraftingSystem();

    private static final Sky sky = new Sky();

    public static final Random random = new Random();

    public void startLoop() throws InterruptedException {
        System.out.println(UserInterface.controlsMessage + "\n");

        while (run) {
            ui.showEvent();

            if (getTime() % 60 == 0) {
                System.out.println("( the time is " + getTimeString() + " )");
            }
        }
    }

    public void stopLoop() {
        run = false;
    }

    private static int getTime() {
        return sky.getTime();
    }

    private static String getTimeString() {
        return sky.toString();
    }

    public static void elapseTime(int time) {
        sky.elapseTime(time);
    }

    public static boolean isNight() {
        return sky.isNight();
    }
}

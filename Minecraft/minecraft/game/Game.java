package minecraft.game;

import minecraft.biome.Biome;
import minecraft.biome.BiomeForest;
import minecraft.entity.player.Player;
import minecraft.game.crafting.CraftingSystem;
import minecraft.world.Sky;

import java.util.Random;

public class Game {
    private boolean run = true;
    private long ticks = 0;

    public static final String controlsMessage = "commands: H = help, R = replay event, I = inventory, C = crafting, E = equipment, S = status, Q = quit";

    public static final Player player = new Player("SDOAJ");
    public static Biome currentBiome = new BiomeForest();

    private static final UserInterface ui = new UserInterface();
    public static final CraftingSystem craftingSystem = new CraftingSystem();

    private static final Sky sky = new Sky();

    public static final Random random = new Random();

    public void startLoop() throws InterruptedException {
        System.out.println(controlsMessage + "\n");

        (new Thread(ui)).start();

        while(run) {
            // 1/20 second real time --> 3 seconds minecraft time
            if (ticks % 20 == 0) {
                sky.incrementTime(1);

                if (getTime() % 60 == 0) {
                    System.out.println("( " + Game.getTimeString() + " )");
                }
            }

            Thread.sleep(50);
            ticks++;
        }
    }

    public void stopLoop() {
        run = false;
    }

    public static int getTime() {
        return sky.getTime();
    }

    public static String getTimeString() {
        return sky.toString();
    }

    public static boolean isNight() {
        return sky.isNight();
    }
}

package minecraft;

import minecraft.entity.Cow;
import minecraft.entity.Pig;
import minecraft.entity.Player;
import minecraft.entity.Zombie;

public class HierarchyDemo_4MaGupta {
    public static void main(String[] args) {
        Player player = new Player("SDOAJ");

        Zombie zombie = new Zombie("jgnibo");
        Pig pig = new Pig("Mr. Piggy");
        Cow cow = new Cow("Thomas the Tank Engine");

        player.kill(zombie, pig, cow);

        System.out.println("\n" + player.fullDescription());
    }
}



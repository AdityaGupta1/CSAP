package minecraft;

import minecraft.game.Game;

public class Main {
    public static void main(String[] args) {
        try {
            (new Game()).startLoop();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}



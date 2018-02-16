package minecraft;

import minecraft.game.Game;

public class HierarchyDemo_4MaGupta {
    public static void main(String[] args) {
        try {
            (new Game()).startLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void print(Object message, boolean newline) {
        System.out.println((newline ? "\n" : "") + message);
    }

    private static void print(Object message) {
        print(message, false);
    }

    private static void print() {
        print("");
    }
}



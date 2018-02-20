package minecraft.game.event;

import minecraft.game.Game;

public class EventGenerator {
    private static final Event[] creatures = {Event.PIG, Event.COW};
    private static final Event[] monsters = {Event.ZOMBIE, Event.CREEPER};

    public static Event generate() {
        WeightedEvents events = new WeightedEvents();

        if (!Game.isNight()) {
            events.addEventChance(new EventChance(Event.TREE, 0.5));
            events.addEventChances(EventChance.generateChances(creatures, 0.5));
        } else {
            events.addEventChance(new EventChance(Event.TREE, 0.5));
            events.addEventChances(EventChance.generateChances(monsters, 0.5));
        }

        return events.generateEvent();
    }

    private static boolean random(double probability) {
        return Game.random.nextDouble() > probability;
    }

    private static Event pickRandom(Event[] events) {
        return events[(int) (Math.random() * events.length)];
    }
}

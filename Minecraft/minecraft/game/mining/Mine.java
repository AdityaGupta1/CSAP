package minecraft.game.mining;

import minecraft.game.event.*;

public class Mine implements EventCreator {
    @Override
    public Event create() {
        WeightedEvents events = new WeightedEvents();

        events.add(new EventChance(Event.STONE, 0.7));
        events.add(new EventChance(Event.DIRT, 0.1));
        events.add(new EventChance(Event.COAL_ORE, 0.04));
        events.add(new EventChance(Event.IRON_ORE, 0.04));
        events.add(new EventChance(Event.DIAMOND_ORE, 0.02));
        events.add(new EventChance(new Event("you see a cave",
                new Response("enter the cave", 2, ResponseType.CHANGE, new Cave()),
                new Response("ignore it", 0, ResponseType.IGNORE)), 0.08));
        events.add(new EventChance(new Event("you see an abandoned mineshaft",
                new Response("enter the abandoned mineshaft", 2, ResponseType.CHANGE, new Mineshaft()),
                new Response("ignore it", 0, ResponseType.IGNORE)), 0.02));

        return events.generateEvent();
    }

    @Override
    public EventCreator clone() {
        return new Mine();
    }
}

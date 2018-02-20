package minecraft.game.event;

import minecraft.game.Game;

import java.util.*;

public class WeightedEvents {
    private List<EventChance> events = new ArrayList<>();
    private Map<Event, ChanceRange> eventChances = new HashMap<>();

    public void addEventChance(EventChance eventChance) {
        events.add(eventChance);
        generateChances();
    }

    public void addEventChances(EventChance... eventChances) {
        events.addAll(Arrays.asList(eventChances));
        generateChances();
    }

    private void generateChances() {
        double bound = 0;
        for (EventChance event : events) {
            eventChances.put(event.getEvent(), new ChanceRange(bound, bound += event.getChance()));
        }
    }

    public Event generateEvent() {
        double random = Game.random.nextDouble();

        for (Event event : eventChances.keySet()) {
            if (eventChances.get(event).isInRange(random)) {
                return event;
            }
        }

        return null;
    }
}

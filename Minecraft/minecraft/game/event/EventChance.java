package minecraft.game.event;

public class EventChance {
    private Event event;
    private double chance;

    public EventChance(Event event, double chance) {
        this.event = event;
        this.chance = chance;
    }

    public static EventChance[] generateChances(Event[] events, double chance) {
        EventChance[] eventChances = new EventChance[events.length];
        double singleChance = chance / events.length;

        for (int i = 0; i < events.length; i++) {
            eventChances[i] = new EventChance(events[i], singleChance);
        }

        return eventChances;
    }

    public Event getEvent() {
        return event;
    }

    public double getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return event + " with " + Math.round(100 * chance) + "% chance";
    }
}

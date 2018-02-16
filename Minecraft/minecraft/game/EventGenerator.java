package minecraft.game;

public class EventGenerator {
    private static final Event[] creatures = {Event.PIG, Event.COW};
    private static final Event[] monsters = {Event.ZOMBIE, Event.CREEPER};

    public static Event generate() {
        if (Game.isNight()) {
            return Game.random.nextFloat() > 0.5 ? pickRandom(monsters) : Event.TREE;
        } else {
            return Game.random.nextFloat() > 0.5 ? pickRandom(creatures) : Event.TREE;
        }
    }

    private static Event pickRandom(Event[] events) {
        return events[(int) (Math.random() * events.length)];
    }
}

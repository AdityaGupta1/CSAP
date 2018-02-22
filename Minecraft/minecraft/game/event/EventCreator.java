package minecraft.game.event;

public interface EventCreator {
    Event create();
    EventCreator clone();
}

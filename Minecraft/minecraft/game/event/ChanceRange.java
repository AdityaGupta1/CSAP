package minecraft.game.event;

public class ChanceRange {
    private double min;
    private double max;

    public ChanceRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(double chance) {
        return chance > min && chance <= max;
    }

    @Override
    public String toString() {
        return min + " to " + max;
    }
}

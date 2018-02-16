package minecraft.item;

public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Item) && (((Item) other).name == this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}

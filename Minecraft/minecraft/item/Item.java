package minecraft.item;

public class Item {
    private String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSingleton() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Item) && other.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return name;
    }
}

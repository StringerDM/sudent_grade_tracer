package View;

public class Item {

    final public String name;
    final public Action action;

    public Item(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }
}

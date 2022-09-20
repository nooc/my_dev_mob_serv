package space.nixus.maddoc;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Inventory implements Serializable {
    protected HashMap<String, GameItem> content;

    protected Inventory() {
        content = new HashMap<>();
    }

    public void init(GameItem... items) {
        for (var item : items) {
            content.put(item.getName(), item);
        }
    }

    public abstract void describe();

    public abstract String invType();

    public GameItem getItem(String name) {
        return content.get(name);
    }

    public boolean moveItem(String name, Inventory target) {
        var item = getItem(name);
        if (item == null || item.isStatic()) {
            Manipulator.cantUnderstand();
            return false;
        } else {
            content.remove(name);
            target.content.put(name, item);
            item.touch();
            if (target.invType().equals(PlayerInventory.TYPE)) {
                Game.fmt("You pick up %s.", name);
            } else {
                Game.fmt("You drop %s.", name);

            }
        }
        return true;
    }

    public void discardItem(String name) {
        var item = getItem(name);
        if (item != null && !item.isStatic()) {
            content.remove(name);
        }
    }
}
